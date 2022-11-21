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

import com.fullstack.project.dao.jdbc.CountryDao;
import com.fullstack.project.dao.jdbc.DbConnection;
import com.fullstack.project.model.Country;

/**
 * Test cases for the {@code DbConnection} class.
 */
public class CountryDaoImplTest {

    @Test
    public void insert() throws SQLException {
	try (Connection conn = DbConnection.getConnection()) {
	    CountryDao countryDao = new CountryDaoImpl(conn);

	    Country country = new Country();
	    country.setName("France");

	    countryDao.insert(country);

	    // Check expectations.
	    // Check that the ID was set.
	    assertNotEquals(country.getId(), 0);
	}
    }

    @Test
    public void findById() throws SQLException {
	try (Connection conn = DbConnection.getConnection()) {
	    CountryDao countryDao = new CountryDaoImpl(conn);
	    Country country1 = new Country();
	    country1.setName("China");
	    countryDao.insert(country1);
	    int idCountry = country1.getId();

	    Country country2 = countryDao.findById(idCountry);

	    // Check expectations.
	    // Check that the country was found.
	    assertNotNull(country1);
	    assertEquals(idCountry, country2.getId());
	}
    }

    @Test
    public void findAll() throws SQLException {
	try (Connection conn = DbConnection.getConnection()) {
	    CountryDao countryDao = new CountryDaoImpl(conn);

	    List<Country> countries = countryDao.findAll();

	    // Check expectations.
	    // Check that the list is not null and that there are results.
	    assertNotNull(countries);
	    assertTrue(countries.size() > 0);
	}
    }

    @Test
    public void update() throws SQLException {
	try (Connection conn = DbConnection.getConnection()) {
	    CountryDao countryDao = new CountryDaoImpl(conn);

	    Country country = new Country();
	    country.setName("Singapur");

	    countryDao.insert(country);

	    country.setName("Singapur " + new Date());

	    countryDao.update(country);

	    Country current = countryDao.findById(country.getId());

	    // Check expectations.
	    assertNotNull(current);
	    assertEquals(country.getId(), current.getId());
	    assertEquals(country.getName(), current.getName());
	}
    }

    @Test
    public void delete() throws SQLException {
	try (Connection conn = DbConnection.getConnection()) {
	    CountryDao countryDao = new CountryDaoImpl(conn);

	    Country country = new Country();
	    country.setName("Singapur");

	    countryDao.insert(country);

	    Country current = countryDao.findById(country.getId());
	    // Check that the country was inserted.
	    assertNotNull(current);

	    countryDao.delete(current);

	    current = countryDao.findById(country.getId());

	    // Check that the country was deleted.
	    assertNull(current);
	}
    }
}
