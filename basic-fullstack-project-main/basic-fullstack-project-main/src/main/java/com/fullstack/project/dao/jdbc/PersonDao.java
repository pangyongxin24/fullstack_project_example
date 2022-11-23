package com.fullstack.project.dao.jdbc;

import java.util.List;

import com.fullstack.project.model.Person;

/**
 * Provides CRUD operations for the person table.
 *
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 */
public interface PersonDao {
    public void insert(Person person);

    public Person findById(int idPerson);

    public List<Person> findAll();

    public void update(Person person);

    public void delete(Person person);
}
