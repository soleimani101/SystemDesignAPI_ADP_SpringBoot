package com.system.contact.Service;

import com.system.contact.Model.PhoneBook;
import com.system.contact.Repository.PhoneBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneBookService {

    private final PhoneBookRepository phoneBookRepository;

    @Autowired
    public PhoneBookService(PhoneBookRepository phoneBookRepository) {
        this.phoneBookRepository =phoneBookRepository ;
    }


    public List<PhoneBook> getAllPhoneBook() {
        return phoneBookRepository.findAll();
    }

    public PhoneBook getPhoneBookById(Integer id) {
        Optional<PhoneBook> system = phoneBookRepository.findById(id);
        return system.orElse(null);
    }

    public void createPhoneBook(PhoneBook phoneBook) {
        phoneBookRepository.save(phoneBook);
    }


    public void updatePhoneBook(PhoneBook phoneBook) {
        phoneBookRepository.save(phoneBook);
    }

    public void deletePhoneBook(Integer id) {
        phoneBookRepository.deleteById(id);
    }


    public void deleteAllSystems() {
        phoneBookRepository.deleteAll();
    }
}
