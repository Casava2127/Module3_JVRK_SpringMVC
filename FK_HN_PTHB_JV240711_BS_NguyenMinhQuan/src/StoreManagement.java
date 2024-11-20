import java.sql.*;
import java.util.Scanner;

public class StoreManagement {
    private static Connection connect(){
        String url = "jdbc:mysql://localhost:3306/quanlybanhang"; // Thay thế bằng tên cơ sở dữ liệu của bạn
        String user = "root"; // Thay thế bằng tên người dùng của bạn
        String password = "123456"; // Thay thế bằng mật khẩu của bạn
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    private static void showMainMenu(){
        System.out.println("******************STORE-MANAGEMENT******************");
        System.out.println("1. Quản lý danh mục");
        System.out.println("2. Quản lý sản phẩm");
        System.out.println("3. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }
    private static void showCategoryMenu(){
        System.out.println("**********************CATEGORY-MENU********************");
        System.out.println("1. Danh sách danh mục");
        System.out.println("2. Tạo mới danh mục");
        System.out.println("3. Cập nhật danh mục");
        System.out.println("4. Xóa danh mục");
        System.out.println("5. Thống kê số lượng sản phẩm theo danh mục");
        System.out.println("6. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }
    private static void showProductMenu() {
        System.out.println("************************PRODUCT-MENU********************");
        System.out.println("1. Danh sách sản phẩm");
        System.out.println("2. Tạo mới sản phẩm");
        System.out.println("3. Cập nhật sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Hiển thị danh sách sản phẩm theo ngày tạo giảm dần");
        System.out.println("6. Tìm kiếm sản phẩm theo khoản giá bán.");
        System.out.println("7. Hiển thị top 3 sản phẩm có lợi nhuận cao nhất");
        System.out.println("8. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            showMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handleCategoryMenu(scanner);
                    break;
                case 2:
                    handleProductMenu(scanner);
                    break;
                case 3:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 3);
        scanner.close();
    }
    private static void handleCategoryMenu(Scanner scanner) {
        int choice;
        do {
            showCategoryMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    getCategories();
                    break;
                case 2:
                    System.out.println("Nhap ten danh muc");
                    String categoryName = scanner.next();
                    createCategory(categoryName);
                    break;
                case 3:
                    System.out.print("Nhập ID danh mục cần cập nhật: ");
                    int categoryId = scanner.nextInt();
                    System.out.print("Nhập tên danh mục mới: ");
                    String newCategoryName = scanner.next();
                    System.out.print("Nhập trạng thái danh mục (1 hoặc 0): ");
                    int categoryStatus = scanner.nextInt();
                    updateCategory(categoryId, newCategoryName, categoryStatus);
                    break;
                case 4:
                    System.out.print("Nhập ID danh mục cần xóa: ");
                    int categoryIdToDelete = scanner.nextInt();
                    deleteCategory(categoryIdToDelete);
                    break;
                case 5:
                    System.out.print("Nhập ID danh mục để thống kê: ");
                    int idToCount = scanner.nextInt();
                    countProductByCategory(idToCount);
                    break;
                case 6:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không h��p lệ. Vui lòng thử lại.");
            }
        } while (choice != 6);
    }
    private static void handleProductMenu(Scanner scanner) {
        int choice;
        do{
            showProductMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    getProducts();
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Nhập tên sản phẩm: ");
                    String productName = scanner.nextLine();
                    //scanner.nextLine();
                    System.out.print("Nhập số lượng: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập giá nhập: ");
                    double costPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Nhập giá bán: ");
                    double sellingPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Nhập ID danh mục: ");
                    int categoryId = scanner.nextInt();
                    scanner.nextLine();
                    createProduct(productName, stock, costPrice, sellingPrice, categoryId);
                    break;
                case 3:
                    System.out.print("Nhập ID sản phẩm cần cập nhật: ");
                    int productId = scanner.nextInt();
                    System.out.print("Nhập tên sản phẩm mới: ");
                    String newProductName = scanner.next();
                    System.out.print("Nhập số lượng mới: ");
                    int newStock = scanner.nextInt();
                    System.out.print("Nhập giá nhập mới: ");
                    double newCostPrice = scanner.nextDouble();
                    System.out.print("Nhập giá bán mới: ");
                    double newSellingPrice = scanner.nextDouble();
                    updateProduct(productId, newProductName, newStock, newCostPrice,newSellingPrice);
                    break;
                case 4:
                    System.out.print("Nhập ID sản phẩm cần xóa: ");
                    int productIdToDelete = scanner.nextInt();
                    deleteProduct(productIdToDelete);
                    break;
                case 5:
                getProductsByDate();
                break;
                case 6:
                    System.out.print("Nhập giá tối thiểu: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Nhập giá tối đa: ");
                    double maxPrice = scanner.nextDouble();
                    searchProductsByPrice(minPrice, maxPrice);
                    break;
                case 7:
                    getTop3ProfitableProducts();
                    break;
                case 8:
                    System.out.println("Thoát khỏi menu sản phẩm.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 8);
    }
    private static void getCategories() {
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcGetCategories()}")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("category_id") + ", Tên: " + rs.getString("category_name") + ", Trạng thái: " + rs.getInt("category_status"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    private static void createCategory(String categoryName) {
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcCreateCategory(?)}")) {
            stmt.setString(1, categoryName);
            stmt.execute();
            System.out.println("Tạo mới danh mục thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    private static void updateCategory(int categoryId, String categoryName, int categoryStatus){
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcUpdateCategory(?,?,?)}")) {
            stmt.setInt(1, categoryId);
            stmt.setString(2, categoryName);
            stmt.setInt(3, categoryStatus);
            stmt.execute();
            System.out.println("Cập nhật danh mục thành công.");
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
    private static void deleteCategory(int categoryID){
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcDeleteCategory(?)}")) {
            stmt.setInt(1, categoryID);
            stmt.execute();
            System.out.println("Xóa danh mục thành công.");
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
    private static void countProductByCategory(int categoryId) {
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcCountProductsByCategory(?)}")) {
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Số lượng sản phẩm trong danh mục ID " + categoryId + ": " + rs.getInt("product_count"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void getProducts() {
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcGetProducts()}")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("product_id") + ", Tên: " + rs.getString("product_name") +
                        ", Số lượng: " + rs.getInt("stock") + ", Giá nhập: " + rs.getDouble("cost_price") +
                        ", Giá bán: " + rs.getDouble("selling_price"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void createProduct(String productName, int stock, double costPrice, double sellingPrice, int categoryId) {
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcCreateProduct(?, ?, ?, ?, ?)}")) {
            stmt.setString(1, productName);
            stmt.setInt(2, stock);
            stmt.setDouble(3, costPrice);
            stmt.setDouble(4, sellingPrice);
            stmt.setInt(5, categoryId);
            stmt.execute();
            System.out.println("Sản phẩm đã được tạo thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    private static void updateProduct(int productId, String productName, int stock, double costPrice, double sellingPrice) {
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcUpdateProduct(?, ?, ?, ?, ?)}")) {
            stmt.setInt(1, productId);
            stmt.setString(2, productName);
            stmt.setInt(3, stock);
            stmt.setDouble(4, costPrice);
            stmt.setDouble(5, sellingPrice);
            stmt.execute();
            System.out.println("Sản phẩm đã được cập nhật thành công.");
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    private static void deleteProduct(int productId) {
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcDeleteProduct(?)}")) {
            stmt.setInt(1, productId);
            stmt.execute();
            System.out.println("Sản phẩm đã được xóa thành công.");
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }

    }
    private static void getProductsByDate(){
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcGetProductsByDate()}")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("product_id") + ", Tên: " + rs.getString("product_name") +
                        ", Ngày tạo: " + rs.getDate("created_at"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    private static void searchProductsByPrice(double minPrice, double maxPrice) {
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcSearchProductsByPrice(?, ?)}")) {
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("product_id") + ", Tên: " + rs.getString("product_name") +
                        ", Giá bán: " + rs.getDouble("selling_price"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    private static void getTop3ProfitableProducts() {
        try (Connection conn = connect(); CallableStatement stmt = conn.prepareCall("{call ProcTop3ProfitableProducts()}")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Tên sản phẩm: " + rs.getString("product_name") + ", Lợi nhuận: " + rs.getDouble("profit"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}


