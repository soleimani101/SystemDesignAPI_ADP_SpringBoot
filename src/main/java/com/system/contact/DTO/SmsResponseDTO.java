package com.system.contact.DTO;

import java.util.List;
import java.util.Map;

public class SmsResponseDTO {
    private SmsDTO smsDto;
    private List<ContactDTO> contactsFinal;
         private Map<String, String> phoneToNameMapping;


    public SmsDTO getSmsDto() {
        return smsDto;
    }

    public void setSmsDto(SmsDTO smsDto) {
        this.smsDto = smsDto;
    }

    public List<ContactDTO> getContactsFinal() {
        return contactsFinal;
    }

    public void setContactsFinal(List<ContactDTO> contactsFinal) {
        this.contactsFinal = contactsFinal;
    }

    public SmsResponseDTO(SmsDTO smsDto, List<ContactDTO> contactsFinal,Map<String,String> phoneToNameMapping) {
        this.smsDto = smsDto;
        this.contactsFinal = contactsFinal;
        this.phoneToNameMapping = phoneToNameMapping;
    }


    public SmsResponseDTO(SmsDTO smsDto, List<ContactDTO> contactsFinal) {
        this.smsDto = smsDto;
        this.contactsFinal = contactsFinal;
    }

}
