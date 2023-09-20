package com.system.contact.Controller;

import com.system.contact.DTO.PhoneBookDTO;
import com.system.contact.Service.PhoneBookService;
import com.system.contact.Service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phonebooks")
public class PhoneBookController {

    private final PhoneBookService phoneBookService;

    @Autowired
    public PhoneBookController(PhoneBookService phoneBookService, SystemService systemService) {
        this.phoneBookService = phoneBookService;
    }



    @GetMapping
    public ResponseEntity<List<PhoneBookDTO>> listPhoneBook() {
        try {
            List<PhoneBookDTO> phoneBooks = phoneBookService.getAllPhoneBook();
            return ResponseEntity.ok(phoneBooks);
        } catch (Exception e) {
            String errorMessage = "An error occurred while listing phone books: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @GetMapping("/{id}")
    public PhoneBookDTO viewPhoneBook(@PathVariable Long id) {
        PhoneBookDTO phoneBookDTO = phoneBookService.getPhoneBookById(id);
        return phoneBookDTO;
    }



    @PostMapping
    public ResponseEntity<String> createPhoneBook( @ModelAttribute("Phonebook") PhoneBookDTO phoneBookDTO) {
        phoneBookService.createPhoneBook(phoneBookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added Successfully");
    }

//
//    @PostMapping("/{id}/edit")
//    public String editPhoneBook(@ModelAttribute("phonebook") PhoneBook phoneBook) {
//        phoneBookService.updatePhoneBook(phoneBook);
//        return "edited Successfully";
//    }
//



    @DeleteMapping("/{id}/delete")
    public String deletePhoneBook(@PathVariable Long id) {
        phoneBookService.deletePhoneBook(id);
        return "deleted Successfully";
    }


    @DeleteMapping("/all/delete")
    public String deleteWholeSystems() {
        phoneBookService.deleteAllSystems();
        return "all deleted Successfully";
    }

}
