
package com.system.contact.Service;

import com.system.contact.DTO.ContactDTO.ContactDTO;
import com.system.contact.Model.Contact;
import com.system.contact.Model.PhoneBook;
import com.system.contact.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository =contactRepository ;
    }


    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        Optional<Contact> system = contactRepository.findById(id);
        return system.orElse(null);
    }

    public void createContact(Contact contact) {
        contactRepository.save(contact);
    }


    public void updateContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }


    public static ContactDTO toDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setName(contact.getName());
        contactDTO.setFamily(contact.getFamily());
        contactDTO.setAge(contact.getAge());
        contactDTO.setPhoneNumber(contact.getPhoneNumber());
        contactDTO.setPhoneBookId(contact.getPhoneBook().getId()); // Set the phoneBookId
        contactDTO.setTimeCreated(contact.getTimeCreated());
        contactDTO.setLastTimeEdited(contact.getLastTimeEdited());
        return contactDTO;
    }

    // Converts ContactDTO to Contact
    public static Contact toEntity(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setId(contactDTO.getId());
        contact.setName(contactDTO.getName());
        contact.setFamily(contactDTO.getFamily());
        contact.setAge(contactDTO.getAge());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        // Set the associated PhoneBook using its ID
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setId(contactDTO.getPhoneBookId());
        contact.setPhoneBook(phoneBook);
        contact.setTimeCreated(contactDTO.getTimeCreated());
        contact.setLastTimeEdited(contactDTO.getLastTimeEdited());
        return contact;
    }

}
