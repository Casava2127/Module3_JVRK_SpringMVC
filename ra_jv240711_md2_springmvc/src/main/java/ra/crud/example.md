Để làm một ví dụ lớn hơn và có nhiều chức năng hơn trong ứng dụng Spring MVC, tôi sẽ xây dựng một ứng dụng quản lý sách đơn giản. Dự án này sẽ có các tính năng cơ bản như thêm sách, hiển thị danh sách sách và tìm kiếm sách. Dưới đây là cấu trúc thư mục, các lớp và phân tích chi tiết về luồng chạy của Spring MVC.
https://tutorialspedia.com/how-java-spring-mvc-works-spring-mvc-request-flow-explained-step-by-step/
### 1. Cấu trúc thư mục của dự án

```
book-app
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── bookapp
│   │   │               ├── config
│   │   │               │   ├── AppConfig.java
│   │   │               │   └── WebConfig.java
│   │   │               ├── controller
│   │   │               │   ├── BookController.java
│   │   │               │   └── SearchController.java
│   │   │               ├── model
│   │   │               │   └── Book.java
│   │   │               ├── repository
│   │   │               │   └── BookRepository.java
│   │   │               ├── service
│   │   │               │   └── BookService.java
│   │   │               └── util
│   │   │                   └── Util.java
│   │   └── resources
│   │       └── views
│   │           ├── book-list.jsp
│   │           ├── add-book.jsp
│   │           └── search-book.jsp
│   └── webapp
│       └── WEB-INF
│           └── web.xml
```

### 2. Giải thích các lớp trong dự án

#### a. **AppConfig.java** (Cấu hình Spring)

```java
package com.example.bookapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.bookapp")
public class AppConfig {

    // Cấu hình ViewResolver cho JSP
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/"); // Đường dẫn tới thư mục chứa các file .jsp
        resolver.setSuffix(".jsp"); // Định dạng của các view (JSP)
        return resolver;
    }
}
```

#### b. **WebConfig.java** (Cấu hình DispatcherServlet)

```java
package com.example.bookapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class}; // Cấu hình của Spring MVC
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // Mapping để DispatcherServlet xử lý các yêu cầu
    }
}
```

#### c. **BookController.java** (Controller xử lý yêu cầu liên quan đến sách)

```java
package com.example.bookapp.controller;

import com.example.bookapp.model.Book;
import com.example.bookapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    // Hiển thị danh sách sách
    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book-list"; // Chuyển tới view book-list.jsp
    }

    // Hiển thị form thêm sách
    @GetMapping("/add-book")
    public String showAddBookForm() {
        return "add-book"; // Chuyển tới view add-book.jsp
    }

    // Xử lý thêm sách mới
    @PostMapping("/add-book")
    public String addBook(Book book) {
        bookService.addBook(book); // Lưu sách vào cơ sở dữ liệu
        return "redirect:/books"; // Sau khi thêm sách, chuyển hướng về danh sách sách
    }
}
```

#### d. **SearchController.java** (Controller xử lý yêu cầu tìm kiếm sách)

```java
package com.example.bookapp.controller;

import com.example.bookapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    @Autowired
    private BookService bookService;

    // Xử lý yêu cầu tìm kiếm sách
    @GetMapping("/search-book")
    public String searchBooks(String query, Model model) {
        model.addAttribute("books", bookService.searchBooks(query)); // Tìm kiếm sách theo từ khóa
        return "search-book"; // Chuyển tới view search-book.jsp
    }
}
```

#### e. **Book.java** (Model đại diện cho một cuốn sách)

```java
package com.example.bookapp.model;

public class Book {
    private String title;
    private String author;
    private String isbn;

    // Constructor, Getter và Setter
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    // Getter and Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
```

#### f. **BookRepository.java** (Repository giả lập lưu trữ sách)

```java
package com.example.bookapp.repository;

import com.example.bookapp.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private List<Book> books = new ArrayList<>();

    // Thêm sách vào danh sách
    public void addBook(Book book) {
        books.add(book);
    }

    // Lấy danh sách sách
    public List<Book> getAllBooks() {
        return books;
    }

    // Tìm kiếm sách theo từ khóa
    public List<Book> searchBooks(String query) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().contains(query) || book.getAuthor().contains(query)) {
                result.add(book);
            }
        }
        return result;
    }
}
```

#### g. **BookService.java** (Service xử lý logic liên quan đến sách)

