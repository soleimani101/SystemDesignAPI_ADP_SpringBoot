package com.system.contact.Repository;

import com.system.contact.Model.System_Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemRepository extends JpaRepository<System_Class, Long> {

    Optional<System_Class> findByName(String name);

    List<System_Class> findByDescription(String description);

    List<System_Class> findByNameAndDescription(String name, String description);
    void deleteDistinctByName(String name);
}
