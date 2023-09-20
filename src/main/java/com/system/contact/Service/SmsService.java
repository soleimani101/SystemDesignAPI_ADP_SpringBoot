package com.system.contact.Service;

import com.system.contact.DTO.SmsDTO;
import com.system.contact.Model.Sms;
import com.system.contact.Model.SmsAssociation;
import com.system.contact.Repository.SmsAssociationRepository;
import com.system.contact.Repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmsService {

    @Autowired
    private SmsRepository smsRepository;
    private SmsAssociationRepository smsAssociationRepository;

    public List<SmsDTO> getAllSms() {
        return smsRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SmsDTO getSmsById(Long id) {
        Sms sms = smsRepository.findById(id).get();
        SmsDTO smsDto = toDTO(sms);
        return smsDto;
    }

    public Sms createSms(SmsDTO smsDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        smsDTO.setSentDate(localDateTime);
        Sms sms = toEntity(smsDTO);
        return smsRepository.save(sms);
    }

//    public Sms updateSms(Long id, Sms sms) {
//        Optional<Sms> existingSmsOptional = smsRepository.findById(id);
//        if (existingSmsOptional.isPresent()) {
//            Sms existingSms = existingSmsOptional.get();
//            // Update relevant fields of existingSms with sms
//            existingSms.setBody(sms.getBody());
//            existingSms.setSourceNumber(sms.getSourceNumber());
//            // Update other fields as needed
//
//            return smsRepository.save(existingSms);
//        } else {
//            return null; // Sms with given id not found
//        }
//    }

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

//        // Assuming you have a method to extract phonebook IDs from PhoneBook entities
        List<Long> assosiationIds = extractAssosiationId(sms.getAssociations());
        smsDTO.setAssociationIds(assosiationIds);

        return smsDTO;
    }

    public Sms toEntity(SmsDTO smsDTO) {
        Sms sms = new Sms();
        sms.setId(smsDTO.getId());
        sms.setBody(smsDTO.getBody());
        sms.setSourceNumber(smsDTO.getSourceNumber());
        sms.setDestinationNumber(smsDTO.getDestinationNumber());
        sms.setSentDate(smsDTO.getSentDate());
        // Assuming you have a method to convert phonebook IDs to PhoneBook entities
        List<SmsAssociation> smsAssosiactions = convertIdToSmsAssosiaction(smsDTO.getAssociationIds());
        sms.setAssociations(smsAssosiactions);
        return sms;
    }


    private static List<Long> extractAssosiationId(List<SmsAssociation> smsAssociations) {
        List<Long> smsasossiationid = new ArrayList<>();
        for (SmsAssociation smsAssociation : smsAssociations) {
            smsasossiationid.add(smsAssociation.getId());
        }
        return smsasossiationid;
    }


    private List<SmsAssociation> convertIdToSmsAssosiaction(List<Long> smsasossiationids) {
        List<SmsAssociation> smsasossiations = new ArrayList<>();
        for (Long smsasossiationid : smsasossiationids) {
            SmsAssociation smsAssociation = smsAssociationRepository.findById(smsasossiationid).get();
            smsasossiations.add(smsAssociation);
        }
        return smsasossiations;
    }

}
