package com.fullstack.project.dao.jdbc;

import java.util.List;

import com.fullstack.project.model.Country;

/**
 * Provides CRUD operations for the country table.
 *
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 */
public interface CountryDao {
    public void insert(Country country);

    public Country findById(int idCountry);

    public List<Country> findAll();

    public void update(Country country);

    public void delete(Country country);
}
