package com.system.contact.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class SmsDTO {

    private Long id;
    private String body;

    private String sourceNumber;
    private String destinationNumber;
    private LocalDateTime sentDate;
    private List<SmsAssociationDTO> associations;


    // Getters and Setters (omitted for brevity)
    public List<SmsAssociationDTO> getAssociations() {
        return associations;
    }

    public void setAssociations(List<SmsAssociationDTO> associations) {
        this.associations = associations;
    }
    public SmsDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    public String getDestinationNumber() {
        return destinationNumber;
    }

    public void setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }




    public SmsDTO(Long id, String body, String sourceNumber, String destinationNumber,
                  LocalDateTime sentDate, List<SmsAssociationDTO> associations) {
        this.id = id;
        this.body = body;
        this.sourceNumber = sourceNumber;
        this.destinationNumber = destinationNumber;
        this.sentDate = sentDate;
        this.associations = associations;
    }
}
