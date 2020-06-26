package com.ascending.training.controller;


import com.ascending.training.model.Department;
import com.ascending.training.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/department")
//也可以在@RestController下面写一个@RequestMapping(value = "/departmentHQL"）这样方法内的
//value = "/departmentHQL"就可以写成value = ""。
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;


    private Logger logger = LoggerFactory.getLogger(getClass());
// @RequestMapping{value = {"/department","/dept"}} 也可以这样写前台请求dept和departmentHQL都可以

    //      /department GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public void getDepartments()
    {
        logger.debug("i am in the department controller");

    }

    //      /department/2 GET
    @RequestMapping(value = "/{Id}", method = RequestMethod.GET,params = {"name"})
    public Department getDepartmentById(@PathVariable(name = "Id") Long id)
    {
        logger.debug("i am in the department controller get by "+id);
        return departmentService.getBy(id);

    }

    //      /department/2?name=HR1 PATCH
    @RequestMapping(value = "/{Id}", method = RequestMethod.PATCH)
    public Department updateDepartment (@PathVariable(name = "Id") Long id, @RequestParam("name") String name)
    {
        Department department = departmentService.getBy(id);
        department.setName(name);
        department = departmentService.update(department);
        return department;
    }

    @RequestMapping(value ="",method = RequestMethod.POST)
    public void create(@RequestBody Department department)
    {

        logger.debug(department.toString());
        departmentService.save(department);
        logger.debug("succeed to insert recording");
    }


//////////////////////////////////////////////////////////////////////////////////////////
    //当URI和HTTP method都一样的时候需要引入第三个parameter
    //      /department/CS GET
    @RequestMapping(value = "", method = RequestMethod.GET,params = {"name"})
    public Department getDepartmentByName(@RequestParam("name") String name)
    {
        logger.debug("i am in the department controller get by "+name);
        return null;
    }
    //      /department/Research and Development GET
    @RequestMapping(value = "", method = RequestMethod.GET,params = {"description"})
    public Department getDepartmentByDescription(@RequestParam(name = "description") String description)
    {
        logger.debug("i am in the department controller get by "+description);
        return null;
    }
//////////////////////////////////////////////////////////////////////////////////////////




}

