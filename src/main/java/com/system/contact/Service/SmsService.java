package com.system.contact.Service;

import com.system.contact.DTO.*;
import com.system.contact.Model.Contact;
import com.system.contact.Model.Sms;
import com.system.contact.Model.SmsAssociation;
import com.system.contact.Repository.ContactRepository;
import com.system.contact.Repository.SmsRepository;
import com.system.contact.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SmsService {

    @Autowired
    private SmsRepository smsRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private PhoneBookService phoneBookService;

    @Autowired
    private ContactService contactService;


    public List<SmsDTO> getAllSms() throws CustomException{
        try {
            List<Sms> smsList = smsRepository.findAll();
            return smsList.stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("An error occurred while getting sms: ", e.getMessage(), 500);
        }
    }


    public SmsResponseDTO getSmsById(Long id) throws CustomException {
        try {
            List<ContactDTO> contactsFinal = new ArrayList<>();
            List<Contact> filteredContactList = new ArrayList<>();
            Set<String> processedPhoneNumbers = new HashSet<>();
            Map<String, String> phoneToNameMapping = new HashMap<>();

            Sms sms = smsRepository.findById(id).orElse(null);
            if (sms == null) {
                throw new CustomException("SMS not found", "The SMS with ID " + id + " does not exist", 404);
            }

            List<SmsAssociation> smsAssociations = sms.getAssociations();
            String destinationNumber = sms.getDestinationNumber();
            if (destinationNumber != null) {
                SmsDTO smsDto = toDTO(sms);
                return new SmsResponseDTO(smsDto, contactsFinal);
            }

            for (SmsAssociation smsAssociation : smsAssociations) {
                // rest of the code ...

            }

            SmsDTO smsDto = toDTO(sms);
            return new SmsResponseDTO(smsDto, contactsFinal);
        } catch (Exception e) {
            throw new CustomException("An error occurred while processing SMS " , e.getMessage(), 500);
        }
    }

    public Sms createSms(SmsDTO smsDTO) throws CustomException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            smsDTO.setSentDate(localDateTime);
            Sms sms = toEntity(smsDTO);
            return smsRepository.save(sms);
        } catch (Exception e) {
            throw new CustomException("An error occurred while creating SMS" , e.getMessage(), 500);
        }
    }


    public void deleteSms(Long id) throws CustomException {
        try {
            Optional<Sms> optionalSms = smsRepository.findById(id);

            if (optionalSms.isPresent()) {
                // SMS exists, proceed with deletion
                smsRepository.deleteById(id);
            } else {
                // SMS doesn't exist, throw CustomException
                throw new CustomException("SMS with ID " + id +" not found" ,  " not found", 404);
            }
        } catch (Exception e) {
            throw new CustomException("An error occurred while deleting SMS " , e.getMessage(), 500);
        }
    }

    public void deleteSmsAll() throws CustomException {
        try {
            smsRepository.deleteAll();
        } catch (Exception e) {
            throw new CustomException("An error occurred while deleting all SMS ", e.getMessage(), 500);
        }
    }



    public SmsDTO toDTO(Sms sms) {
        SmsDTO smsDTO = new SmsDTO();

        smsDTO.setId(sms.getId());
        smsDTO.setBody(sms.getBody());
        smsDTO.setSourceNumber(sms.getSourceNumber());
        smsDTO.setDestinationNumber(sms.getDestinationNumber());
        smsDTO.setSentDate(sms.getSentDate());
        List<SmsAssociation> smsAssosiactions = sms.getAssociations();
        List<SmsAssociationDTO> smsAssociationsDTO = smsAssosiactions.stream().map(smsAssosiaction -> SmsAssociationtoDto(smsAssosiaction)).collect(Collectors.toList());
        smsDTO.setAssociations(smsAssociationsDTO);

        return smsDTO;
    }

    public Sms toEntity(SmsDTO smsDTO) throws CustomException{
        Sms sms = new Sms();
        sms.setBody(smsDTO.getBody());
        sms.setSourceNumber(smsDTO.getSourceNumber());
        sms.setDestinationNumber(smsDTO.getDestinationNumber());
        sms.setSentDate(smsDTO.getSentDate());
        List<SmsAssociationDTO> smsAssosiactionsDTO = smsDTO.getAssociations();
        List<SmsAssociation> smsAssociations = smsAssosiactionsDTO.stream().map(smsAssosiactionDTO -> {
            try {
                return SmsAssociationtoEntity(smsAssosiactionDTO, sms);
            } catch (CustomException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        System.out.println(smsAssociations);
        sms.setAssociations(smsAssociations);
        return sms;
    }


    public SmsAssociation SmsAssociationtoEntity(SmsAssociationDTO smsAssociationDTO, Sms sms) throws CustomException {
        SmsAssociation smsAssociation = new SmsAssociation();

        PhoneBookDTO phoneBookDTO = phoneBookService.getPhoneBookById(smsAssociationDTO.getPhoneBookId());
        smsAssociation.setPhoneBook(phoneBookService.toEntity(phoneBookDTO));
        smsAssociation.setSms(sms);
        smsAssociation.setAgeLimit(smsAssociationDTO.getAgeLimit());
        smsAssociation.setSmsCondition(smsAssociationDTO.getSmsCondition());
        return smsAssociation;
    }


    public SmsAssociationDTO SmsAssociationtoDto(SmsAssociation smsAssociation) {
        SmsAssociationDTO dto = new SmsAssociationDTO();
        dto.setPhoneBookId(smsAssociation.getPhoneBook().getId());
        dto.setAgeLimit(smsAssociation.getAgeLimit());
        dto.setSmsCondition(smsAssociation.getSmsCondition());
        return dto;
    }

}
