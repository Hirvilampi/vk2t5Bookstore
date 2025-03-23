package viikko2.bookstore_t5;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import viikko2.bookstore_t5.domain.Book;
import viikko2.bookstore_t5.domain.BookRepository;
import viikko2.bookstore_t5.domain.Category;
import viikko2.bookstore_t5.domain.CategoryRepository;
import viikko2.bookstore_t5.domain.AppUser;
import viikko2.bookstore_t5.domain.AppUserRepository;

@SpringBootApplication
public class BookstoreT5Application {

	private static final Logger log = LoggerFactory.getLogger(BookstoreT5Application.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreT5Application.class, args);
	}

//	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository,
			CategoryRepository cRepository, AppUserRepository urepository) {
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

			// Create users: admin/admin user/user
//			String rawPassword = "user";
//			String encodedPassword = passwordEncoder.encode(rawPassword);
			String passuser1 = "$2y$10$rQgJQcJpLV0cBZiuibmRdO80ZPZiQJFwHQbcJ.g.MHUjIcFNQBNNm";
			AppUser user1 = new AppUser("user", passuser1, "USER");
			AppUser user2 = new AppUser("admin", "$2y$10$RtMvRumaP/ByXMVBRVO2SOH5X3GD3LpxTnXMOfuaG3zFB73WASWa6",
					"ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			log.info(user1.getUsername() + " " + user1.getPasswordHash());
			log.info(user2.getUsername() + " " + user2.getPasswordHash());

		};
	}

	@Bean
	public CommandLineRunner demo(CategoryRepository crepository) {
		return (args) -> {

		};
	}

	// @Bean
	// public UserDetailsService userDetailsService() {
	// UserDetails user = User.withDefaultPasswordEncoder()
	// .username("user")
	// .password("password")
	// .roles("USER")
	// .build();
	// List<UserDetails> users = new ArrayList();
	// users.add(user);
	// return new InMemoryUserDetailsManager(users);
	// }

}
