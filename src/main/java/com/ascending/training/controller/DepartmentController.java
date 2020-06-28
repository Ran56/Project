package com.ascending.training.controller;


import com.ascending.training.model.Department;
import com.ascending.training.service.DepartmentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/department")
//也可以在@RestController下面写一个@RequestMapping(value = "/department"）这样方法内的
//value = "/department"就可以写成value = ""。
// @RequestMapping{value = {"/department","/dept"}} 也可以这样写前台请求dept和department都可以
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    //      /department GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Department> getDepartments()
    {
        logger.debug("Retrieve all departments");
        return departmentService.getDepartments();
    }

    //      /department/2 GET
    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public Department getDepartmentById(@PathVariable(name = "Id") Long id)
    {
        logger.debug("Retrieve department by id "+id);
        return departmentService.getBy(id);
    }

    //当URI和HTTP method都一样的时候需要引入第三个parameter
    //      /department?name=CS GET
    @RequestMapping(value = "", method = RequestMethod.GET,params = {"name"})
    public Department getDepartmentByName(@RequestParam(name = "name") String name)
    {
        logger.debug("Using parameter to retrieve department by name "+name);
        return departmentService.getDepartmentByName(name);
    }

    //      /department?description=Research and Development GET
    @RequestMapping(value = "", method = RequestMethod.GET,params = {"description"})
    public Department getDepartmentByDescription(@RequestParam(name = "description") String description)
    {
        logger.debug("Using parameter to retrieve department by description "+description);
        return null;
    }

    //      /department/1?name=HR1 PATCH
    @RequestMapping(value = "/{Id}", method = RequestMethod.PATCH)
    public Department updateDepartment (@PathVariable(name = "Id") Long id, @RequestParam("name") String name)
    {
        logger.debug("Updating name of department " + id);
        Department department = departmentService.getBy(id);
        department.setName(name);
        department = departmentService.update(department);
        return department;
    }

    //      /department POST
    @RequestMapping(value ="",method = RequestMethod.POST)
    public Department create(@RequestBody Department department)
    {
        logger.debug("Creating department ");
        Department department1 = departmentService.save(department);
        return department1;
    }

    //      /department/2 DELETE
    @RequestMapping(value = "/{Id}",method = RequestMethod.DELETE)
    public boolean delete(@PathVariable(name="Id") Long id)
    {
        logger.debug("Deleting department by id "+id);
        Department department = departmentService.getBy(id);
        boolean result = departmentService.delete(department);
        return result;
    }








}

