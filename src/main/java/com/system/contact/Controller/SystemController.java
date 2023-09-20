package com.system.contact.Controller;

import com.system.contact.DTO.SystemDTO;
import com.system.contact.Service.SystemService;
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
    public ResponseEntity<List<SystemDTO>> listSystems() {
        try {
            List<SystemDTO> systems = systemService.getAllSystems();
            return ResponseEntity.ok(systems);
        } catch (Exception e) {
            String errorMessage = "An error occurred while listing systems: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemDTO> viewSystem(@PathVariable Long id, Model model) {
        try {
            SystemDTO systemDTO = systemService.getSystemById(id);
            return ResponseEntity.ok(systemDTO);
        } catch (Exception e) {
            String errorMessage = "An error occurred while viewing the system: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createSystem(@ModelAttribute("system") SystemDTO systemDTO, BindingResult bindingResult) {
        try {
            systemService.createSystem(systemDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Added Successfully"  );
        } catch (Exception e) {
            String errorMessage = "An error occurred while creating the system: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }




//    @PutMapping
//    public ResponseEntity<String> editSystem(@ModelAttribute("system") System_Class system, @PathVariable("id") Long id, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            // Handle validation errors
//            String errorMessage = "Validation errors occurred: " + bindingResult.getFieldErrors();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
//        }
//
//        try {
//            System_Class existingSystem = systemService.getSystemById(id);
//            if (existingSystem == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("System not found");
//            }
//
//            LocalDateTime localDateTime = LocalDateTime.now();
//            existingSystem.setLastTimeEdited(localDateTime);
//            systemService.updateSystem(system);
//
//            return ResponseEntity.status(HttpStatus.CREATED).body("Edited Successfully");
//        } catch (Exception e) {
//            String errorMessage = "An error occurred while editing the system: " + e.getMessage();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
//        }
//    }
//
//
//
//
@DeleteMapping("/{name}/deletebyName")
public ResponseEntity<String> deleteSystemByName(@PathVariable String name) {
    try {
        systemService.deleteSystemByName(name);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully by id");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred while deleting the system with ID " + name + ": " + e.getMessage());
    }
}


    @DeleteMapping("/{id}/deletebyid")
    public ResponseEntity<String> deleteSystem(@PathVariable Long id) {
        try {
            systemService.deleteSystem(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully by id");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the system with ID " + id + ": " + e.getMessage());
        }
    }


    @DeleteMapping("/all/delete")
    public ResponseEntity<String> deleteWholeSystems() {
        try {
            systemService.deleteAllSystems();
            return ResponseEntity.status(HttpStatus.OK).body("All systems deleted successfully");
        } catch (Exception e) {
            String errorMessage = "An error occurred while deleting systems: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }



}