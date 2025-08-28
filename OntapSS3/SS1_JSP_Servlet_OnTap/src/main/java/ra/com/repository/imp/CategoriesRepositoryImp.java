package ra.com.repository.imp;

import ra.com.entity.Categories;
import ra.com.repository.CategoriesRepository;
import ra.com.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepositoryImp implements CategoriesRepository {

    @Override
    public List<Categories> findAll() {
        List<Categories> listCategories = new ArrayList<>();

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement callSt = conn.prepareCall("{call find_all_categories()}");
             ResultSet rs = callSt.executeQuery()) {

            while (rs.next()) {
                Categories categories = new Categories();
                categories.setCatalogId(rs.getInt("catalog_id"));
                categories.setCatalogName(rs.getString("catalog_name"));
                categories.setDescription(rs.getString("catalog_description"));
                categories.setStatus(rs.getBoolean("catalog_status"));
                listCategories.add(categories);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listCategories;
    }

    @Override
    public boolean save(Categories categories) {
        boolean result = false;

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement callSt = conn.prepareCall("{call create_new_catalog(?,?,?)}")) {

            callSt.setString(1, categories.getCatalogName());
            callSt.setString(2, categories.getDescription());
            callSt.setBoolean(3, categories.isStatus());

            callSt.execute();
            result = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public Categories findById(int catalogId) {
        Categories catalog = null;

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement callSt = conn.prepareCall("{call find_catalog_by_id(?)}")) {

            callSt.setInt(1, catalogId);
            try (ResultSet rs = callSt.executeQuery()) {
                if (rs.next()) {
                    catalog = new Categories();
                    catalog.setCatalogId(rs.getInt("catalog_id"));
                    catalog.setCatalogName(rs.getString("catalog_name"));
                    catalog.setDescription(rs.getString("catalog_description"));
                    catalog.setStatus(rs.getBoolean("catalog_status"));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return catalog;
    }

    @Override
    public boolean update(Categories categories) {
        boolean result = false;

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement callSt = conn.prepareCall("{call update_catalog(?,?,?,?)}")) {

            callSt.setInt(1, categories.getCatalogId());
            callSt.setString(2, categories.getCatalogName());
            callSt.setString(3, categories.getDescription());
            callSt.setBoolean(4, categories.isStatus());

            callSt.execute();
            result = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(int catalogId) {
        boolean result = false;

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement callSt = conn.prepareCall("{call delete_catalog(?)}")) {

            callSt.setInt(1, catalogId);
            callSt.executeUpdate();
            result = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