```java
package com.example.bookapp.service;

import com.example.bookapp.model.Book;
import com.example.bookapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Thêm sách vào repository
    public void addBook(Book book) {
        bookRepository.addBook(book);
    }

    // Lấy danh sách sách từ repository
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    // Tìm kiếm sách từ repository
    public List<Book> searchBooks(String query) {
        return bookRepository.searchBooks(query);
    }
}
```### Phân tích chi tiết luồng chạy của ứng dụng Spring MVC

Chúng ta sẽ phân tích luồng chạy chi tiết hơn dựa trên ví dụ ứng dụng quản lý sách, bắt đầu từ khi người dùng gửi yêu cầu đến khi hệ thống trả về kết quả.

### 1. **Khi người dùng truy cập vào URL `/books` để xem danh sách sách**

**Các bước:**

1. **Lệnh gửi yêu cầu:**  
   Người dùng gửi một yêu cầu HTTP GET đến `/books`.

2. **DispatcherServlet tiếp nhận yêu cầu:**  
   `DispatcherServlet` sẽ tiếp nhận tất cả các yêu cầu HTTP từ người dùng và quyết định Controller nào sẽ xử lý yêu cầu này dựa trên cấu hình Spring (các URL mapping). Trong trường hợp này, yêu cầu `/books` sẽ được chuyển đến `BookController` vì nó được ánh xạ trong Controller của chúng ta.

3. **Controller xử lý yêu cầu:**  
   Trong `BookController`, phương thức `showBooks()` sẽ được gọi. 
   - Spring MVC sẽ tự động inject đối tượng `BookService` vào controller (vì chúng ta đã sử dụng annotation `@Autowired`).
   - Phương thức này sẽ gọi `bookService.getAllBooks()` để lấy tất cả các cuốn sách từ `BookRepository`.

4. **Service gọi Repository:**  
   `bookService.getAllBooks()` sẽ gọi `bookRepository.getAllBooks()` để lấy danh sách các cuốn sách từ `BookRepository`. 
   - Ở đây, dữ liệu có thể được giả lập trong bộ nhớ với một danh sách đơn giản (ở ví dụ này, chúng ta đang lưu trữ sách trong bộ nhớ thay vì cơ sở dữ liệu thực tế).

5. **Trả dữ liệu cho View:**  
   Sau khi lấy danh sách sách từ `BookRepository`, controller sẽ thêm danh sách vào `Model`:
   ```java
   model.addAttribute("books", bookService.getAllBooks());
   ```
Model này chứa dữ liệu cần hiển thị trong view.

6. **DispatcherServlet tìm View:**  
   `DispatcherServlet` sẽ tìm kiếm view phù hợp để hiển thị kết quả. Dựa trên cấu hình trong `viewResolver` của Spring (`/WEB-INF/views/` với suffix `.jsp`), Spring sẽ tìm file `book-list.jsp` trong thư mục `WEB-INF/views/`.

7. **View trả kết quả cho người dùng:**  
   Cuối cùng, file `book-list.jsp` sẽ được trả về cho người dùng. Trang web này sẽ hiển thị danh sách sách trong bảng, sử dụng JSTL để lặp qua các đối tượng `books` mà controller đã thêm vào model:
   ```jsp
   <c:forEach var="book" items="${books}">
       <tr>
           <td>${book.title}</td>
           <td>${book.author}</td>
           <td>${book.isbn}</td>
       </tr>
   </c:forEach>
   ```

### 2. **Khi người dùng truy cập vào URL `/add-book` để thêm sách mới**

**Các bước:**

1. **Lệnh gửi yêu cầu:**  
   Người dùng truy cập vào URL `/add-book` thông qua trình duyệt của họ (HTTP GET).

2. **DispatcherServlet tiếp nhận yêu cầu:**  
   Tương tự như bước 1 ở trên, `DispatcherServlet` tiếp nhận yêu cầu và chuyển nó đến `BookController`.

3. **Controller xử lý yêu cầu:**  
   Phương thức `showAddBookForm()` trong `BookController` sẽ được gọi, và trả về view `add-book.jsp`. Đây là form HTML để người dùng nhập thông tin sách mới.

4. **View trả kết quả cho người dùng:**  
   `add-book.jsp` sẽ hiển thị form cho người dùng điền tên sách, tác giả, và ISBN. Người dùng sẽ nhập thông tin và nhấn nút "Submit".

### 3. **Khi người dùng gửi form thêm sách đến URL `/add-book`**

**Các bước:**

