package viikko2.bookstore_t5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import viikko2.bookstore_t5.domain.Book;
import viikko2.bookstore_t5.domain.BookRepository;

@Controller
public class BookController {

    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = { "/", "booklist" })
    public String showCustomers(Model model) {
        model.addAttribute("books", repository.findAll());
        return "bookList";
    }

    @RequestMapping("/addbook")
    public String addFriendForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

    @RequestMapping("/editbook/{id}")
    public String editBookForm(@PathVariable("id") Long bookId, Model model) {
        Book book = repository.findById(bookId).orElse(null);
        model.addAttribute("book", book);
        return "editbook";
    }

    @RequestMapping(value = "/editsave/{id}", method = RequestMethod.POST)
    public String editBook(@PathVariable("id") Long bookId, @ModelAttribute Book book) {
        book.setId(bookId); // Ensure the book ID is set
        repository.save(book);
        return "redirect:../booklist";
    }

}
