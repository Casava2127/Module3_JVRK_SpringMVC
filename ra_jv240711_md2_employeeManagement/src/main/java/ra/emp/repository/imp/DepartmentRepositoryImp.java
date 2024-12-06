package ra.emp.repository.imp;

import org.springframework.stereotype.Repository;
import ra.emp.model.Department;
import ra.emp.repository.DepartmentRepository;
import ra.emp.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepositoryImp implements DepartmentRepository {

    @Override
    public List<Department> findAll() {
        List<Department> listDepartments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Mở kết nối đến cơ sở dữ liệu
            conn = ConnectionDB.openConnection();

            // Gọi stored procedure FindAllDepartment
            String sql = "call FindAllDepartment()";
            ps = conn.prepareStatement(sql);

            // Thực thi truy vấn
            rs = ps.executeQuery();

            // Duyệt qua ResultSet và ánh xạ kết quả vào các đối tượng Department
            while (rs.next()) {
                Department dept = new Department();
                dept.setDeptId(rs.getInt("Dept_id"));
                dept.setDeptName(rs.getString("Dept_name"));
                dept.setDeptDescription(rs.getString("Dept_description"));
                dept.setDeptStatus(rs.getBoolean("Dept_status"));

                // Thêm đối tượng Department vào danh sách
                listDepartments.add(dept);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Đóng các tài nguyên
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return listDepartments;
    }

//    @Override
//    public List<Department> search(String searchTerm) {
//        return List.of();
//    }

    @Override
    public boolean create(Department dept) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            // Sửa đổi để gọi đúng thủ tục với tất cả tham số
            callSt = conn.prepareCall("{call AddDepartment(?,?,?)}");
            callSt.setString(1, dept.getDeptName());
            callSt.setString(2, dept.getDeptDescription());
            callSt.setBoolean(3, dept.getDeptStatus());
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
    public Department findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Department dept = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call FindDepartmentById(?)}");
            callSt.setInt(1, id);

            ResultSet rs = callSt.executeQuery();
            dept = new Department();
            if (rs.next()) {
                dept.setDeptId(rs.getInt("Dept_id"));
                dept.setDeptName(rs.getString("Dept_name"));
                dept.setDeptDescription(rs.getString("Dept_description"));
                dept.setDeptStatus(rs.getBoolean("Dept_status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dept;
    }

    @Override
    public boolean update(Department dept) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call UpdateDepartment(?,?,?)}");
            callSt.setInt(1, dept.getDeptId());
            callSt.setString(2, dept.getDeptName());
            callSt.setString(3, dept.getDeptDescription());
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
    public boolean delete(int deptId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call DeleteDepartment(?)}");
            callSt.setInt(1, deptId);
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