1. **Lệnh gửi yêu cầu:**  
   Người dùng điền thông tin sách vào form và gửi yêu cầu HTTP POST đến `/add-book` với các tham số `title`, `author`, và `isbn`.

2. **DispatcherServlet tiếp nhận yêu cầu:**  
   `DispatcherServlet` tiếp tục nhận yêu cầu này và chuyển tới `BookController`, nhưng lần này là phương thức `addBook(Book book)` sẽ được gọi. Spring tự động map các tham số từ form HTML vào đối tượng `Book` nhờ vào cơ chế dữ liệu binding.

3. **Controller xử lý yêu cầu:**  
   Phương thức `addBook()` sẽ nhận đối tượng `Book` đã được điền đầy đủ thông tin từ form.
    - Sau đó, controller sẽ gọi phương thức `bookService.addBook(book)` để lưu đối tượng `Book` vào `BookRepository`.

4. **Service gọi Repository:**  
   Trong `BookService`, phương thức `addBook(Book book)` sẽ gọi `bookRepository.addBook(book)` để lưu sách vào bộ nhớ (hoặc cơ sở dữ liệu).

5. **Redirect:**  
   Sau khi thêm thành công sách, phương thức sẽ thực hiện một redirect về `/books` để người dùng có thể thấy danh sách sách đã được cập nhật. Điều này giúp tránh việc gửi lại form khi người dùng refresh trang.

6. **DispatcherServlet tiếp nhận redirect:**  
   `DispatcherServlet` nhận yêu cầu redirect tới `/books` và lặp lại quy trình đã phân tích ở bước 1 để hiển thị lại danh sách sách mới.

### 4. **Khi người dùng truy cập vào URL `/search-book` để tìm kiếm sách**

**Các bước:**

1. **Lệnh gửi yêu cầu:**  
   Người dùng truy cập vào trang tìm kiếm qua URL `/search-book` và nhập từ khóa tìm kiếm.

2. **DispatcherServlet tiếp nhận yêu cầu:**  
   Yêu cầu này được chuyển tới `SearchController` bởi `DispatcherServlet`.

3. **Controller xử lý yêu cầu:**  
   Trong `SearchController`, phương thức `searchBooks()` sẽ nhận tham số tìm kiếm từ form. Sau đó, nó sẽ gọi `bookService.searchBooks(query)` để tìm kiếm các sách có từ khóa trong tên sách hoặc tác giả.

4. **Service gọi Repository:**  
   Trong `BookService`, phương thức `searchBooks(query)` sẽ gọi `bookRepository.searchBooks(query)` để tìm kiếm sách trong bộ nhớ.

5. **Trả kết quả cho View:**  
   Kết quả tìm kiếm sẽ được thêm vào model và chuyển tới view `search-book.jsp`.

6. **View trả kết quả cho người dùng:**  
   Cuối cùng, `search-book.jsp` sẽ hiển thị danh sách các sách mà tìm kiếm trả về, tương tự như cách danh sách sách được hiển thị trong `book-list.jsp`.

### Tổng hợp Flow Spring MVC trong ứng dụng

1. **DispatcherServlet** là trung tâm, tiếp nhận và chuyển tiếp tất cả yêu cầu đến controller phù hợp.
2. **Controller** xử lý yêu cầu, gọi service để thực hiện logic nghiệp vụ (như lấy dữ liệu từ repository, thêm sách mới, tìm kiếm sách, v.v.).
3. **Service** là lớp chứa các nghiệp vụ logic, tương tác với repository.
4. **Repository** chịu trách nhiệm về dữ liệu, thường sẽ truy cập cơ sở dữ liệu hoặc lưu trữ trong bộ nhớ.
5. **Model** chứa dữ liệu mà controller muốn trả về cho view.
6. **View** (JSP) nhận dữ liệu từ model và hiển thị cho người dùng.

Spring MVC giúp tổ chức mã nguồn rõ ràng và tách biệt các trách nhiệm. Luồng xử lý giữa controller, service, repository và view tạo nên một ứng dụng dễ bảo trì, mở rộng và test.
### Giải thích chi tiết ba bước: **Service gọi Repository**, **Trả kết quả cho View**, và **View trả kết quả cho người dùng**

---

### 1. **Service gọi Repository:**

Trong Spring MVC, **Service** là lớp thực hiện các logic nghiệp vụ, và **Repository** chịu trách nhiệm truy xuất dữ liệu (từ cơ sở dữ liệu hoặc bộ nhớ). Khi người dùng thực hiện tìm kiếm sách, `BookService` sẽ tương tác với `BookRepository` để truy vấn dữ liệu.

