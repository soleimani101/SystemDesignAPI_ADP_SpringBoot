package com.system.contact.DTO;

public class SmsAssociationDTO {


    private Long phoneBookId;
    private int ageLimit;
    private String smsCondition;



    public SmsAssociationDTO() {
    }

    public SmsAssociationDTO( Long phoneBookId, int ageLimit, String smsCondition) {
        this.phoneBookId = phoneBookId;
        this.ageLimit = ageLimit;
        this.smsCondition = smsCondition;
    }

    public Long getPhoneBookId() {
        return phoneBookId;
    }

    public void setPhoneBookId(Long phoneBookId) {
        this.phoneBookId = phoneBookId;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getSmsCondition() {
        return smsCondition;
    }

    public void setSmsCondition(String smsCondition) {
        this.smsCondition = smsCondition;
    }


}
