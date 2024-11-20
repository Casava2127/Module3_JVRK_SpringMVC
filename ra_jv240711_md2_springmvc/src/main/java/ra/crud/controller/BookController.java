package ra.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.crud.model.Book;
import ra.crud.service.BookService;
import ra.crud.service.imp.BookServiceImp;

import java.util.List;

@Controller
@RequestMapping("/bookController")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookServiceImp bookServiceImp) {
        this.bookService = bookServiceImp;
    }

    //    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @GetMapping("/findAll")
    public ModelAndView findAllBook() {
        ModelAndView mav = new ModelAndView("books");
        List<Book> listBooks = bookService.findAll();
        mav.addObject("listBooks", listBooks);
        return mav;
    }

    //Model, ModelView, ModelAndView: Hỗ trợ tương tác giữa controller và view

    @GetMapping("/initCreate")
    public String initCreateBook(Model model) {
        //1. Khởi tạo đối tượng Book để map lên form nhập liệu
        Book bookNew = new Book();
        //2. add vào model
        model.addAttribute("bookNew", bookNew);
        return "newBook";
    }

    @PostMapping("/create")
//    public String createBook(@ModelAttribute("bookNew") Book book) {
    public String createBook(Book bookNew) {
        boolean result = bookService.save(bookNew);
        if (result) {
            return "redirect:findAll";
        }
        return "error";
    }

    @GetMapping("/initUpdate")
//    public String initUpdateBook(Model model, @RequestParam("bookId") int id){
    public String initUpdateBook(Model model, int bookId) {
        Book bookUpdate = bookService.findById(bookId);
        model.addAttribute("bookUpdate", bookUpdate);
        return "updateBook";
    }

    @PostMapping("/update")
    public String updateBook(Book bookUpdate) {
        boolean result = bookService.update(bookUpdate);
        if (result) {
            return "redirect:findAll";
        }
        return "error";
    }

    @GetMapping("/delete")
    public String deleteBook(int bookId) {
        boolean result = bookService.delete(bookId);
        if (result) {
            return "redirect:findAll";
        }
        return "error";
    }
}
