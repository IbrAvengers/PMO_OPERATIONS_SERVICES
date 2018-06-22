package com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.entity.EmployeeDetailsEntity;
import com.model.EmployeeAllDetails;

@Repository
public class EmployeeDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public ResponseEntity<EmployeeDetailsEntity> getEmployeeDetails(String empID)
	{
		EmployeeDetailsEntity emp = em.find(EmployeeDetailsEntity.class, empID);
		if(emp == null)
			return new ResponseEntity("Employee Not Found",HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<EmployeeDetailsEntity>(emp,HttpStatus.OK);
	}
	
	public ResponseEntity<EmployeeAllDetails> getEmployeeAllDetails(String empID)
	{
		String getSql = null;
		List<Object[]> objects = null;
		EmployeeAllDetails details = null;
		
		try 
		{
			getSql = "select emp.emp_id,emp.first_name,emp.last_name,emp.ibm_mail_id,emp.ph_number,pr.manager,pr.project from employee_details emp,employee_project_details pr where emp.emp_id = :empID and pr.emp_id = :empID";
			objects = em.createNativeQuery(getSql).setParameter("empID", empID).getResultList();
			if(objects != null)
			{
				details = new EmployeeAllDetails();
				for(Object[] o : objects)
				{
					details = new EmployeeAllDetails();
					details.setEmp_id(o[0].toString());
					details.setFirst_name(o[1].toString());
					details.setLast_name(o[2].toString());
					details.setIbm_mail_id(o[3].toString());
					details.setPh_number(o[4].toString());
					details.setManager(o[5].toString());
					details.setProject(o[6].toString());
				}
				if(details == null)
					return new ResponseEntity("Employee Not Found",HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<EmployeeAllDetails>(details,HttpStatus.OK);
			}
			else
				return new ResponseEntity("Employee Not Found",HttpStatus.NOT_FOUND);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ResponseEntity("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<EmployeeAllDetails>> listAllEmployees()
	{
		String getSql = null;
		List<EmployeeAllDetails> vo = null;
		List<Object[]> objects = null;
		EmployeeAllDetails details = null;
		
		try 
		{
			getSql = "select emp.emp_id,emp.first_name,emp.last_name,emp.ibm_mail_id,emp.ph_number,pr.manager,pr.project from employee_details emp,employee_project_details pr where emp.emp_id = pr.emp_id";
			objects = em.createNativeQuery(getSql).getResultList();
			if(objects != null)
			{
				vo = new ArrayList<>(objects.size());
				for(Object[] o : objects)
				{
					details = new EmployeeAllDetails();
					details.setEmp_id(o[0].toString());
					details.setFirst_name(o[1].toString());
					details.setLast_name(o[2].toString());
					details.setIbm_mail_id(o[3].toString());
					details.setPh_number(o[4].toString());
					details.setManager(o[5].toString());
					details.setProject(o[6].toString());
					vo.add(details);
				}
				if(vo == null)
					return new ResponseEntity("Employee Not Found",HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<EmployeeAllDetails>>(vo,HttpStatus.OK);
			}
			else
				return new ResponseEntity("Employee Not Found",HttpStatus.NOT_FOUND);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ResponseEntity("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<EmployeeAllDetails>> listManagerReporties(String manID)
	{
		String getSql = null;
		List<EmployeeAllDetails> vo = null;
		List<Object[]> objects = null;
		EmployeeAllDetails details = null;
		
		try 
		{
			getSql = "select emp.emp_id,emp.first_name,emp.last_name,emp.ibm_mail_id,emp.ph_number,pr.manager,pr.project from employee_details emp,employee_project_details pr where emp.emp_id = pr.emp_id and pr.manager = :manID";
			objects = em.createNativeQuery(getSql).setParameter("manID", manID).getResultList();
			if(objects != null)
			{
				vo = new ArrayList<>(objects.size());
				for(Object[] o : objects)
				{
					details = new EmployeeAllDetails();
					details.setEmp_id(o[0].toString());
					details.setFirst_name(o[1].toString());
					details.setLast_name(o[2].toString());
					details.setIbm_mail_id(o[3].toString());
					details.setPh_number(o[4].toString());
					details.setManager(o[5].toString());
					details.setProject(o[6].toString());
					vo.add(details);
				}
				if(vo == null)
					return new ResponseEntity("Employee Not Found",HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<EmployeeAllDetails>>(vo,HttpStatus.OK);
			}
			else
				return new ResponseEntity("Employee Not Found",HttpStatus.NOT_FOUND);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ResponseEntity("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}