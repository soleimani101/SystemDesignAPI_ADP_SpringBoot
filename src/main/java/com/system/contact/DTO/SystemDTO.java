package com.system.contact.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class SystemDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime timeCreated;
    private LocalDateTime lastTimeEdited;
    private List<Long> ListIdPhoneBook;





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

    public List<Long> getListIdPhoneBook() {
        return ListIdPhoneBook;
    }

    public void setListIdPhoneBook(List<Long> listIdPhoneBook) {
        ListIdPhoneBook = listIdPhoneBook;
    }
}
