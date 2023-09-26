//package com.system.contact.Service;
//
//import com.system.contact.DTO.PhoneBookDTO;
//import com.system.contact.DTO.SmsAssociationDTO;
//import com.system.contact.Model.Sms;
//import com.system.contact.Model.SmsAssociation;
//import com.system.contact.Repository.SmsAssociationRepository;
//import com.system.contact.Repository.SmsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class SmsAssociationService {
//
//    @Autowired
//    private SmsAssociationRepository smsAssociationRepository;
//
//    @Autowired
//    private SmsRepository smsRepository;
//    @Autowired
//    private PhoneBookService phoneBookService;
//
//    public List<SmsAssociation> getAllSmsAssociations() {
//        return smsAssociationRepository.findAll();
//    }
//
//    public SmsAssociation getSmsAssociationById(Long id) {
//        Optional<SmsAssociation> associationOptional = smsAssociationRepository.findById(id);
//        return associationOptional.orElse(null);
//    }
//
//    public SmsAssociation createSmsAssociation(SmsAssociationDTO smsAssociationDTO) {
//        return smsAssociationRepository.save(toEntity(smsAssociationDTO));
//    }
//
//    public SmsAssociation updateSmsAssociation(Long id, SmsAssociation updatedSmsAssociation) {
//        Optional<SmsAssociation> existingAssociationOptional = smsAssociationRepository.findById(id);
//
//        if (existingAssociationOptional.isPresent()) {
//            SmsAssociation existingAssociation = existingAssociationOptional.get();
//            existingAssociation.setAgeLimit(updatedSmsAssociation.getAgeLimit());
//            existingAssociation.setSmsCondition(updatedSmsAssociation.getSmsCondition());
//            return smsAssociationRepository.save(existingAssociation);
//        } else {
//            return null;
//        }
//    }
//
//    public boolean deleteSmsAssociation(Long id) {
//        Optional<SmsAssociation> associationOptional = smsAssociationRepository.findById(id);
//
//        if (associationOptional.isPresent()) {
//            smsAssociationRepository.deleteById(id);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//
//    public SmsAssociation toEntity(SmsAssociationDTO smsAssociationDTO) {
//        SmsAssociation smsAssociation = new SmsAssociation();
//        smsAssociation.setId(smsAssociationDTO.getId());
//
//
//        PhoneBookDTO phoneBookDTO = phoneBookService.getPhoneBookById(smsAssociationDTO.getPhoneBookId());
//        smsAssociation.setPhoneBook(phoneBookService.toEntity(phoneBookDTO));
//
//        smsAssociation.setAgeLimit(smsAssociationDTO.getAgeLimit());
//        smsAssociation.setSmsCondition(smsAssociationDTO.getSmsCondition());
//
//        Long sms_id = smsAssociationDTO.getSms_id();
//        Sms sms =  smsRepository.getReferenceById(sms_id);
//        smsAssociation.setSms(sms);
//        return smsAssociation;
//    }
//
//
//    public SmsAssociationDTO toDto(SmsAssociation smsAssociation) {
//        SmsAssociationDTO dto = new SmsAssociationDTO();
//        dto.setId(smsAssociation.getId());
//        dto.setPhoneBookId(smsAssociation.getPhoneBook().getId());
//        dto.setAgeLimit(smsAssociation.getAgeLimit());
//        dto.setSmsCondition(smsAssociation.getSmsCondition());
//        dto.setSms_id(smsAssociation.getSms().getId());
//        return dto;
//    }
//}
