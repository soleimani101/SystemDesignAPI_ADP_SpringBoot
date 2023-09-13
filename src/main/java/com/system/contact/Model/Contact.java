package com.system.contact.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "family")
    private String family;

    @Column(name = "age")
    private String age;

    @Column(name = "phonenumber")
    private String phoneNumber;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PhoneBook.class)
    private PhoneBook phonebook;

    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeCreated;

    @Column(name = "LastTimeEdited")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastTimeEdited;

    // Default constructor
    public Contact() {
    }

    public PhoneBook getPhonebook() {
        return phonebook;
    }

    public void setPhonebook(PhoneBook phonebook) {
        this.phonebook = phonebook;
    }

    // Parameterized constructor
    public Contact(String name, String family, String age, String phoneNumber, PhoneBook phoneBook, LocalDateTime timeCreated, LocalDateTime lastTimeEdited) {
        this.name = name;
        this.family = family;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.phonebook = phoneBook;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
