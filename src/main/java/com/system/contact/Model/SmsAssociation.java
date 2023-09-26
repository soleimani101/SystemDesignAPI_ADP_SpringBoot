package com.system.contact.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "sms_phonebook_association")
public class SmsAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sms_id")
    private Sms sms;

    @ManyToOne
    @JoinColumn(name = "phonebook_id")
    private PhoneBook phoneBook;

    @Column(name = "age_limit")
    private int ageLimit;

    @Column(name = "sms_condition")
    private String smsCondition;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sms getSms() {
        return sms;
    }

    public void setSms(Sms sms) {
        this.sms = sms;
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
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

    public SmsAssociation() {
    }

    public SmsAssociation(Sms sms, PhoneBook phoneBook, int ageLimit, String smsCondition) {
        this.sms = sms;
        this.phoneBook = phoneBook;
        this.ageLimit = ageLimit;
        this.smsCondition = smsCondition;
    }

    // Getters and setters
}
