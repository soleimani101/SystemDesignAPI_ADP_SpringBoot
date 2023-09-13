
package com.system.contact.Controller;

import com.system.contact.DTO.ContactDTO;
import com.system.contact.Model.Contact;
import com.system.contact.Service.ContactService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @GetMapping("/list")
    public List<ContactDTO> listContacts() {
        List<ContactDTO> contactlist = contactService.getAllContacts();
        return contactlist;
    }

    @GetMapping("/{id}")
    public ContactDTO viewContacts(@PathVariable Long id, Model model) {
        ContactDTO contactDTO = contactService.getContactById(id);
        return contactDTO;
    }


    @PostMapping("/{id}/new")
    public String createContact(@ModelAttribute("contact") Contact contact,@PathVariable("id") Long id) {

        ContactDTO contactDTO = ContactService.toDTO(contact,id);
        contactService.createContact(contactDTO);
        return "added Successfully";
    }


    @PostMapping("/{id}/edit")
    public String editContact(@ModelAttribute("contact") Contact contact) {
        contactService.updateContact(contact);
        return "edited Successfully";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "deleted Successfully";
    }
}
