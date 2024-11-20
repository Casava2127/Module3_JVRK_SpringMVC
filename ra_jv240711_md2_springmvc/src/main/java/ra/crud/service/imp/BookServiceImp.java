package ra.crud.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.crud.model.Book;
import ra.crud.repository.BookRepository;
import ra.crud.repository.imp.BookRepositoryImp;
import ra.crud.service.BookService;

import java.util.List;

@Service
public class BookServiceImp implements BookService {
    //Tiêm instance của bean BookRepository đã được nạp trong container
    private final BookRepository bookRepository;

      @Autowired
    public BookServiceImp(BookRepositoryImp bookRepositoryImp) {
        this.bookRepository = bookRepositoryImp;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public boolean save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean update(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public boolean delete(int bookId) {
        return bookRepository.delete(bookId);
    }
}
