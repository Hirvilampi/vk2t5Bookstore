package viikko2.bookstore_t5.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;

public class Message {
	@NotNull(message = "EI VOI can't be empty")
	private Long id;

	@Size(min = 5, max = 30, message="too martin shorty")
	private String msg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Message [msg=" + msg + "]";
	}
}
