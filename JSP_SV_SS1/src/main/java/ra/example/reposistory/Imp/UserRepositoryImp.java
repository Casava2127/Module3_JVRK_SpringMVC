package ra.example.reposistory.Imp;

import ra.example.entity.User;
import ra.example.reposistory.UserRepository;
import ra.example.util.ConnectionDBUser;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImp implements UserRepository {
    @Override
    public List<User> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<User> listUsers = null;

        try{
            conn = ConnectionDBUser.openConnection();
            callSt = conn.prepareCall("{call find_all_users()}");
            ResultSet rs = callSt.executeQuery();
            listUsers = new ArrayList<>();

            while(rs.next()){
                User user = new User();
//                // theo sql
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("full_name"));
                user.setStatus(rs.getBoolean("status"));
                user.setCreatedAt(rs.getTimestamp("created_at"));

                listUsers.add(user);
            }
         } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDBUser.closeConnection(conn, callSt);
        }
        return listUsers;
    }

    @Override
    public boolean save(User user) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result =false;

        try{
            conn = ConnectionDBUser.openConnection();
            callSt = conn.prepareCall("{call create_new_user(?,?,?,?,?)}");
            callSt.setString(1, user.getUsername());
            callSt.setString(2, user.getEmail());
            callSt.setString(3, user.getPassword());
            callSt.setString(4, user.getFullName());
            callSt.setBoolean(5, user.isStatus());
            callSt.executeUpdate(); // Thực hiện lệnh INSERT
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDBUser.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public User findById(int userId) {
        Connection conn = null;
        CallableStatement callSt = null;
        User user = new User();

        try{
            conn = ConnectionDBUser.openConnection();
            callSt = conn.prepareCall("{call find_user_by_id(userId))}");
            callSt.setInt(1,userId);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("full_name"));
                user.setStatus(rs.getBoolean("status"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDBUser.closeConnection(conn, callSt);
        }
        return user;
    }
}
