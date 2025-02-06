package ra.crud.repository.imp;

import org.springframework.stereotype.Repository;
import ra.crud.model.Department;
import ra.crud.model.Employee;
import ra.crud.repository.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository

public class EmployeeRepositoryImp implements EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery("from Employee", Employee.class).getResultList();
    }

    @Override
    public Employee findById(String  empId) {
        return entityManager.createQuery("from Employee where empId=:id", Employee.class).setParameter("id", empId).getSingleResult();
    }

    @Override
    @Transactional
    public boolean save(Employee employee) {
        try {
            entityManager.persist(employee); // luudoi tuogn
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public boolean update(Employee employee) {
        try {
            entityManager.merge(employee); // cap nhat du lieu
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delete(String empId) {
        try {
            Employee employee = findById(empId);
            entityManager.remove(employee);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
