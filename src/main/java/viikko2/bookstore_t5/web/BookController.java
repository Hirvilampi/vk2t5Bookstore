package viikko2.bookstore_t5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import viikko2.bookstore_t5.domain.Book;
import viikko2.bookstore_t5.domain.BookRepository;
import viikko2.bookstore_t5.domain.Category;
import viikko2.bookstore_t5.domain.CategoryRepository;
import viikko2.bookstore_t5.domain.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Configuration
@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private CategoryRepository crepository;

	// login to show books
    @RequestMapping(value={"/login","/"})
    public String login() {	
        return "login";
    }	

    @RequestMapping(value = {"booklist", "/booklist" })
    public String showCustomers(Model model) {
        model.addAttribute("books", repository.findAll());
        return "bookList";
    }

    @RequestMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("categories", crepository.findAll());
            return "addbook";
        } else {
            repository.save(book);
            return "redirect:booklist";
        }

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

    @RequestMapping("/editbook/{id}")
    public String editBookForm(@PathVariable("id") Long bookId, Model model) {
        Book book = repository.findById(bookId).orElse(null);
        model.addAttribute("book", book);
        model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }

    @RequestMapping(value = "/editsave/{id}", method = RequestMethod.POST)
    public String editBook(@Valid @ModelAttribute Book book, BindingResult bindingResult,
            @PathVariable("id") Long bookId) {
        if (bindingResult.hasErrors()) {
            return "editbook";
        } else {
            book.setId(bookId); // Ensure the book ID is set
            repository.save(book);
            return "redirect:../booklist";
        }

    }

}
