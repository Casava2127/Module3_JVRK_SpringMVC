package ra.crud.service.imp;

import org.springframework.beans.factory.annotation.Autowired; // Dùng để tiêm dependency tự động vào constructor
import org.springframework.stereotype.Service; // Đánh dấu lớp này là một Service của Spring
import ra.crud.model.Book; // Lớp đại diện cho đối tượng Book
import ra.crud.repository.BookRepository; // Interface repository chứa các phương thức truy cập dữ liệu cho Book
import ra.crud.repository.imp.BookRepositoryImp; // Lớp triển khai BookRepository, thực hiện các thao tác truy xuất dữ liệu
import ra.crud.service.BookService; // Interface BookService, khai báo các phương thức nghiệp vụ liên quan đến Book

import java.util.List; // Dùng để lưu danh sách các đối tượng Book

// Đánh dấu lớp này là một Service trong Spring, nơi chứa các logic nghiệp vụ của ứng dụng
@Service
public class BookServiceImp implements BookService {

    // Tiêm instance của Bean BookRepository đã được nạp trong container Spring
    // Sử dụng @Autowired để tiêm phụ thuộc, nhưng nên sử dụng tiêm qua constructor thay vì tiêm qua setter.
    private final BookRepository bookRepository;

    // Constructor nhận BookRepositoryImp (thực thi của BookRepository) để tiêm vào BookServiceImp
    @Autowired
    public BookServiceImp(BookRepositoryImp bookRepositoryImp) {
        this.bookRepository = bookRepositoryImp; // Gán BookRepositoryImp vào bookRepository
    }

    // Phương thức tìm tất cả sách
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll(); // Gọi phương thức findAll từ repository để lấy danh sách sách
    }

    // Phương thức tìm sách theo ID
    @Override
    public Book findById(int bookId) {
        return bookRepository.findById(bookId); // Gọi phương thức findById từ repository để tìm sách theo ID
    }

    // Phương thức lưu sách mới
    @Override
    public boolean save(Book book) {
        return bookRepository.save(book); // Gọi phương thức save từ repository để lưu sách vào cơ sở dữ liệu
    }

    // Phương thức cập nhật thông tin sách
    @Override
    public boolean update(Book book) {
        return bookRepository.update(book); // Gọi phương thức update từ repository để cập nhật thông tin sách
    }

    // Phương thức xóa sách
    @Override
    public boolean delete(int bookId) {
        return bookRepository.delete(bookId); // Gọi phương thức delete từ repository để xóa sách theo ID
    }
}