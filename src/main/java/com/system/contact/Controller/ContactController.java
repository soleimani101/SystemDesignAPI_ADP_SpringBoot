
package com.system.contact.Controller;

import com.system.contact.DTO.ContactDTO;
import com.system.contact.Service.ContactService;
import com.system.contact.exception.CustomException;
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
    public ResponseEntity<List<ContactDTO>> listContacts() throws CustomException {
        List<ContactDTO> contactlist = contactService.getAllContacts();
        return ResponseEntity.ok(contactlist) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> viewContacts(@PathVariable Long id) throws CustomException{
        ContactDTO contactDTO = contactService.getContactById(id);
      return   ResponseEntity.ok(contactDTO);
    }


    @PostMapping
    public ResponseEntity<String> createContact(@ModelAttribute("Contact") ContactDTO contactDTO) throws CustomException {
        contactService.createContact(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added Successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) throws CustomException {
        contactService.deleteContact(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted Successfully");
    }
}
