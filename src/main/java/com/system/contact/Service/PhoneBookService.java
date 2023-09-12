package com.system.contact.Service;

import com.system.contact.DTO.PhoneBookDTO;
import com.system.contact.Model.PhoneBook;
import com.system.contact.Model.System_Class;
import com.system.contact.Repository.PhoneBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneBookService {

    public static PhoneBookRepository phoneBookRepository;
    private SystemService systemService;

    @Autowired
    public PhoneBookService(PhoneBookRepository phoneBookRepository) {
        this.phoneBookRepository =phoneBookRepository ;
    }



    public List<PhoneBookDTO> getAllPhoneBook() {
        return phoneBookRepository.findAll().stream().map(PhoneBookService::toDTO).collect(Collectors.toList());
    }


    public static PhoneBook getPhoneBookById(Long id) {
        Optional<PhoneBook> system = phoneBookRepository.findById(id);
        return system.orElse(null);
    }

    public void createPhoneBook(PhoneBookDTO phoneBookDTO, Long id) {

//        System_Class system_class = systemService.getSystemById(id);
        LocalDateTime localDateTime = LocalDateTime.now();
        phoneBookDTO.setTimeCreated(localDateTime);
        phoneBookDTO.setLastTimeEdited(localDateTime);

//        phoneBookDTO.setSystem_class(system_class);
        PhoneBook phoneBook = toEntity(phoneBookDTO,id);

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

    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }


    public static PhoneBookDTO toDTO(PhoneBook phoneBook) {
        PhoneBookDTO phoneBookDTO = new PhoneBookDTO();
        phoneBookDTO.setId(phoneBook.getId());
        phoneBookDTO.setName(phoneBook.getName());
        phoneBookDTO.setDescription(phoneBook.getDescription());
        phoneBookDTO.setSystemId(phoneBook.getSystem().getId()); // Set the system ID
        phoneBookDTO.setTimeCreated(phoneBook.getTimeCreated());
        phoneBookDTO.setLastTimeEdited(phoneBook.getLastTimeEdited());
        return phoneBookDTO;
    }

    public static PhoneBook toEntity(PhoneBookDTO phoneBookDTO,Long id) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setId(phoneBookDTO.getId());
        phoneBook.setName(phoneBookDTO.getName());
        phoneBook.setDescription(phoneBookDTO.getDescription());
        System_Class system = new System_Class();
        system.setId(id);
        phoneBook.setSystem(system);
        phoneBook.setTimeCreated(phoneBookDTO.getTimeCreated());
        phoneBook.setLastTimeEdited(phoneBookDTO.getLastTimeEdited());
        return phoneBook;
    }

}
