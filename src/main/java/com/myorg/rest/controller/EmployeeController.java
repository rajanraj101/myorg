package com.myorg.rest.controller;

import java.util.Optional;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myorg.rest.dao.EmployeeDAO;
import com.myorg.rest.model.Employee;
import com.myorg.rest.model.Employees;

@RestController
@RequestMapping(path = "/myorg/employees")
public class EmployeeController 
{
	private static final Logger LOGGER=LogManager.getLogger(EmployeeController.class.getSimpleName());
	
    @Autowired
    private EmployeeDAO employeeDao;
    
    @GetMapping(path="/all", produces = "application/json")
    public Employees getEmployees() 
    {
    	LOGGER.debug("get all employees");
        return employeeDao.getAllEmployees();
    }
   
    @PostMapping(path= "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(
                        @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
                        @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
                        @RequestBody Employee employee) 
                 throws Exception 
    {       

    	LOGGER.debug("add employee request for " + employee);
        //Generate resource id
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);
        
        //add resource
        employeeDao.addEmployee(employee);

    	LOGGER.debug("employee added ID :" + employee.getId());
        
        return ResponseEntity.accepted().body("Employee added successfully " + employee);
    }
    
    @GetMapping(path= "/{id}", produces = "application/json")
    public ResponseEntity<Object> getEmployee(@PathVariable int id) 
                 throws Exception 
    {  

    	LOGGER.debug("get employee for id "+ id);
    	Optional<Employee> e = employeeDao.getAllEmployees().getEmployeeList()
    													.stream()
    													.filter(emp -> (emp.getId() == id) )
    													.findFirst() ;

    	if(e.isPresent()) {
    		 return ResponseEntity.ok().body(e);
    	} else {
    		 return ResponseEntity.ok().body("Employee not found with Id:" + id);
    	}
    }
        
    @DeleteMapping(path= "/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteEmployee(@PathVariable int id) 
                 throws Exception 
    {  

    	LOGGER.debug("get employee for id "+ id);
    	boolean isRemoved = employeeDao.getAllEmployees().getEmployeeList().removeIf(emp -> (emp.getId() == id) );
    	
    	if(isRemoved) { 
    		 return ResponseEntity.ok().body("isDeleted: " + isRemoved +", id: " + id);
    	} else {
    		 return ResponseEntity.ok().body("Employee not found with Id:" + id);
    	}
    }
    
}
