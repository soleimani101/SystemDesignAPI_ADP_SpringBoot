package com.system.contact.Controller;

import com.system.contact.DTO.SystemDTO;
import com.system.contact.Model.System_Class;
import com.system.contact.Service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.system.contact.Service.SystemService.SystemtoDTO;

@RestController
@RequestMapping("/systems")
public class SystemController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<SystemDTO>>  listSystems() {
        List<SystemDTO> systems = systemService.getAllSystems();
        return ResponseEntity.ok(systems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemDTO> viewSystem(@PathVariable Long id, Model model) {
        System_Class system = systemService.getSystemById(id);
        SystemDTO systemDTO = SystemtoDTO(system);

//        model.addAttribute("system", systemDTO);
        return ResponseEntity.ok(systemDTO);
    }



    @PostMapping("/new")
    public ResponseEntity<String> createSystem(@ModelAttribute("system") System_Class system_class) {
        SystemDTO systemDTO = SystemtoDTO(system_class);
        systemService.createSystem(systemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added Successfully");
    }

/// should be fixed
    @PostMapping("/{id}/edit")
    public String editSystem(@ModelAttribute("system") System_Class system,  @PathVariable("id") Long id  ) {
        // Step 1: Fetch the existing entity from the database by its ID
        System_Class existingSystem = systemService.getSystemById(id);
        if (existingSystem == null) {
            return "System not found";
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        existingSystem.setLastTimeEdited(localDateTime);
        systemService.updateSystem(system);
        return "Edited successfully";
    }





    @DeleteMapping("/{name}/deletebyname")
    public String deleteSystemByName(@PathVariable String name) {
        systemService.deleteSystemByName(name);
        return  "deleted Successfully";
    }



    @DeleteMapping("/{id}/delete")
    public String deleteSystem(@PathVariable Long id) {
        systemService.deleteSystem(id);
        return  "deleted Successfully";
    }


    @DeleteMapping("/all/delete")
    public String deleteWholeSystems() {
        systemService.deleteAllSystems();
        return "all deleted Successfully";
    }
}