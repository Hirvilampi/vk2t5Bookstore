package viikko2.bookstore_t5.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import viikko2.bookstore_t5.domain.Book;
import viikko2.bookstore_t5.domain.BookRepository;
import viikko2.bookstore_t5.domain.Category;
import viikko2.bookstore_t5.domain.CategoryRepository;

@RestController
public class BookRestController {

    private static final Logger log = LoggerFactory.getLogger(BookRestController.class);

    private final BookRepository bookRepository;
    private CategoryRepository categoryRepository;

    public BookRestController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    // return list of book
    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        log.info("//fetch and return books");
        return bookRepository.findAll();
    }

    // add new book
    @PostMapping("books")
    Book newBook(@RequestBody Book newBook) {
        log.info("save new book " + newBook);
        return bookRepository.save(newBook);
    }

    // edit existing book info
    @PutMapping("/books/{id}")
    Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
        log.info("edit boo " + editedBook);
        editedBook.setId(id);
        return bookRepository.save(editedBook);
    }

    // delete existing book
    @DeleteMapping("/books/{id}")
    public Iterable<Book> deleteBook(@PathVariable Long id) {
        log.info("delete book, id " + id);
        bookRepository.deleteById(id);
        return bookRepository.findAll();
    }

    // find book and return info
    @GetMapping("/books/{id}")
    Optional<Book> getBook(@PathVariable Long id) {
        log.info("find book by id " + id);
        return bookRepository.findById(id);
    }

    // find one car with specific title
    @GetMapping("/books/title/{title}")
    List<Book> getBookByTitle(@PathVariable String title) {
        log.info("find book by title " + title);
        return bookRepository.findByTitle(title);
    }

    // find one car with specific title
    @GetMapping("/books/author/{author}")
    List<Book> getBookByAuthor(@PathVariable String author) {
        log.info("find book by author " + author);
        return bookRepository.findByAuthor(author);
    }

    // find by category name
    @GetMapping("/category/{name}")
    List<Book> getCategoryByName(@PathVariable String name) {
        log.info("find category by name " + name);
        return bookRepository.findByCategoryName(name);
    }

    // find by price less than given amount
    @GetMapping("/pricelessthan/{price}")
    List<Book> getBooksPriceLessThan(@PathVariable double price) {
        log.info("find books by price less than " + price);
        return bookRepository.findByPriceLessThan(price);
    }

    // find by price in given range
    @GetMapping("/pricerange/{price}/{price2}")
    List<Book> getBooksPriceRange(@PathVariable double price, @PathVariable double price2) {
        log.info("find books by price less than " + price);
        return bookRepository.findByPriceBetween(price, price2);
    }

}
