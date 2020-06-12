package com.ascending.training.repository;

import com.ascending.training.model.Department;
import com.ascending.training.model.DepartmentHQL;

import java.util.List;

public interface DepartmentDao {
    DepartmentHQL save(DepartmentHQL department);
    List<DepartmentHQL> getDepartments();
    DepartmentHQL getBy(Long id);
    boolean delete(DepartmentHQL dep);

    DepartmentHQL update(DepartmentHQL department);
    boolean delete(String deptName);
    List<DepartmentHQL> getDepartmentsEager();
    DepartmentHQL getDepartmentEagerBy(Long id);
    DepartmentHQL getDepartmentByName(String deptName);


    DepartmentHQL getDepartmentAndEmployeesBy(String deptName);
    List<Object[]> getDepartmentAndEmployeesAndAccounts(String deptName);






}
