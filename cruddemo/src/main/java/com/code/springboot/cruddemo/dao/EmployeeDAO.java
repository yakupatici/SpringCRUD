package com.code.springboot.cruddemo.dao;

import com.code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll(); // veritabanındaki tüm kayıtları alır

    Employee findById(int theId); // Id ile bulma

    Employee save(Employee theEmployee); // yeni kayıt ekleme

    void delete(int theId); // silme

}
