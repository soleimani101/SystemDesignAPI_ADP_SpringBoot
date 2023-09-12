package com.system.contact.Service;

import com.system.contact.DTO.SystemDTO;
import com.system.contact.Model.PhoneBook;
import com.system.contact.Model.System_Class;
import com.system.contact.Repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SystemService {

    private final SystemRepository systemRepository;

    @Autowired
    public SystemService(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }


    public List<SystemDTO> getAllSystems() {
        return systemRepository.findAll().stream().map(SystemService::SystemtoDTO).collect(Collectors.toList());
    }

    public System_Class getSystemById(Long id) {
        Optional<System_Class> system = systemRepository.findById(id);
        return system.orElse(null);
    }

    public System_Class getSystemIdByName(String name) {
        Optional<System_Class> system = systemRepository.findByName(name);
        return system.orElse(null);
    }

    public void createSystem(SystemDTO systemDTO) {

        LocalDateTime localDateTime = LocalDateTime.now();
        systemDTO.setTimeCreated(localDateTime);
        systemDTO.setLastTimeEdited(localDateTime);
        System_Class system_class = SystemtoEntity(systemDTO);
        systemRepository.save(system_class);

    }


    public void updateSystem(System_Class system) {
        systemRepository.save(system);
    }

    public void deleteSystem(Long id) {
        systemRepository.deleteById(id);
    }

    public void deleteSystemByName(String name) {
        systemRepository.deleteByNameContains(name);
    }

    public void deleteAllSystems() {
        systemRepository.deleteAll();
    }

    public static SystemDTO SystemtoDTO(System_Class system) {
        SystemDTO systemDTO = new SystemDTO();
        systemDTO.setId(system.getId());
        systemDTO.setName(system.getName());
        systemDTO.setDescription(system.getDescription());
        systemDTO.setListIdPhoneBook(system.getPhoneBookList().stream().map(PhoneBook::getId).collect(Collectors.toList()));
        systemDTO.setTimeCreated(system.getTimeCreated());
        systemDTO.setLastTimeEdited(system.getLastTimeEdited());
        return systemDTO;
    }

    private System_Class SystemtoEntity(SystemDTO systemDTO) {
        System_Class system = new System_Class();
        system.setId(systemDTO.getId());
        system.setName(systemDTO.getName());
        system.setPhoneBookList(systemDTO.getListIdPhoneBook().stream().map(PhoneBookService::getPhoneBookById).collect(Collectors.toList()));
        system.setDescription(systemDTO.getDescription());
        system.setTimeCreated(systemDTO.getTimeCreated());
        system.setLastTimeEdited(systemDTO.getLastTimeEdited());
        return system;
    }
}


