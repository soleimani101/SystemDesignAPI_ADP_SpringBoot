
package com.system.contact.Service;

import com.system.contact.Model.Contact;
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

    public Contact getContactById(Integer id) {
        Optional<Contact> system = contactRepository.findById(id);
        return system.orElse(null);
    }

    public void createContact(Contact contact) {
        contactRepository.save(contact);
    }


    public void updateContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void deleteContact(Integer id) {
        contactRepository.deleteById(id);
    }
}
