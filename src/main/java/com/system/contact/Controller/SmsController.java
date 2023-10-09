package com.system.contact.Controller;

import com.system.contact.DTO.SmsDTO;
import com.system.contact.DTO.SmsResponseDTO;
import com.system.contact.Service.SmsService;
import com.system.contact.exception.CustomException;
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
    public ResponseEntity<List<SmsDTO>> getAllSms() throws CustomException {
        List<SmsDTO> smsListDto = smsService.getAllSms();
        return ResponseEntity.ok(smsListDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SmsResponseDTO> getSmsById(@PathVariable Long id) throws CustomException {
        SmsResponseDTO smsDTO = smsService.getSmsById(id);
        return ResponseEntity.ok(smsDTO);
    }


    @PostMapping
    public ResponseEntity<String> createSms(@RequestBody SmsDTO smsDTO) throws CustomException {
        smsService.createSms(smsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added Successfully");

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSms(@PathVariable Long id) throws CustomException {
        smsService.deleteSms(id);
        return ResponseEntity.status(HttpStatus.OK).body(" system" + id +  "deleted successfully");
    }


    @DeleteMapping("/all")
    public ResponseEntity<String> deleteWholeSms() throws CustomException {
        smsService.deleteSmsAll();
        return ResponseEntity.status(HttpStatus.OK).body("All systems deleted successfully");

    }


}
