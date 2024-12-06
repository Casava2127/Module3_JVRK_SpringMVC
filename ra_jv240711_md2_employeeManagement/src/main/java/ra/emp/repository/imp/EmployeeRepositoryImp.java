package ra.emp.repository.imp;

import org.springframework.stereotype.Repository;
import ra.emp.model.Employee;
import ra.emp.repository.EmployeeRepository;
import ra.emp.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImp implements EmployeeRepository {

    @Override
    public List<Employee> findAll() {
        List<Employee> listEmployees = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Mở kết nối đến cơ sở dữ liệu
            conn = ConnectionDB.openConnection();

            // Gọi stored procedure FindAllEmployees
            String sql = "CALL FindAllEmployees()";
            ps = conn.prepareStatement(sql);

            // Thực thi truy vấn
            rs = ps.executeQuery();

            // Duyệt qua ResultSet và ánh xạ kết quả vào các đối tượng Employee
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpId(rs.getString("Emp_id"));
                emp.setEmpName(rs.getString("Emp_name"));
                emp.setBod(rs.getDate("Emp_bod"));
                emp.setEmpAddress(rs.getString("Emp_address"));
                emp.setEmpPhone(rs.getString("Emp_phone"));
                emp.setEmpRate(String.valueOf(rs.getFloat("Emp_rate")));
                emp.setDeptId(rs.getInt("Dept_id"));
                emp.setStatus(rs.getInt("Emp_status"));

                // Thêm đối tượng Employee vào danh sách
                listEmployees.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng các tài nguyên
            ConnectionDB.closeConnection(conn, ps, rs);
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
            // Sửa đổi để gọi đúng thủ tục với tất cả tham số
            callSt = conn.prepareCall("{call AddEmployee(?,?,?,?,?,?,?,?)}");
            callSt.setString(1, emp.getEmpId());
            callSt.setString(2, emp.getEmpName());
            callSt.setDate(3, new java.sql.Date(emp.getBod().getTime()));
            callSt.setString(4, emp.getEmpAddress()); // Địa chỉ
            callSt.setString(5, emp.getEmpPhone());   // Điện thoại
            callSt.setFloat(6, Float.parseFloat(emp.getEmpRate())); // Hệ số lương
            callSt.setInt(7, emp.getDeptId());
            callSt.setInt(8, emp.getStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public Employee findById(String id) {
//
        Connection conn = null;
        CallableStatement callSt = null;
        Employee emp = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call FindEmployeeById(?)}");
            callSt.setString(1, id);  // Chuyển đổi id từ String thành int

            ResultSet rs = callSt.executeQuery();
            emp = new Employee();
            if (rs.next()) {
                emp.setEmpId(rs.getString("Emp_id"));
                emp.setEmpName(rs.getString("Emp_name"));
                emp.setBod(rs.getDate("Emp_bod"));
                emp.setEmpAddress(rs.getString("Emp_address"));
                emp.setEmpPhone(rs.getString("Emp_phone"));
                emp.setEmpRate(String.valueOf(rs.getFloat("Emp_rate")));
                emp.setDeptId(rs.getInt("Dept_id"));
                emp.setStatus(rs.getInt("Emp_status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return emp;

    }
    @Override
    public boolean updateEmp(Employee emp) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call UpdateEmployee(?,?,?,?,?,?,?,?)}");
            callSt.setString(1, emp.getEmpId());
            callSt.setString(2, emp.getEmpName());
            callSt.setDate(3, new java.sql.Date(emp.getBod().getTime()));
            callSt.setString(4, emp.getEmpAddress()); // Địa chỉ
            callSt.setString(5, emp.getEmpPhone());   // Điện thoại
            callSt.setFloat(6, Float.parseFloat(emp.getEmpRate())); // Hệ số lương
            callSt.setInt(7, emp.getDeptId());
            callSt.setInt(8, emp.getStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public boolean delete(String bookId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call DeleteEmployee(?)}");
            callSt.setString(1, bookId);
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
