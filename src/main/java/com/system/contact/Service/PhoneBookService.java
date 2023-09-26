package com.system.contact.Service;

import com.system.contact.DTO.PhoneBookDTO;
import com.system.contact.Model.PhoneBook;
import com.system.contact.Model.System_Class;
import com.system.contact.Repository.PhoneBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneBookService {

    public  PhoneBookRepository phoneBookRepository;

    @Autowired
    public PhoneBookService(PhoneBookRepository phoneBookRepository) {
        this.phoneBookRepository = phoneBookRepository;
    }


    public List<PhoneBookDTO> getAllPhoneBook() {
        return phoneBookRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public  PhoneBookDTO getPhoneBookById(Long id) {
        PhoneBook phoneBook = phoneBookRepository.findById(id).get();
        PhoneBookDTO phoneBookDTO = toDTO(phoneBook);
        return phoneBookDTO;
    }

    public void createPhoneBook(PhoneBookDTO phoneBookDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        phoneBookDTO.setTimeCreated(localDateTime);
        phoneBookDTO.setLastTimeEdited(localDateTime);
        PhoneBook phoneBook = toEntity(phoneBookDTO);
        phoneBookRepository.save(phoneBook);
    }


    public void updatePhoneBook(PhoneBook phoneBook) {
        phoneBookRepository.save(phoneBook);
    }

    public void deletePhoneBook(Long id) {
        phoneBookRepository.deleteById(id);
    }


    public void deleteAllSystems() {
        phoneBookRepository.deleteAll();
    }


    private PhoneBookDTO toDTO(PhoneBook phoneBook) {
        PhoneBookDTO phoneBookDTO = new PhoneBookDTO();
        phoneBookDTO.setId(phoneBook.getId());
        phoneBookDTO.setName(phoneBook.getName());
        phoneBookDTO.setDescription(phoneBook.getDescription());
        phoneBookDTO.setSystemId(phoneBook.getSystemid().getId());
        phoneBookDTO.setTimeCreated(phoneBook.getTimeCreated());
        phoneBookDTO.setLastTimeEdited(phoneBook.getLastTimeEdited());
        return phoneBookDTO;
    }

   public PhoneBook toEntity(PhoneBookDTO phoneBookDTO) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setId(phoneBookDTO.getId());
        phoneBook.setName(phoneBookDTO.getName());
        phoneBook.setDescription(phoneBookDTO.getDescription());
        System_Class system = new System_Class();
        system.setId(phoneBookDTO.getId());
        phoneBook.setSystemid(system);
        phoneBook.setTimeCreated(phoneBookDTO.getTimeCreated());
        phoneBook.setLastTimeEdited(phoneBookDTO.getLastTimeEdited());
        return phoneBook;
    }

}
