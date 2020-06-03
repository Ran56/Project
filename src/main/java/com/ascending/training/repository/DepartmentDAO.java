package com.ascending.training.repository;
import com.ascending.training.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DepartmentDAO {
// CRUD
    List<Department> departments = new ArrayList();
    static final String DBURL ="jdbc:postgresql://localhost:5432/dealer";
    static final String USER = "admin";
    static final String PASS = "password";
    private Logger logger = LoggerFactory.getLogger(getClass());


    public int add () {
        Connection conn = null;
        Statement stmt = null;
        int r = 0;
        try {

            logger.debug("Connection to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO departments (name, description, location)" + "VALUES (?,?,?)";
            r = stmt.executeUpdate(sql);

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {

            try {
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return r;
    }

    public int delete(){

        Connection conn = null;
        Statement stmt = null;
        int r = 0;
        try {
            //STEP 2: Open a connection
            logger.debug("Connection to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);
            //STEP 3: Execute a query
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM DEPARTMENTS WHERE NAME = ?";
           r = stmt.executeUpdate(sql);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return r;
    }

    public int update(){

        Connection conn = null;
        Statement stmt = null;
        int r = 0;
        try {
            logger.debug("Connection to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String sql = "UPDATE departments SET name=?, description=?, location=? WHERE name=?";
            r = stmt.executeUpdate(sql);

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return r;
    }

    public List<Department> getDepartments(){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Open a connection
            logger.debug("Connection to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);
            //STEP 3: Execute a query
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM departments";
            rs = stmt.executeQuery(sql);

            logger.info("C onverting data...");
            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                 Long id  = rs.getLong("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String location = rs.getString("location");
                //Fill the object
                Department department = new Department();
                department.setId(id);
                department.setName(name);
                department.setDescription(description);
                department.setLocation(location);
                departments.add(department);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return departments;
    }




  //  public static void main(String[] args)
  //  {
        //此方法是为了用眼睛去看，应该用test去测试
//        DepartmentDAO departmentJDBCDAO = new DepartmentDAO();
//        System.out.println(departmentJDBCDAO.getDepartments());
        //不需要去运行，在test中运行
    //}


}
