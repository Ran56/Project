package com.ascending.training.controller;


import com.ascending.training.model.DepartmentHQL;
import com.ascending.training.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//也可以在@RestController下面写一个@RequestMapping(value = "/departmentHQL"）这样方法内的
//value = "/departmentHQL"就可以写成value = ""。
public class DepartmentController {

    private Logger logger = LoggerFactory.getLogger(getClass());
// @RequestMapping{value = {"/departmentHQL","/dept"}} 也可以这样写前台请求dept和departmentHQL都可以
    @RequestMapping(value = "/departmentHQL", method = RequestMethod.GET)
    public void getDepartments()
    {
        logger.debug("i am in the department controller");

    }

    @RequestMapping(value = "/departmentHQL/{Id}", method = RequestMethod.GET)
    public void getDepartmentById(@PathVariable(name = "Id") Long id)
    {
        logger.debug("i am in the department controller get by "+id);
    }
//    public List<DepartmentHQL> getDepartments()
//    {
//
//    }


}
