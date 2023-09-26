package com.system.contact.Service;

import com.system.contact.DTO.*;
import com.system.contact.Model.*;
import com.system.contact.Repository.ContactRepository;
import com.system.contact.Repository.SmsRepository;
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


    public List<SmsDTO> getAllSms() {
        return smsRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SmsResponseDTO getSmsById(Long id) {
        List<ContactDTO> contactsFinal = new ArrayList<>();
        List<Contact> filteredContactList = new ArrayList<>();
        Set<String> processedPhoneNumbers = new HashSet<>();
        Map<String, String> phoneToNameMapping = new HashMap<>();

        Sms sms = smsRepository.findById(id).orElse(null);
        if (sms != null) {
            List<SmsAssociation> smsAssociations = sms.getAssociations();
            String destinationNumber = sms.getDestinationNumber();  // Get destination number

            for (SmsAssociation smsAssociation : smsAssociations) {
                PhoneBook phoneBook = smsAssociation.getPhoneBook();
                int age = smsAssociation.getAgeLimit();
                String condition = smsAssociation.getSmsCondition();

                if(condition == "less") {
                     filteredContactList = contactRepository.findByAgeLessThan(age);
                }
                if(condition == "greater") {
                     filteredContactList = contactRepository.findByAgeGreaterThan(age);
                }
                if(condition == "equal") {
                     filteredContactList = contactRepository.findByAgeEquals(age);
                }



                for (Contact contact : filteredContactList) {
                    int contactAge = Integer.parseInt(contact.getAge());
                    String phoneNumber = contact.getPhoneNumber();

                    if (processedPhoneNumbers.contains(phoneNumber)) {
                        continue;
                    }





                }
            }

            if (destinationNumber != null) {
                contactsFinal.clear();  // Clear the contacts list
            }
        }

        SmsDTO smsDto = toDTO(sms);
        return new SmsResponseDTO(smsDto, contactsFinal);
    }


    public Sms createSms(SmsDTO smsDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        smsDTO.setSentDate(localDateTime);
        Sms sms = toEntity(smsDTO);
//        Sms save = smsRepository.save(sms);
//        save.getId()
        return smsRepository.save(sms);
    }

    public void deleteSms(Long id) {
        smsRepository.deleteById(id);
    }

    public void deleteSmsAll() {
        smsRepository.deleteAll();
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

    public Sms toEntity(SmsDTO smsDTO) {
        Sms sms = new Sms();
        sms.setBody(smsDTO.getBody());
        sms.setSourceNumber(smsDTO.getSourceNumber());
        sms.setDestinationNumber(smsDTO.getDestinationNumber());
        sms.setSentDate(smsDTO.getSentDate());


        List<SmsAssociationDTO> smsAssosiactionsDTO = smsDTO.getAssociations();
        List<SmsAssociation> smsAssociations = smsAssosiactionsDTO.stream().map(smsAssosiactionDTO -> SmsAssociationtoEntity(smsAssosiactionDTO,sms)).collect(Collectors.toList());
        System.out.println(smsAssociations);
        sms.setAssociations(smsAssociations);
        return sms;
    }


    public SmsAssociation SmsAssociationtoEntity(SmsAssociationDTO smsAssociationDTO,Sms sms) {
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
