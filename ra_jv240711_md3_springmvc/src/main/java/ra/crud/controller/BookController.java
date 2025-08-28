package ra.crud.controller;

import org.springframework.beans.factory.annotation.Autowired; // Được sử dụng để tự động inject dependencies
import org.springframework.stereotype.Controller; // Đánh dấu lớp này là một Controller trong Spring MVC
import org.springframework.ui.Model; // Được sử dụng để truyền dữ liệu từ Controller đến View
import org.springframework.web.bind.annotation.GetMapping; // Đánh dấu phương thức xử lý yêu cầu GET
import org.springframework.web.bind.annotation.PostMapping; // Đánh dấu phương thức xử lý yêu cầu POST
import org.springframework.web.bind.annotation.RequestMapping; // Đánh dấu lớp hoặc phương thức với một đường dẫn URL
import org.springframework.web.servlet.ModelAndView; // Được sử dụng để trả về một đối tượng bao gồm dữ liệu và view
import ra.crud.model.Book; // Lớp Book đại diện cho đối tượng sách
import ra.crud.service.BookService; // Lớp interface BookService cung cấp các phương thức nghiệp vụ
import ra.crud.service.imp.BookServiceImp; // Lớp triển khai BookService, nơi chứa logic nghiệp vụ thực tế

import java.util.List; // Dùng để lưu danh sách sách

// Đánh dấu đây là một Controller của Spring MVC, và tất cả các yêu cầu bắt đầu với "/bookController" sẽ được chuyển đến đây
@Controller
@RequestMapping("/bookController")
//@Controller:
//
//Đánh dấu lớp này là một controller trong Spring MVC, chịu trách nhiệm xử lý các yêu cầu HTTP.
//Spring sẽ quét lớp này khi khởi động ứng dụng nhờ vào cấu hình @ComponentScan.
//@RequestMapping("/bookController"):
//
//Định nghĩa tiền tố cho các URL. Ví dụ, mọi yêu cầu bắt đầu với /bookController sẽ được định tuyến tới các phương thức trong lớp này.
//@Autowired:
//
//Spring sẽ tự động inject (tiêm) một bean của lớp BookServiceImp vào BookController để sử dụng.
public class BookController {

    private final BookService bookService; // Khai báo đối tượng BookService để gọi các phương thức trong service
    // @Autowired: Inject BookServiceImp vào BookController qua constructor
    @Autowired
    public BookController(BookServiceImp bookServiceImp) {
        this.bookService = bookServiceImp;
    }

    // Phương thức xử lý yêu cầu GET cho "/findAll", trả về danh sách sách (Xử lý yêu cầu GET tới /bookController/findAll.)
    @GetMapping("/findAll")
    public ModelAndView findAllBook() {
        ModelAndView mav = new ModelAndView("books"); // Khởi tạo đối tượng ModelAndView, view là "books"
        List<Book> listBooks = bookService.findAll(); // Gọi phương thức service để lấy danh sách sách
        mav.addObject("listBooks", listBooks); // Thêm danh sách sách vào model để chuyển đến view
        return mav; // Trả về ModelAndView chứa view và dữ liệu cần hiển thị
    }

    // Phương thức xử lý yêu cầu GET cho "/initCreate", trả về form tạo sách mới
    @GetMapping("/initCreate")
    public String initCreateBook(Model model) {
        // 1. Khởi tạo đối tượng Book mới để bind với form nhập liệu
        Book bookNew = new Book();
        // 2. Thêm đối tượng bookNew vào model để nó có thể được truy cập trong view
        model.addAttribute("bookNew", bookNew);
        // Trả về view "newBook" (form tạo sách mới)
        return "newBook";
    }

    // Phương thức xử lý yêu cầu POST cho "/create", tạo mới sách
    @PostMapping("/create")
    public String createBook(Book bookNew) {
        // Lưu thông tin sách mới vào cơ sở dữ liệu thông qua service
        boolean result = bookService.save(bookNew);
        if (result) {
            // Nếu lưu thành công, chuyển hướng đến trang "findAll" để xem danh sách sách
            return "redirect:findAll";
        }
        // Nếu có lỗi, trả về trang "error"
        return "error";
    }

    // Phương thức xử lý yêu cầu GET cho "/initUpdate", hiển thị form cập nhật sách
    @GetMapping("/initUpdate")
    public String initUpdateBook(Model model, int bookId) {
        // Tìm sách theo ID để hiển thị thông tin trong form cập nhật
        Book bookUpdate = bookService.findById(bookId);
        // Thêm đối tượng sách vào model để truy cập trong view
        model.addAttribute("bookUpdate", bookUpdate);
        // Trả về view "updateBook" (form cập nhật sách)
        return "updateBook";
    }

    // Phương thức xử lý yêu cầu POST cho "/update", cập nhật thông tin sách
    @PostMapping("/update")
    public String updateBook(Book bookUpdate) {
        // Cập nhật thông tin sách trong cơ sở dữ liệu
        boolean result = bookService.update(bookUpdate);
        if (result) {
            // Nếu cập nhật thành công, chuyển hướng đến trang "findAll" để xem danh sách sách
            return "redirect:findAll";
        }
        // Nếu có lỗi, trả về trang "error"
        return "error";
    }

    // Phương thức xử lý yêu cầu GET cho "/delete", xóa sách
    @GetMapping("/delete")
    public String deleteBook(int bookId) {
        // Xóa sách theo ID
        boolean result = bookService.delete(bookId);
        if (result) {
            // Nếu xóa thành công, chuyển hướng đến trang "findAll" để xem lại danh sách sách
            return "redirect:findAll";
        }
        // Nếu có lỗi, trả về trang "error"
        return "error";
    }
}