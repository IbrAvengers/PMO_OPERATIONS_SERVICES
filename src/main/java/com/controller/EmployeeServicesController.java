package com.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.EmployeeDAO;
import com.entity.EmployeeDetailsEntity;
import com.model.EmployeeAllDetails;

@RestController("/")
public class EmployeeServicesController {
	@Autowired
	EmployeeDAO empDAO;
	
	
	@RequestMapping(value = "/loginService", method = RequestMethod.GET, produces = { "application/json","application/xml" })
	public List<String> loginService(HttpServletResponse response, Authentication authentication) {
		Collection<GrantedAuthority> authorities = null;
		List<String> roles = null;

		try 
		{
			roles = new ArrayList<String>();
			authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) 
			{
				roles.add(grantedAuthority.getAuthority());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return roles;
	}
	
	@RequestMapping(value = "/getemployeealldetails/{empID}", method = RequestMethod.GET, produces = { "application/json","application/xml" })
	public  ResponseEntity<EmployeeAllDetails> getEmployeeAllDetails(@PathVariable String empID) 
	{
		return empDAO.getEmployeeAllDetails(empID);
	}
	
	@RequestMapping(value = "/listemployees", method = RequestMethod.GET, produces = { "application/json","application/xml" })
	public  ResponseEntity<List<EmployeeAllDetails>> empServiceAll() 
	{
		return empDAO.listAllEmployees();
	}
	
	@RequestMapping(value = "/getemployeedetails/{empID}", method = RequestMethod.GET, produces = { "application/json","application/xml" })
	public ResponseEntity<EmployeeDetailsEntity> empService(@PathVariable String empID, Authentication authentication) 
	{
		String uname = authentication.getName();
		if(uname.equals(empID))
		{
			return empDAO.getEmployeeDetails(empID);
		}
		else
			return new ResponseEntity("Not Authorised",HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping(value = "/managerService/{manID}", method = RequestMethod.GET, produces = { "application/json","application/xml" })
	public ResponseEntity<List<EmployeeAllDetails>> managerService(@PathVariable String manID) 
	{
		return empDAO.listManagerReporties(manID);
	}
}