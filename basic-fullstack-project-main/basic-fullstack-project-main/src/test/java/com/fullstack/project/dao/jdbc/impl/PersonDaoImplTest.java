package com.fullstack.project.dao.jdbc.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.fullstack.project.dao.jdbc.PersonDao;
import com.fullstack.project.dao.jdbc.DbConnection;
import com.fullstack.project.model.Person;

/**
 * Test cases for the {@code DbConnection} class.
 */
public class PersonDaoImplTest {

    @Test
    public void insert() throws SQLException {
	try (Connection conn = DbConnection.getConnection()) {
	    PersonDao personDao = new PersonDaoImpl(conn);

	    Person person = new Person();
	    person.setName("Serena");
	    person.setLastName("P");

	    personDao.insert(person);

	    // Check expectations.
	    // Check that the ID was set.
	    assertNotEquals(person.getId(), 0);
	}
    }

    @Test
    public void findById() throws SQLException {
	try (Connection conn = DbConnection.getConnection()) {
	    PersonDao personDao = new PersonDaoImpl(conn);
	    Person person1 = new Person();
	    person1.setName("Serena");
	    person1.setLastName("P");
	    personDao.insert(person1);
	    int idPerson = person1.getId();

	    Person person2 = personDao.findById(idPerson);

	    // Check expectations.
	    // Check that the country was found.
	    assertNotNull(person1);
	    assertEquals(idPerson, person2.getId());
	}
    }

    @Test
    public void findAll() throws SQLException {
	try (Connection conn = DbConnection.getConnection()) {
	    PersonDao personDao = new PersonDaoImpl(conn);

	    List<Person> people = personDao.findAll();

	    // Check expectations.
	    // Check that the list is not null and that there are results.
	    assertNotNull(people);
	    assertTrue(people.size() > 0);
	}
    }

    @Test
    public void update() throws SQLException {
	try (Connection conn = DbConnection.getConnection()) {
	    PersonDao personDao = new PersonDaoImpl(conn);

	    Person person = new Person();
	    person.setName("helloSerena");
	    person.setLastName("P");

	    personDao.insert(person);

	    person.setName("helloSerena" + new Date());
	    person.setLastName("P" + new Date());

	    personDao.update(person);

	    Person current = personDao.findById(person.getId());

	    // Check expectations.
	    assertNotNull(current);
	    assertEquals(person.getId(), current.getId());
	    assertEquals(person.getName(), current.getName());
	}
    }

    @Test
    public void delete() throws SQLException {
	try (Connection conn = DbConnection.getConnection()) {
	    PersonDao personDao = new PersonDaoImpl(conn);

	    Person person = new Person();
	    person.setName("helloJuan");
	    person.setLastName("Carlos");

	    personDao.insert(person);

	    Person current = personDao.findById(person.getId());
	    // Check that the person was inserted.
	    assertNotNull(current);

	    personDao.delete(current);

	    current = personDao.findById(person.getId());

	    // Check that the person was deleted.
	    assertNull(current);
	}
    }
}
