package ra.emp.repository.imp;

import org.springframework.stereotype.Repository;
import ra.emp.model.Department;
import ra.emp.repository.DepartmentRepository;
import ra.emp.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepositoryImp implements DepartmentRepository {
    @Override
    public List<Department> findAllActive() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Department> listDepartments = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_dept()}");
            ResultSet rs = callSt.executeQuery();
            listDepartments = new ArrayList<>();
            while (rs.next()) {
                Department dept = new Department();
                dept.setDeptId(rs.getInt("dept_id"));
                dept.setDeptName(rs.getString("dept_name"));
                dept.setStatus(rs.getBoolean("dept_status"));
                listDepartments.add(dept);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listDepartments;
    }
}
