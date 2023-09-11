
package com.system.contact.Repository;

import com.system.contact.Model.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook, Integer> {

    PhoneBook findByName(String name);
    List<PhoneBook> findByDescription(String description);
    List<PhoneBook> findByNameAndDescription(String name, String description);
}
