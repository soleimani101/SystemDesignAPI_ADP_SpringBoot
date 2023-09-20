package com.system.contact.Service;

import com.system.contact.DTO.SystemDTO;
import com.system.contact.Model.System_Class;
import com.system.contact.Repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

    public SystemDTO getSystemById(Long id) {
        System_Class system = systemRepository.findById(id).get();
        SystemDTO systemDTO  = SystemtoDTO(system) ; 
        return systemDTO;
    }

    public SystemDTO getSystemIdByName(String name) {
        System_Class system = systemRepository.findByName(name).get();
        SystemDTO systemDTO  = SystemtoDTO(system) ;
        return  systemDTO ; 
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

    @Transactional
    public void deleteSystemByName(String name) {
        try {
            systemRepository.deleteDistinctByName(name);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteAllSystems() {
        systemRepository.deleteAll();
    }

    public static SystemDTO SystemtoDTO(System_Class system) {
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


