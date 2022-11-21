package com.fullstack.project.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fullstack.project.dao.jdbc.CountryDao;
import com.fullstack.project.exceptions.Exceptions.DbException;
import com.fullstack.project.model.Country;

public class CountryDaoImpl implements CountryDao {
    private Connection conn;

    public CountryDaoImpl(Connection con) {
	this.conn = con;
    }

    @Override
    public void insert(Country country) {
	final String sql = "insert into country(name) values(?)";
	try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	    // Set the parameters for the insert.
	    pstmt.setString(1, country.getName());

	    pstmt.executeUpdate();

	    // Get the generated primary key.
	    try (ResultSet rs = pstmt.getGeneratedKeys()) {
		if (rs.next()) {
		    int id = rs.getInt(1);
		    country.setId(id);
		}
	    } catch (SQLException ex) {
		throw new DbException(ex);
	    }
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
    }

    @Override
    public Country findById(int idCountry) {
	final String sql = "select id_country, name from country where id_country = ?";
	try (PreparedStatement ps = conn.prepareStatement(sql)) {

	    // Set the parameters for the query.
	    ps.setInt(1, idCountry);

	    // Process the results
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		    Country country = new Country();
		    country.setId(rs.getInt(1));
		    country.setName(rs.getString(2));

		    return country;
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
    public List<Country> findAll() {
	List<Country> countries = new ArrayList<>();
	final String sql = "select id_country, name from country";
	try (PreparedStatement ps = conn.prepareStatement(sql)) {

	    // Process the results
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		    Country country = new Country();
		    country.setId(rs.getInt(1));
		    country.setName(rs.getString(2));

		    countries.add(country);
		}
	    } catch (SQLException ex) {
		throw new DbException(ex);
	    }
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}

	return countries;
    }

    @Override
    public void update(Country country) {
	final String sql = "update country set name= ? where id_country = ?";
	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    // Set the parameters for the update.
	    pstmt.setString(1, country.getName());
	    pstmt.setInt(2, country.getId());

	    pstmt.executeUpdate();
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
    }

    @Override
    public void delete(Country country) {
	final String sql = "delete from country where id_country = ?";
	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    // Set the parameters for the delete.
	    pstmt.setInt(1, country.getId());

	    pstmt.executeUpdate();
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
    }
}
