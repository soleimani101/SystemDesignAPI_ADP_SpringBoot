package com.system.contact.Controller;

import com.system.contact.Model.PhoneBook;
import com.system.contact.Model.System_Class;
import com.system.contact.Service.PhoneBookService;
import com.system.contact.Service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/phonebooks")
public class PhoneBookController {

    private final PhoneBookService phoneBookService;
    private SystemService systemService;

    @Autowired
    public PhoneBookController(PhoneBookService phoneBookService,SystemService systemService ) {
        this.phoneBookService = phoneBookService;
        this.systemService = systemService;
    }


    @GetMapping("/list")
    @ResponseBody
    public List<PhoneBook> listPhoneBook() {
        List<PhoneBook> systems = phoneBookService.getAllPhoneBook();
        return systems;
    }

    @GetMapping("/{id}")
    public PhoneBook viewPhoneBook(@PathVariable Integer id, Model model) {
        PhoneBook system = phoneBookService.getPhoneBookById(id);
        model.addAttribute("phonebook", system);
        return system;
    }


//    @PostMapping("/{Refference_name}/new")
//    public String createPhoneBook(@ModelAttribute("phonebook") PhoneBook phoneBook, @PathVariable String Refference_name) {
//        System_Class system = systemService.getSystemByName(Refference_name);
//
//        LocalDateTime localDateTime = LocalDateTime.now();
//
//
//
//        system.addtoPhoneBookList(phoneBook);
//        systemService.updateSystem(system);
//        phoneBook.setSystem(system);
//        phoneBook.setTimeCreated(localDateTime);
//        phoneBook.setLastTimeEdited(localDateTime);
//        phoneBookService.createPhoneBook(phoneBook);
//        return "added Successfully";
//

    @PostMapping("/{id}/new")
    public String createPhoneBook(@PathVariable("id") int id, @ModelAttribute("Phonebook") PhoneBook phoneBook) {

//        system.addtoPhoneBookList(phoneBook);
//        systemService.updateSystem(system);
//        phoneBook.setSystem(system);
     System_Class system = systemService.getSystemById(id);

        LocalDateTime localDateTime = LocalDateTime.now();
        phoneBook.setTimeCreated(localDateTime);
        phoneBook.setLastTimeEdited(localDateTime);
        System.out.println(system);

        phoneBook.setSystem(system);
        phoneBookService.createPhoneBook(phoneBook);
        return "added Successfully";
    }


    @PostMapping("/{id}/edit")
    public String editPhoneBook(@ModelAttribute("phonebook") PhoneBook phoneBook) {
        phoneBookService.updatePhoneBook(phoneBook);
        return "edited Successfully";
    }

    @DeleteMapping("/{id}/delete")
    public String deletePhoneBook(@PathVariable Integer id) {
        phoneBookService.deletePhoneBook(id);
        return "deleted Successfully";
    }


    @DeleteMapping("/all/delete")
    public String deleteWholeSystems() {
        phoneBookService.deleteAllSystems();
        return "all deleted Successfully";
    }

}
