package com.system.contact.Controller;

import com.system.contact.DTO.PhoneBookDTO;
import com.system.contact.Model.PhoneBook;
import com.system.contact.Service.PhoneBookService;
import com.system.contact.Service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.system.contact.Service.PhoneBookService.toDTO;

@RestController
@RequestMapping("/phonebooks")
public class PhoneBookController {

    private final PhoneBookService phoneBookService;

    @Autowired
    public PhoneBookController(PhoneBookService phoneBookService, SystemService systemService) {
        this.phoneBookService = phoneBookService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<PhoneBookDTO>> listPhoneBook() {
        List<PhoneBookDTO> phoneBooks = phoneBookService.getAllPhoneBook();
        return ResponseEntity.ok(phoneBooks);
    }


    @GetMapping("/{id}")
    public PhoneBookDTO viewPhoneBook(@PathVariable Long id, Model model) {
        PhoneBookDTO phoneBookDTO = phoneBookService.getPhoneBookById(id);
        return phoneBookDTO;
    }


    @PostMapping("/{id}/new")
    public ResponseEntity<String> createPhoneBook(@PathVariable("id") Long id, @ModelAttribute("Phonebook") PhoneBook phoneBook) {

        PhoneBookDTO phoneBookDTO = toDTO(phoneBook,id);
        phoneBookService.createPhoneBook(phoneBookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added Successfully");
    }


    @PostMapping("/{id}/edit")
    public String editPhoneBook(@ModelAttribute("phonebook") PhoneBook phoneBook) {
        phoneBookService.updatePhoneBook(phoneBook);
        return "edited Successfully";
    }




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
