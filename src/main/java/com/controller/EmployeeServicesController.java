package com.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.EmployeeDAO;
import com.entity.EmployeeDetailsEntity;

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
	
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = { "application/json","application/xml" })
	public  ResponseEntity<List<EmployeeDetailsEntity>> empService() 
	{
		return empDAO.listEmployee();
	}
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = { "application/json","application/xml" })
	public ResponseEntity<EmployeeDetailsEntity> empService(@PathVariable String id) 
	{
		return empDAO.getEmployee(id);
	}
	@RequestMapping(value = "/managerService", method = RequestMethod.GET, produces = { "application/json","application/xml" })
	public void managerService() 
	{
		System.out.println("Inside Manager");
	}
	@RequestMapping(value = "/adminService", method = RequestMethod.GET, produces = { "application/json","application/xml" })
	public void adminService() 
	{
		System.out.println("Inside Admin");
	}
	
}
 