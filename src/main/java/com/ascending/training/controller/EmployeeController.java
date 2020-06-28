package com.ascending.training.controller;

import com.ascending.training.model.Department;
import com.ascending.training.model.Employee;
import com.ascending.training.service.DepartmentService;
import com.ascending.training.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    //      /employee GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Employee> getEmployees()
    {
        logger.debug("Retrieve all employees");
        return employeeService.getEmployees();
    }

    //      /employee/2 GET
    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable(name = "Id") Long id)
    {
        logger.debug("Retrieve employee by id "+id);
        return employeeService.getBy(id);
    }

    //      /employee?name=Ran GET
    @RequestMapping(value = "", method = RequestMethod.GET,params = {"name"})
    public Employee getEmployeeByName(@RequestParam(name = "name") String name)
    {
        logger.debug("Using parameter to retrieve employee by name "+name);
        return employeeService.getEmployeeByName(name);
    }

    //      /employee/2?name=Liu PATCH
    @RequestMapping(value = "/{Id}", method = RequestMethod.PATCH)
    public Employee updateEmployee (@PathVariable(name = "Id") Long id, @RequestParam("name") String name)
    {
        logger.debug("Updating name of employee " + id);
        Employee employee = employeeService.getBy(id);
        employee.setName(name);
        employee = employeeService.update(employee);
        return employee;
    }

    //      /employee/2 POST
    @RequestMapping(value ="/{Id}",method = RequestMethod.POST)
    public Employee create(@PathVariable(name = "Id") Long id, @RequestBody Employee employee)
    {
        logger.debug("Creating employee ");
        Department department = departmentService.getBy(id);
        employee.setDepartment(department);
        employee = employeeService.save(employee);
        return employee;
    }

    //      /employee/126 DELETE
    @RequestMapping(value = "/{Id}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable(name="Id") Long id)
    {
        logger.debug("Deleting employ by id "+id);
        Employee employee = employeeService.getBy(id);
        boolean result = employeeService.delete(employee);
        return result;
    }






}
