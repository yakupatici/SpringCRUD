package com.code.springboot.cruddemo.dao;

import com.code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {


    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee e", Employee.class);

        // execute query and get result list
        List<Employee> employees = query.getResultList();

        // return the result

        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // get employee
        Employee employee = entityManager.find(Employee.class, theId);

        // return employee
        return employee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        // save employee
        Employee dbEmployee = entityManager.merge(theEmployee);

       // return the dbEmployee
        return dbEmployee;
    }

    @Override  // Note : we dont use @ Transactional at DAO layer , ıt will be handled at Service Layer
    public void delete(int theId) {
        // find employee by id

        Employee dbEmployee = entityManager.find(Employee.class, theId);
        entityManager.remove(dbEmployee);


    }
}
