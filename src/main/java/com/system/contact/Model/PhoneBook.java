package com.system.contact.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
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


    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phonebook", fetch = FetchType.LAZY)
    private List<Contact> contactList;


    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeCreated;

    @Column(name = "LastTimeEdited")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastTimeEdited;

    // Default constructor
    public PhoneBook() {
    }

    // Parameterized constructor
    public PhoneBook(String name, String description, System_Class systemid, LocalDateTime timeCreated, LocalDateTime lastTimeEdited) {
        this.name = name;
        this.description = description;
        this.systemid = systemid;
        this.timeCreated = timeCreated;
        this.lastTimeEdited = lastTimeEdited;
    }

    // Getter and Setter methods for all fields

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

    public System_Class getSystem() {
        return systemid;
    }

    public void setSystem(System_Class systemid) {
        this.systemid = systemid;
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


}
