package com.system.contact.Service;

import com.system.contact.Model.System_Class;
import com.system.contact.Repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemService {

    private final SystemRepository systemRepository;
    private SystemService systemService;

    @Autowired
    public SystemService(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }


    public List<System_Class> getAllSystems() {
        return systemRepository.findAll();
    }

    public System_Class getSystemById(Integer id) {
        Optional<System_Class> system = systemRepository.findById(id);
        return system.orElse(null);
    }

    public System_Class getSystemIdByName(String name) {
        Optional<System_Class> system = systemRepository.findByName(name);
        return system.orElse(null);
    }

    public void createSystem(System_Class system) {
        systemRepository.save(system);
    }


    public void updateSystem(System_Class system) {
        systemRepository.save(system);
    }

    public void deleteSystem(Integer id) {
        systemRepository.deleteById(id);
    }

    public void deleteAllSystems() {
        systemRepository.deleteAll();
    }
}


