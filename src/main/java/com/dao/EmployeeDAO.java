package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.entity.EmployeeDetailsEntity;
import com.entity.UserEntity;
import com.model.EmployeeDetails;

@Repository
public class EmployeeDAO {
	
	@PersistenceContext
	EntityManager em;
	public ResponseEntity<EmployeeDetailsEntity> getEmployee(String id)
	{
		EmployeeDetailsEntity emp = em.find(EmployeeDetailsEntity.class, id);
		if(emp == null)
			return new ResponseEntity("Employee Not Found",HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<EmployeeDetailsEntity>(emp,HttpStatus.OK);
	}

	public ResponseEntity<List<EmployeeDetailsEntity>> listEmployee()
	{
		String getSql = null;
		List<EmployeeDetailsEntity> vo = null;
		
		try 
		{
			getSql ="from EmployeeDetailsEntity emp";
			vo = em.createQuery(getSql,EmployeeDetailsEntity.class).getResultList();
			if(vo == null)
				return new ResponseEntity("Employee Not Found",HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<List<EmployeeDetailsEntity>>(vo,HttpStatus.OK);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}

}
