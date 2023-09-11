
package com.system.contact.Controller;

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
    @ResponseBody
    public List<Contact> listContacts() {
        List<Contact> contact = contactService.getAllContacts();
        return contact;
    }

    @GetMapping("/{id}")
    public Contact viewContacts(@PathVariable Integer id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return contact;
    }


    @PostMapping("/new")
    public String createContact(@ModelAttribute("contact") Contact contact) {

        contactService.createContact(contact);
        return "added Successfully";
    }


    @PostMapping("/{id}/edit")
    public String editContact(@ModelAttribute("contact") Contact contact) {
        contactService.updateContact(contact);
        return "edited Successfully";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteContact(@PathVariable Integer id) {
        contactService.deleteContact(id);
        return "deleted Successfully";
    }
}