#### Cách hoạt động:
- **Phương thức trong `BookService`:**
  Phương thức `searchBooks(query)` trong `BookService` nhận tham số `query`, đây là từ khóa tìm kiếm do người dùng nhập vào form tìm kiếm. Ví dụ: người dùng tìm kiếm sách có từ khóa "Java".

  ```java
  public List<Book> searchBooks(String query) {
      return bookRepository.searchBooks(query);
  }
  ```
    - `bookRepository.searchBooks(query)` sẽ gọi phương thức trong `BookRepository` để tìm kiếm các sách thỏa mãn điều kiện tìm kiếm.

- **Cách gọi `BookRepository`:**
  Trong `BookRepository`, phương thức `searchBooks(query)` có thể tìm kiếm trong một danh sách sách đã lưu trong bộ nhớ (hoặc cơ sở dữ liệu thực tế nếu kết nối với DB). Ví dụ:

  ```java
  public List<Book> searchBooks(String query) {
      List<Book> results = new ArrayList<>();
      for (Book book : books) {
          if (book.getTitle().contains(query) || book.getAuthor().contains(query)) {
              results.add(book);
          }
      }
      return results;
  }
  ```

    - Ở đây, `searchBooks(query)` sẽ duyệt qua danh sách các sách trong bộ nhớ và trả về những sách có tiêu đề hoặc tác giả chứa từ khóa tìm kiếm.

- **Trả kết quả từ Repository đến Service:**
  Phương thức `bookRepository.searchBooks(query)` sẽ trả về một danh sách các đối tượng `Book` (sách) phù hợp với từ khóa tìm kiếm, và `BookService` tiếp nhận kết quả này để chuyển tiếp đến controller.

---

### 2. **Trả kết quả cho View:**

Sau khi có kết quả tìm kiếm từ `BookRepository`, **BookService** sẽ truyền kết quả này vào **Model** và gửi đến view (thường là JSP file) để hiển thị kết quả tìm kiếm cho người dùng.

#### Cách hoạt động:
- Trong **controller**, sau khi `BookService` trả về danh sách các sách tìm được từ `BookRepository`, controller sẽ thêm danh sách sách vào model và chuyển hướng đến view. Ví dụ:

  ```java
  @GetMapping("/search-book")
  public String searchBooks(@RequestParam("query") String query, Model model) {
      List<Book> searchResults = bookService.searchBooks(query);
      model.addAttribute("books", searchResults);  // Thêm kết quả vào model
      return "search-book";  // Trả về view "search-book.jsp"
  }
  ```
    - Phương thức `addAttribute("books", searchResults)` sẽ thêm danh sách sách tìm được vào đối tượng `model` với tên là `"books"`. Model này sẽ được sử dụng trong view để hiển thị dữ liệu.

- **Model**:
    - `Model` trong Spring MVC là một đối tượng chứa dữ liệu mà controller muốn gửi cho view. Khi controller trả về view, model sẽ chứa tất cả các thông tin cần thiết để view hiển thị.

- **Quá trình trả kết quả**:
    - Khi controller trả về `"search-book"`, Spring MVC sẽ tìm kiếm view có tên là `search-book.jsp` trong thư mục cấu hình view (thường là `/WEB-INF/views/`).

---

### 3. **View trả kết quả cho người dùng:**

Cuối cùng, sau khi controller đã thêm dữ liệu vào model và gửi đến view, **View** (JSP) sẽ sử dụng thông tin từ model để hiển thị kết quả tìm kiếm cho người dùng.

#### Cách hoạt động:
- **JSP sử dụng JSTL để hiển thị dữ liệu:**
  Trong `search-book.jsp`, chúng ta sẽ sử dụng JSTL (JavaServer Pages Standard Tag Library) để hiển thị danh sách sách. Ví dụ:

  ```jsp
  <h2>Results for "${query}"</h2>
  <table>
      <thead>
          <tr>
              <th>Title</th>
              <th>Author</th>
              <th>ISBN</th>
          </tr>
      </thead>
      <tbody>
          <c:forEach var="book" items="${books}">
              <tr>
                  <td>${book.title}</td>
                  <td>${book.author}</td>
                  <td>${book.isbn}</td>
              </tr>
          </c:forEach>
      </tbody>
  </table>
  ```
    - **`${books}`** là biến chứa danh sách các sách mà controller đã gửi vào model.
    - `c:forEach` là tag JSTL dùng để lặp qua các đối tượng trong danh sách `books` và hiển thị thông tin của từng cuốn sách trong bảng.
    - Mỗi sách sẽ có các thuộc tính như `title`, `author`, và `isbn` được hiển thị trong các cột của bảng.

