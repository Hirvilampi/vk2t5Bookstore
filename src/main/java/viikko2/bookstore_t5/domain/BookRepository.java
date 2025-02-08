package viikko2.bookstore_t5.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByTitle(String title);

}
