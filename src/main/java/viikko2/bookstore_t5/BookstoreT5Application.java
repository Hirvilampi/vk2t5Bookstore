package viikko2.bookstore_t5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import viikko2.bookstore_t5.domain.Book;
import viikko2.bookstore_t5.domain.BookRepository;

@SpringBootApplication
public class BookstoreT5Application {

	private static final Logger log = LoggerFactory.getLogger(BookstoreT5Application.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreT5Application.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("save a couple of students");
			repository.save(new Book("Aku Annka", "Don Johnson"));
			repository.save(new Book("Havukka-ahon ajattelija", "Veikko Huovinen"));

			repository.save(new Book("Naamio", "Adam Smithee"));

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

}
