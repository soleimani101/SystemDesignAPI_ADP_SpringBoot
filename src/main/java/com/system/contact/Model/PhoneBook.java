package com.system.contact.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PhoneBook")
public class PhoneBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = System_Class.class)
    private System_Class systemid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phonebook", fetch = FetchType.LAZY)
    private List<Contact> contactList;



    @OneToMany(mappedBy = "phoneBook", cascade = CascadeType.ALL)
    private List<SmsAssociation> smsAssociations;

    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeCreated;

    @Column(name = "LastTimeEdited")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastTimeEdited;

    public PhoneBook() {
        // Default constructor
    }

    public PhoneBook(String name, String description, System_Class systemid, LocalDateTime timeCreated, LocalDateTime lastTimeEdited) {
        this.name = name;
        this.description = description;
        this.systemid = systemid;
        this.timeCreated = timeCreated;
        this.lastTimeEdited = lastTimeEdited;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }



    public List<SmsAssociation> getSmsAssociations() {
        return smsAssociations;
    }

    public void setSmsAssociations(List<SmsAssociation> smsAssociations) {
        this.smsAssociations = smsAssociations;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getLastTimeEdited() {
        return lastTimeEdited;
    }

    public void setLastTimeEdited(LocalDateTime lastTimeEdited) {
        this.lastTimeEdited = lastTimeEdited;
    }

    public void addSmsAssociation(SmsAssociation smsAssociation) {
        if (smsAssociations == null) {
            smsAssociations = new ArrayList<>();
        }
        smsAssociations.add(smsAssociation);
        smsAssociation.setPhoneBook(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public System_Class getSystemid() {
        return systemid;
    }

    public void setSystemid(System_Class systemid) {
        this.systemid = systemid;
    }

    // Getter and Setter methods for other fields...
}
