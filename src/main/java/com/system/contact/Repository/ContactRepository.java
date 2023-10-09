
package com.system.contact.Repository;

import com.system.contact.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByPhonebookIdAndAgeLessThan(Long phoneBookId,int age);
    List<Contact> findAllByPhonebookIdAndAgeGreaterThan(Long phoneBookId,int age);
    List<Contact> findAllByPhonebookIdAndAgeEquals(Long phoneBookId,int age);

}
