
package com.system.contact.Controller;

import com.system.contact.DTO.ContactDTO;
import com.system.contact.Service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @GetMapping
    public List<ContactDTO> listContacts() {
        List<ContactDTO> contactlist = contactService.getAllContacts();
        return contactlist;
    }

    @GetMapping("/{id}")
    public ContactDTO viewContacts(@PathVariable Long id) {
        System.out.println(id);
        ContactDTO contactDTO = contactService.getContactById(id);
        return contactDTO;
    }


    @PostMapping
    public ResponseEntity<String> createContact(@ModelAttribute("Contact") ContactDTO contactDTO) {

        contactService.createContact(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added Successfully");
    }







//
//    @PostMapping("/{id}/edit")
//    public String editContact(@ModelAttribute("contact") Contact contact) {
//        contactService.updateContact(contact);
//        return "edited Successfully";
//    }

    @DeleteMapping("/{id}/delete")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "deleted Successfully";
    }
}
