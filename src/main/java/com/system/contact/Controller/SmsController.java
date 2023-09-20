package com.system.contact.Controller;

import com.system.contact.DTO.SmsDTO;
import com.system.contact.Service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;


    @GetMapping
    public ResponseEntity<List<SmsDTO>> getAllSms() {
        try {
            List<SmsDTO> smsListDto = smsService.getAllSms();
            return ResponseEntity.ok(smsListDto);
        } catch (Exception e) {
            String errorMessage = "An error occurred while listing Sms: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @GetMapping("/{id}")
    public SmsDTO getSmsById(@PathVariable Long id) {
        SmsDTO smsDTO = smsService.getSmsById(id);
        return smsDTO ;
    }

    @PostMapping
    public ResponseEntity<String> createSms(@RequestBody SmsDTO smsDTO) {
        try {
            smsService.createSms(smsDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Added Successfully"  );
        } catch (Exception e) {
            String errorMessage = "An error occurred while creating the system: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Sms> updateSms(@PathVariable Long id, @RequestBody Sms sms) {
//        Sms updatedSms = smsService.updateSms(id, sms);
//        if (updatedSms == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(updatedSms, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSms(@PathVariable Long id) {
        smsService.deleteSms(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





    @DeleteMapping("/all")
    public ResponseEntity<String> deleteWholeSms() {
        try {
            smsService.deleteSmsAll();
            return ResponseEntity.status(HttpStatus.OK).body("All systems deleted successfully");
        } catch (Exception e) {
            String errorMessage = "An error occurred while deleting systems: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


}
