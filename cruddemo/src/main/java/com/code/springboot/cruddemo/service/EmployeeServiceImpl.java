// package tanımı: Uygulamanın hangi pakette olduğunu belirtir. Bu kod, "service" paketinde yer alan bir EmployeeServiceImpl sınıfını tanımlar.
package com.code.springboot.cruddemo.service;

import com.code.springboot.cruddemo.dao.EmployeeDAO;
import com.code.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service annotasyonu bu sınıfın bir "service" olduğunu belirtir ve Spring Framework'ün bu sınıfı bir bean olarak yönetmesini sağlar. 
// Service layer (katman), iş mantığı ve veri işleme süreçlerini DAO ile Controller arasında yönlendirmek için kullanılır.
// Service katmanı, uygulamanın merkezi iş mantığını içerir ve Controller'lar ile DAO (Data Access Object) arasında bir köprü sağlar.
@Service
public class EmployeeServiceImpl implements EmployeeService {

    // EmployeeDAO nesnesi ile DAO katmanına erişiyoruz. DAO veri tabanıyla doğrudan etkileşim kurar.
    // Service katmanında veri tabanı sorguları yapılmaz, onun yerine DAO kullanılır. Bu da sorumlulukların ayrılmasını sağlar (Separation of Concerns - SoC).
    private EmployeeDAO employeeDAO;

    // Constructor-based dependency injection kullanarak EmployeeDAO'nun bir instance'ını bu service sınıfına enjekte ediyoruz.
    // @Autowired annotasyonu, Spring'in EmployeeDAO nesnesini otomatik olarak enjekte etmesini sağlar.
    // Bu yöntem sayesinde nesne bağımlılıkları dışarıdan sağlanır (Dependency Injection), bu da kodun test edilebilirliğini ve esnekliğini artırır.
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
        this.employeeDAO = theEmployeeDAO;
    }

    // Tüm çalışanları döndüren bir method. Bu method, DAO katmanını kullanarak veri tabanından tüm çalışanları getirir.
    // Service katmanında veri işleme mantığı ya da ek iş kuralları uygulanabilir. Bu method, DAO'yu çağırarak verileri iş mantığına uygun hale getirir.
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }


    @Override
    public Employee findById(int theId) {
        return employeeDAO.findById(theId);
    }
    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeDAO.save(theEmployee);
    }
    @Transactional
    @Override
    public void deleteById(int   theId) {
          employeeDAO.delete(theId);
    }
}

/*
* Note : Transactional anatosyonunu Dao da kullanmayıp
* tüm bu işleri sevice kısmında yapmasını istedik .
* Spring’de veri tabanı işlemlerinin yönetimini sağlayan bir anotasyondur. Veritabanındaki işlemleri tek bir birim olarak ele alır v
* ve veri tutarlılığını sağlar. Başarılı olursa değişiklikler kaydedilir, bir hata durumunda ise yapılan tüm işlemler geri alınır.
*Delete , update , save gibi metodlara enjecte edilmelidir.
*
* */