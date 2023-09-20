
package com.system.contact.Service;

import com.system.contact.DTO.ContactDTO;
import com.system.contact.Model.Contact;
import com.system.contact.Model.PhoneBook;
import com.system.contact.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository =contactRepository ;
    }



    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(ContactService::toDTO)
                .collect(Collectors.toList());
    }


    public ContactDTO getContactById(Long id) {
        Contact contact = contactRepository.findById(id).get();
        ContactDTO phoneBookDTO =  toDTO(contact) ;
        return phoneBookDTO;
    }

    public void createContact(ContactDTO contactDTO) {

        LocalDateTime localDateTime = LocalDateTime.now();
        contactDTO.setTimeCreated(localDateTime);
        contactDTO.setLastTimeEdited(localDateTime);

        Contact contact = toEntity(contactDTO);
        contactRepository.save(contact);
    }


    public void updateContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }


    public static ContactDTO toDTO(Contact contact ) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setName(contact.getName());
        contactDTO.setFamily(contact.getFamily());
        contactDTO.setAge(contact.getAge());
        contactDTO.setPhoneNumber(contact.getPhoneNumber());
        contactDTO.setPhoneBookId(contact.getPhonebook().getId()); // Set the phoneBookId
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
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.setId(contactDTO.getPhoneBookId());
        contact.setPhonebook(phoneBook);
        contact.setTimeCreated(contactDTO.getTimeCreated());
        contact.setLastTimeEdited(contactDTO.getLastTimeEdited());
        return contact;
    }

}
