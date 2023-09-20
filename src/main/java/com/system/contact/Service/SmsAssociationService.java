package com.system.contact.Service;

import com.system.contact.Model.SmsAssociation;
import com.system.contact.Repository.SmsAssociationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SmsAssociationService {

    @Autowired
    private SmsAssociationRepository smsAssociationRepository;

    public List<SmsAssociation> getAllSmsAssociations() {
        return smsAssociationRepository.findAll();
    }

    public SmsAssociation getSmsAssociationById(Long id) {
        Optional<SmsAssociation> associationOptional = smsAssociationRepository.findById(id);
        return associationOptional.orElse(null);
    }

    public SmsAssociation createSmsAssociation(SmsAssociation smsAssociation) {
        return smsAssociationRepository.save(smsAssociation);
    }

    public SmsAssociation updateSmsAssociation(Long id, SmsAssociation updatedSmsAssociation) {
        Optional<SmsAssociation> existingAssociationOptional = smsAssociationRepository.findById(id);

        if (existingAssociationOptional.isPresent()) {
            SmsAssociation existingAssociation = existingAssociationOptional.get();
            existingAssociation.setAgeLimit(updatedSmsAssociation.getAgeLimit());
            existingAssociation.setSmsCondition(updatedSmsAssociation.getSmsCondition());
            return smsAssociationRepository.save(existingAssociation);
        } else {
            return null;
        }
    }

    public boolean deleteSmsAssociation(Long id) {
        Optional<SmsAssociation> associationOptional = smsAssociationRepository.findById(id);

        if (associationOptional.isPresent()) {
            smsAssociationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
