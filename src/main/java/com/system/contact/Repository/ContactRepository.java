
package com.system.contact.Repository;

import com.system.contact.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByAgeGreaterThanAndPhonebookEquals(int age,String phonebook);
    List<Contact> findByAgeLessThanAndPhonebookEquals(int age,String phonebook);
    List<Contact> findByAgeEqualsAndPhonebookEquals(int age, String phonebook);

}
