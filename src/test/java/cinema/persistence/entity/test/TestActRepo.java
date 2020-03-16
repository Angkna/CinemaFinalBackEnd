package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cinema.persistence.repository.ActRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class TestActRepo {

	
	@Autowired
	ActRepository actRepository;
	
	@Test
	void test() {
		var res = actRepository.findByMovieIdMovie(15);
		System.out.println(res);
	}

}
