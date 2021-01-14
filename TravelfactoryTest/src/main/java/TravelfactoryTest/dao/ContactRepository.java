package TravelfactoryTest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import TravelfactoryTest.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, String>{

}
