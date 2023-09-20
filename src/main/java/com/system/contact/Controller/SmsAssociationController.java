package com.system.contact.Controller;

import com.system.contact.Model.SmsAssociation;
import com.system.contact.Service.SmsAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms-associations")
public class SmsAssociationController {

    @Autowired
    private SmsAssociationService smsAssociationService;

    @GetMapping
    public ResponseEntity<List<SmsAssociation>> getAllSmsAssociations() {
        List<SmsAssociation> associations = smsAssociationService.getAllSmsAssociations();
        return ResponseEntity.ok(associations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmsAssociation> getSmsAssociationById(@PathVariable Long id) {
        SmsAssociation association = smsAssociationService.getSmsAssociationById(id);
        if (association != null) {
            return ResponseEntity.ok(association);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SmsAssociation> createSmsAssociation(@RequestBody SmsAssociation smsAssociation) {
        SmsAssociation createdAssociation = smsAssociationService.createSmsAssociation(smsAssociation);
        return ResponseEntity.ok(createdAssociation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SmsAssociation> updateSmsAssociation(@PathVariable Long id, @RequestBody SmsAssociation smsAssociation) {
        SmsAssociation updatedAssociation = smsAssociationService.updateSmsAssociation(id, smsAssociation);
        if (updatedAssociation != null) {
            return ResponseEntity.ok(updatedAssociation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSmsAssociation(@PathVariable Long id) {
        boolean deleted = smsAssociationService.deleteSmsAssociation(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
