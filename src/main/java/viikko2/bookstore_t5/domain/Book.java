package viikko2.bookstore_t5.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import viikko2.bookstore_t5.domain.Category;
import jakarta.persistence.Table;

@Entity
@Table( name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    @NotEmpty(message = "must have title")
    @Size(min = 3, max = 100, message = "must be between 3-100 characters")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "can't be empty")
    @Size(min = 3, max = 100, message = "must be between 3-100 characters")
    private String author;

    @Column(name = "publicationyear")
    private Integer publicationYear;

    @Column(name = "isbn")
    @Size(max=100)
    private String isbn;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categoryid")
    private Category category;

    public Book() {
    }

    public Book(String title, String author, Integer publicationYear, String isbn, double price, Category category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
        this.category = category;
    }

    public Book(String title, String author, Category category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getFormattedPrice() {
        return price != null ? String.format("%.2f €", price) : "N/A";
    }

    @Override
    public String toString() {
        if (this.category != null) {
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", category=" + category
                    + ", publicationYear=" + publicationYear + ", isbn=" + isbn + ", price=" + price + "]";
        } else {
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
                    + ", isbn=" + isbn + ", price=" + price + "]";
        }
    }
}