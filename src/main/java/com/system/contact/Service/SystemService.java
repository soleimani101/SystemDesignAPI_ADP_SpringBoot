package com.system.contact.Service;

import com.system.contact.DTO.SystemDTO;
import com.system.contact.Model.System_Class;
import com.system.contact.Repository.SystemRepository;
import com.system.contact.exception.CustomException;
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

    public List<SystemDTO> getAllSystems() throws CustomException {
        try {
            return systemRepository.findAll().stream()
                    .map(this::SystemtoDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("An error occurred while getting systems: ", e.getMessage(), 500);
        }
    }


    public SystemDTO getSystemById(Long id) throws CustomException {
        Optional<System_Class> optionalSystem = systemRepository.findById(id);

        if (optionalSystem.isPresent()) {
            System_Class system = optionalSystem.get();
            return SystemtoDTO(system);
        } else {
            throw new CustomException("An error occurred while getting systems: ", "e.getMessage()", 500);
        }
    }

    public SystemDTO getSystemIdByName(String name) throws CustomException {
        Optional<System_Class> optionalSystem = systemRepository.findByName(name);

        if (optionalSystem.isPresent()) {
            System_Class system = optionalSystem.get();
            return SystemtoDTO(system);
        } else {
            throw new CustomException("System with name " + name + " not found", " not found", 500);
        }
    }

    public void createSystem(SystemDTO systemDTO) throws CustomException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            systemDTO.setTimeCreated(localDateTime);
            systemDTO.setLastTimeEdited(localDateTime);
            System_Class system_class = SystemtoEntity(systemDTO);
            systemRepository.save(system_class);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), "Internal server error", 500);
        }
    }

    public void deleteSystem(Long id) throws CustomException {
        Optional<System_Class> systemOptional = systemRepository.findById(id);
        if (systemOptional.isPresent()) {
            systemRepository.deleteById(id);
        } else {
            throw new CustomException("System with ID not found" + id, "not found", 500);
        }
    }


    public void deleteAllSystems() throws CustomException {
        try {
            systemRepository.deleteAll();
        } catch (Exception ex) {
            throw new CustomException("An error occurred while deleting all systems", "Internal Error", 500);
        }
    }

    public SystemDTO SystemtoDTO(System_Class system) {
        SystemDTO systemDTO = new SystemDTO();
        systemDTO.setId(system.getId());
        systemDTO.setName(system.getName());
        systemDTO.setDescription(system.getDescription());

        systemDTO.setTimeCreated(system.getTimeCreated());
        systemDTO.setLastTimeEdited(system.getLastTimeEdited());
        return systemDTO;
    }

    private System_Class SystemtoEntity(SystemDTO systemDTO) {
        System_Class system = new System_Class();
        system.setName(systemDTO.getName());
        system.setDescription(systemDTO.getDescription());
        system.setTimeCreated(systemDTO.getTimeCreated());
        system.setLastTimeEdited(systemDTO.getLastTimeEdited());
        return system;
    }
}
