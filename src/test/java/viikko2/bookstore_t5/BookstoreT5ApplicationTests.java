package viikko2.bookstore_t5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import viikko2.bookstore_t5.domain.BookRepository;

@SpringBootTest
class BookstoreT5ApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Test
	void contextLoads() {
	}

	// testataan yhteys tietokantaan
	@Test
	public void TestDataBaseConnection() {
		assertThat(bookRepository).isNotNull(); // varmistetaan, että bookrepository ei ole tyhjä
		assertThat(bookRepository.count()).isNotNull(); // tehdään kysely ja varmistetaan ettei tuu virhettä
	}



}