- **Kết quả hiển thị:**
  Khi người dùng thực hiện tìm kiếm, sau khi gửi yêu cầu và nhận kết quả từ controller, trang `search-book.jsp` sẽ được hiển thị với danh sách các sách tìm thấy từ cơ sở dữ liệu hoặc bộ nhớ. Người dùng sẽ thấy danh sách các sách phù hợp với từ khóa tìm kiếm.

---

### Tổng kết:

- **Service gọi Repository:** Khi người dùng nhập từ khóa tìm kiếm, controller sẽ gọi `BookService.searchBooks(query)`, mà bên trong `BookService` sẽ gọi `BookRepository.searchBooks(query)` để tìm kiếm các sách phù hợp với từ khóa trong bộ nhớ (hoặc cơ sở dữ liệu).

- **Trả kết quả cho View:** Kết quả từ `BookService` (danh sách sách tìm được) sẽ được đưa vào `Model`, sau đó controller trả về view `search-book.jsp`.

- **View trả kết quả cho người dùng:** Cuối cùng, trong view `search-book.jsp`, các sách tìm được sẽ được hiển thị trong bảng bằng cách sử dụng JSTL để lặp qua các đối tượng sách trong model và hiển thị các thuộc tính của mỗi cuốn sách (tên, tác giả, ISBN).

Quá trình này giúp người dùng dễ dàng tìm kiếm sách và nhận được kết quả ngay lập tức mà không phải làm thủ công.

#### h. **book-list.jsp** (View hiển thị danh sách sách)

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Book List</title>
</head>
<body>
    <h1>Book List</h1>
    <a href="/add-book">Add New Book</a> <!-- Link đến form thêm sách mới -->
    <table border="1">
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
        </tr>
        <!-- Lặp qua danh sách sách và hiển thị -->
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```

#### i. **add-book.jsp** (View hiển thị form thêm sách)

```jsp
<html>
<head>
    <title>Add New Book</title>
</head>
<body>
    <h1>Add New Book</h1>
    <form action="/add-book" method="post">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title

" required><br><br>
        
        <label for="author">Author:</label>
        <input type="text" id="author" name="author" required><br><br>
        
        <label for="isbn">ISBN:</label>
        <input type="text" id="isbn" name="isbn" required><br><br>
        
        <input type="submit" value="Add Book">
    </form>
</body>
</html>
```

#### j. **search-book.jsp** (View tìm kiếm sách)

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Search Books</title>
</head>
<body>
    <h1>Search Books</h1>
    <form action="/search-book" method="get">
        <label for="query">Search Query:</label>
        <input type="text" id="query" name="query" required><br><br>
        <input type="submit" value="Search">
    </form>

    <h2>Search Results</h2>
    <table border="1">
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
        </tr>
        <!-- Lặp qua kết quả tìm kiếm -->
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```

### 3. Luồng chạy của ứng dụng

1. **DispatcherServlet** nhận các yêu cầu HTTP và chuyển chúng tới các **Controller**.
2. Ví dụ, khi người dùng truy cập `/books`, **BookController** sẽ xử lý yêu cầu này, gọi **BookService** để lấy tất cả sách và trả về một danh sách sách trong **Model**.
3. **DispatcherServlet** sau đó sẽ tìm kiếm một **View** (ví dụ `book-list.jsp`) để hiển thị kết quả.
4. Nếu người dùng muốn thêm sách, họ có thể truy cập `/add-book`, điền thông tin và gửi form. **BookController** sẽ nhận dữ liệu và lưu vào **BookRepository**.
5. Tương tự, yêu cầu tìm kiếm sách sẽ được xử lý bởi **SearchController**, và kết quả tìm kiếm sẽ được hiển thị trong `search-book.jsp`.

### Kết luận

Ứng dụng Spring MVC này giúp chúng ta tổ chức mã nguồn theo các mô-đun rõ ràng: Controller (xử lý yêu cầu), Service (logic nghiệp vụ), Repository (lưu trữ dữ liệu), và View (giao diện người dùng). Luồng chạy rất rõ ràng và tách biệt, giúp dễ dàng quản lý và mở rộng ứng dụng trong tương lai.