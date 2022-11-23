
package com.fullstack.project;

import com.fullstack.project.dao.jdbc.DbConnection;
import com.fullstack.project.dao.jdbc.impl.CountryDaoImpl;
import com.fullstack.project.dao.jdbc.CountryDao;
import com.fullstack.project.model.Country;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;

public class Main {

	public static void countryFunctionalities(){
		try (Connection conn = DbConnection.getConnection()) {
	        CountryDao countryDao = new CountryDaoImpl(conn);
 		    Country country = new Country();
		    country.setName("China");
		    countryDao.insert(country);

		    country.setName("US" + new Date());
		    countryDao.update(country);

		    System.out.println("country id: " + country.getId());

		    List<Country> listCountries = countryDao.findAll();
		    for(Country aCountry : listCountries){
		    	System.out.println("name: " + aCountry.getName() + " id: " + aCountry.getId());
		    }

	    } catch(SQLException e){
	    	System.out.println("Exception occur");
	    }
	}


    public static void main(String []args) {
		System.out.println("Basic fullstack project");
		DbConnection.printConnectionDetails();
		Main.countryFunctionalities();
   }
}
