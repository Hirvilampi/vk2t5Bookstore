package viikko2.bookstore_t5;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.junit.jupiter.api.Test;

import viikko2.bookstore_t5.domain.Book;
import viikko2.bookstore_t5.domain.BookRepository;
import viikko2.bookstore_t5.domain.Category;
import viikko2.bookstore_t5.domain.CategoryRepository;

@DataJpaTest
// @SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByAuthorShouldReturnTitle() {
        List<Book> books = bookRepository.findByAuthor("Don Rosa");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Aku Annka");
    }

    @Test
    public void createNewBook() {
        Category category = new Category("Horror");
        categoryRepository.save(category);
        Book book = new Book("Raamattu", "Papit", category);
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull(); // tarkistaa, että kirja on tallennettu repositorioon
    }

    @Test
    public void deleteBook() {
        List<Book> books = bookRepository.findByTitle("Aku Annka");
        Book book = books.get(0);
        bookRepository.delete(book);
        List<Book> newBooks = bookRepository.findByTitle("Aku Annka");
        assertThat(newBooks).hasSize(0);
    }

    @Test
    public void createNewCategory() {
        Category category = new Category("Horror");
        categoryRepository.save(category);
        Book book = new Book("Raamattu", "Papit", category);
        bookRepository.save(book);
        assertThat(book.getCategory().getName()).isEqualTo("Horror");

    }

    @Test
    public void getCorrectData() {
        Optional<Book> book = bookRepository.findById((long) 1);
        assertThat(book).isPresent(); // että book:ssa on tietoa
        assertThat(book.get().getAuthor()).isEqualTo("Don Rosa");
        assertThat(book.get().getTitle()).isEqualTo("Aku Annka");
    }

}
