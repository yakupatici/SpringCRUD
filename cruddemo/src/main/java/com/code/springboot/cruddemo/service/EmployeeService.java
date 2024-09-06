package com.code.springboot.cruddemo.service;

import com.code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int theId); // Id ile bulma

    Employee save(Employee theEmployee); // yeni kayıt ekleme

    void deleteById(int theId); // silme

}
