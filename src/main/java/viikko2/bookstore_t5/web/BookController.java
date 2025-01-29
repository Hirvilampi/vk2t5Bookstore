package viikko2.bookstore_t5.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import viikko2.bookstore_t5.domain.Book;
import viikko2.bookstore_t5.domain.BookRepository;


@Controller
public class BookController {

    private static List<Book> bookList = new ArrayList<>();

    private BookRepository repository;

    public BookController(BookRepository repository){
        this.repository = repository;
    }

    @RequestMapping(value={"/","index"})
    public String showMainPage() {
        return "index";
    }

    @GetMapping("/addbook")
    public String addFriendForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("saveBook")
    // public String saveCar(@ModelAttribute Car car, Model model) {
    public String saveBook(Book book) {
        System.out.println("Database is not implemented : " + book);
        repository.save(book);
        // TODO save to database
        // now we are saving to list variable instead of db
        bookList.add(book);

        return "redirect:/newbook";
        // return "redirect:/carlist";
    }

    @GetMapping("/newbook")
    public String showNewFriend(Model model) {
        System.err.println("books...");
        model.addAttribute("books", bookList);
        return "result";
    }

    @GetMapping("/booklist")
    public String showCustomers(Model model) {
        System.out.println("books...");
        // insert book list to model as a key-value pair
        model.addAttribute("books", repository.findAll());
        return "books";
    }

}
