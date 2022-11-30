package com.fullstack.project.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fullstack.project.dao.jdbc.PersonDao;
import com.fullstack.project.exceptions.Exceptions.DbException;
import com.fullstack.project.model.Person;
import com.fullstack.project.model.Country;

public class PersonDaoImpl implements PersonDao {
    private Connection conn;

    public PersonDaoImpl(Connection con) {
	this.conn = con;
    }

    @Override
    public void insert(Person person) {
	final String sql = "insert into person(name, lastname, age, country) values(?,?,?,?)";
	try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	    // Set the parameters for the insert.
	    pstmt.setString(1, person.getName());
	    pstmt.setString(2, person.getLastName());
	    pstmt.setInt(3, person.getAge());
	    pstmt.setInt(4, person.getCountry().getId());

	    pstmt.executeUpdate();

	    // Get the generated primary key.
	    try (ResultSet rs = pstmt.getGeneratedKeys()) {
		if (rs.next()) {
		    int id = rs.getInt(1);
		    person.setId(id);
		}
	    } catch (SQLException ex) {
		throw new DbException(ex);
	    }
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
    }

    @Override
    public Person findById(int idPerson) {
	final String sql = "select id_person, name from person where id_person = ?";
	try (PreparedStatement ps = conn.prepareStatement(sql)) {

	    // Set the parameters for the query.
	    ps.setInt(1, idPerson);

	    // Process the results
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		    Person person = new Person();
		    person.setId(rs.getInt(1));
		    person.setName(rs.getString(2));
		    person.setLastName(rs.getString(3));

		    return person;
		}
	    } catch (SQLException ex) {
		throw new DbException(ex);
	    }
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
	return null;
    }

    @Override
    public List<Person> findAll() {
	List<Person> people = new ArrayList<>();
	final String sql = "select id_person, name from person";
	try (PreparedStatement ps = conn.prepareStatement(sql)) {

	    // Process the results
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		    Person person = new Person();
		    person.setId(rs.getInt(1));
		    person.setName(rs.getString(2));
		    person.setLastName(rs.getString(3));
		    person.setAge(rs.getInt(4));
		    Country country = new Country();
	    	country.setId(rs.getInt(5));
		    person.setCountry(country);

		    people.add(person);
		}
	    } catch (SQLException ex) {
		throw new DbException(ex);
	    }
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
	return people;
    }

    @Override
    public void update(Person person) {
	final String sql = "update person set name= ? set last name= ? set age= ? set country= ? where id_person = ?";
	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    // Set the parameters for the update.
	    pstmt.setString(1, person.getName());
	    pstmt.setString(2, person.getLastName());
	    pstmt.setInt(3, person.getAge());
	    pstmt.setInt(4, person.getCountry().getId());
	    pstmt.setInt(5, person.getId());

	    pstmt.executeUpdate();
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
    }

    @Override
    public void delete(Person person) {
	final String sql = "delete from person where id_person = ?";
	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    // Set the parameters for the delete.
	    pstmt.setInt(1, person.getId());

	    pstmt.executeUpdate();
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
    }
}
