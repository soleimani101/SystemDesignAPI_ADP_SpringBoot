package com.system.contact.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "system_tbl")
public class System_Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "systemid", fetch = FetchType.LAZY)
    private List<PhoneBook> phoneBookIdList;


    @Column(name = "description")
    private String description;

    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeCreated;


    @Column(name = "LastTimeEdited")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastTimeEdited;


    // Default constructor
    public System_Class() {
    }

    // Parameterized constructor
    public System_Class(String name, String description, LocalDateTime timeCreated, LocalDateTime lastTimeEdited) {
        this.name = name;
        this.description = description;
        this.timeCreated = timeCreated;
        this.lastTimeEdited = lastTimeEdited;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<PhoneBook> getPhoneBookList() {
        return phoneBookIdList;
    }

    public void setPhoneBookList(List<PhoneBook> phoneBookIdList) {
        this.phoneBookIdList = phoneBookIdList;
    }


}