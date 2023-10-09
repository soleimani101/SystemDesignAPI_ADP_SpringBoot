package com.system.contact.Controller;

import com.system.contact.DTO.SystemDTO;
import com.system.contact.Service.SystemService;
import com.system.contact.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/systems")
public class SystemController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }


    @GetMapping
    public ResponseEntity<List<SystemDTO>> listSystems() throws CustomException {

            List<SystemDTO> systems = systemService.getAllSystems();
            return ResponseEntity.ok(systems);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SystemDTO> viewSystem(@PathVariable Long id, Model model)throws CustomException {
            SystemDTO systemDTO = systemService.getSystemById(id);
            return ResponseEntity.ok(systemDTO);
    }

    @PostMapping
    public ResponseEntity<String> createSystem(@ModelAttribute("system") SystemDTO systemDTO, BindingResult bindingResult)throws CustomException {
            systemService.createSystem(systemDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Added Successfully");

    }


//@DeleteMapping("/deletebyName/{name}")
//public ResponseEntity<String> deleteSystemByName(@PathVariable String name)throws CustomException {
//        systemService.de(name);
//        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully by id");
//}


    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity deleteSystem(@PathVariable Long id) throws CustomException {
        systemService.deleteSystem(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully by id");
    }


    @DeleteMapping("/all/delete")
    public ResponseEntity<String> deleteWholeSystems() throws CustomException{
            systemService.deleteAllSystems();
            return ResponseEntity.status(HttpStatus.OK).body("All systems deleted successfully");

    }



}