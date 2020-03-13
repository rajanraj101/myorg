package com.myorg.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private EmployeeDAO employeeDao;
    
    @GetMapping(path="/all", produces = "application/json")
    public Employees getEmployees() 
    {
    	System.out.println("get all employees");
        return employeeDao.getAllEmployees();
    }
   
    @PostMapping(path= "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(
                        @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
                        @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
                        @RequestBody Employee employee) 
                 throws Exception 
    {       

    	System.out.println("add employee request for " + employee);
        //Generate resource id
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);
        
        //add resource
        employeeDao.addEmployee(employee);

    	System.out.println("employee added ID :" + employee.getId());
        
       /* //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(employee.getId())
                                    .toUri();*/
        
        //Send location in response
        return ResponseEntity.accepted().body("Employee added successfully " + employee);
    }
    
    @GetMapping(path= "/{id}", produces = "application/json")
    public ResponseEntity<Object> getEmployee(@PathVariable int id) 
                 throws Exception 
    {  

    	System.out.println("get employee for id "+ id);
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
    
}
