// package tanımı: Bu sınıfın hangi pakette yer aldığını belirtir. Bu sınıf, "rest" paketinde bulunur ve REST API'yi yönetir.
package com.code.springboot.cruddemo.rest;

import com.code.springboot.cruddemo.service.EmployeeService;
import com.code.springboot.cruddemo.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Bu anotasyon, sınıfı bir RESTful web servis sınıfı haline getirir.
// RESTful servislere yapılan HTTP isteklerini (GET, POST, PUT, DELETE) işleyip cevap döner.
// Bu sınıf JSON formatında veri döndürecektir.
@RestController
// @RequestMapping("/api"): Bu sınıftaki tüm uç noktaların (endpoints) temel yolunu "/api" olarak ayarlıyoruz.
// Tüm HTTP istekleri "/api" yolu ile başlayacaktır.
@RequestMapping("/api")
public class EmployeeRestController {

    // Servis katmanını temsil eden EmployeeService nesnesini tanımlıyoruz.
    // Bu katman veri erişimi ve iş mantığını yönetir.
    private EmployeeService employeeService;

    // Constructor-based injection: EmployeeService bağımlılığı bu sınıfa constructor aracılığıyla enjekte edilir.
    // Bu yöntem daha güvenlidir ve test edilebilirlik açısından avantajlıdır.
    // @Autowired anotasyonu eklenmemiş, ancak Spring modern versiyonlarında tek bir constructor varsa otomatik olarak enjekte eder.
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // @GetMapping("/employees"): Bu anotasyon, HTTP GET isteklerini "/employees" yoluyla yakalar.
    // Yani "/api/employees" yoluna yapılan GET istekleri bu metoda yönlendirilir.
    // Bu metot, tüm çalışanları liste olarak dönecek.
    @GetMapping("/employees")
    public List<Employee> findAll() {
        // EmployeeService aracılığıyla veri tabanındaki tüm çalışanları döndürür.
        // Bu liste JSON formatında API cevabı olarak döner.
        return employeeService.findAll();
    }
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee not found - " + employeeId );

        }
        return employee;
    }

    // add mapping for POST / employees - add new employee

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        // force a save of new ıtem instead of update

        employee.setId(0);
        Employee savedEmployee = employeeService.save(employee);
        return savedEmployee;
    }

    // add mapping for PUT / employees - update existing employee

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.save(employee);
        return savedEmployee;
    }


    // add mapping for DELETE / employees{emploeyyedId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
       Employee tempEmployee = employeeService.findById(employeeId);

       // throw exception if null
        if (tempEmployee == null) {
            throw new RuntimeException("Employee not found - " + employeeId );
        }
        employeeService.deleteById(employeeId);
        return "Employee deleted - " + employeeId;
    }




}




// Dao da bulunan işlemler direk olarak restcontrolera getirilmek yerine employee servici aracılığıyla erişilir ve
// bu da kod karmaşıklığını önler