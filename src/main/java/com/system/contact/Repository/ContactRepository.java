
package com.system.contact.Repository;

import com.system.contact.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {


//    Contact findByName(String name);
//    List<Contact> findByDescription(String description);
//    List<Contact> findByNameAndDescription(String name, String description);
}
