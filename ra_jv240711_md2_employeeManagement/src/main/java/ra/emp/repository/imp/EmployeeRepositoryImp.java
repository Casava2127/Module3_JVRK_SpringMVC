package ra.emp.repository.imp;

import org.springframework.stereotype.Repository;
import ra.emp.model.Department;
import ra.emp.model.Employee;
import ra.emp.repository.EmployeeRepository;
import ra.emp.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImp implements EmployeeRepository {

    @Override
    public List<Employee> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> listEmployees = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_emp()}");
            ResultSet rs = callSt.executeQuery();
            listEmployees = new ArrayList<>();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpId(rs.getString("emp_id"));
                emp.setEmpName(rs.getString("emp_name"));
                emp.setBod(rs.getDate("emp_bod"));
                emp.setDeptId(rs.getInt("dept_id"));
                emp.setDeptName(rs.getString("dept_name"));
                emp.setStatus(rs.getInt("emp_status"));
                listEmployees.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listEmployees;
    }

    @Override
    public boolean create(Employee emp) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_emp(?,?,?,?,?)}");
            callSt.setString(1, emp.getEmpId());
            callSt.setString(2, emp.getEmpName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            callSt.setDate(3, new java.sql.Date(emp.getBod().getTime()));
            callSt.setInt(4, emp.getDeptId());
            callSt.setInt(5, emp.getStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
}
