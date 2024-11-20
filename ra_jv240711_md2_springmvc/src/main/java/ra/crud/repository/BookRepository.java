package ra.crud.repository;

import ra.crud.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();

    Book findById(int id);

    boolean save(Book book);

    boolean update(Book book);

    boolean delete(int bookId);
}
