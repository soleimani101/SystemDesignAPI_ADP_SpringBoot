package com.system.contact.Controller;

import com.system.contact.Model.System_Class;
import com.system.contact.Service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/systems")
public class SystemController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<System_Class> listSystems() {
        List<System_Class> systems = systemService.getAllSystems();
        return systems;
    }

    @GetMapping("/{id}")
    public System_Class viewSystem(@PathVariable Integer id, Model model) {
        System_Class system = systemService.getSystemById(id);
        model.addAttribute("system", system);
        return system;
    }


    @PostMapping("/new")
    public String createSystem(@ModelAttribute("system") System_Class system) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
            system.setTimeCreated(localDateTime);
            system.setLastTimeEdited(localDateTime);
        systemService.createSystem(system);
        return "added Successfully";
    }





    @PostMapping("/{id}/edit")
    public String editSystem(@ModelAttribute("system") System_Class system,  @PathVariable("id") int id  ) {
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









    @DeleteMapping("/{id}/delete")
    public String deleteSystem(@PathVariable Integer id) {
        systemService.deleteSystem(id);
        return "deleted Successfully";
    }


    @DeleteMapping("/all/delete")
    public String deleteWholeSystems() {
        systemService.deleteAllSystems();
        return "all deleted Successfully";
    }
}