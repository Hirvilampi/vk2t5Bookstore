package viikko2.bookstore_t5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import viikko2.bookstore_t5.domain.Book;
import viikko2.bookstore_t5.domain.BookRepository;
import viikko2.bookstore_t5.domain.Category;
import viikko2.bookstore_t5.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreT5Application {

	private static final Logger log = LoggerFactory.getLogger(BookstoreT5Application.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreT5Application.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository cRepository) {
		return (args) -> {
			log.info("create few categories");

			Category category1 = new Category("Comics");
			Category category2 = new Category("Fiction");
			Category category3 = new Category("Non-Fiction");

			cRepository.save(category1);
			cRepository.save(category2);
			cRepository.save(category3);

			log.info("save a couple of books");
			repository.save(new Book("Aku Annka", "Don Rosa", 1952, "243-11-1", 13.50, category1));
			repository.save(new Book("Havukka-ahon ajattelija", "Veikko Huovinen", category2));
			repository.save(new Book("Naamio", "Adam Smithee", category3));

			log.info("fetch all books");
			for (Book student : repository.findAll()) {
				log.info(student.toString());
			}
			log.info("haetaan kaikki Naamiot");
			for (Book student : repository.findByTitle("Naamio")) {
				log.info(student.toString());
			}

		};
	}

	@Bean
	public CommandLineRunner demo(CategoryRepository crepository) {
		return (args) -> {

		};
	}

}
