package demo;

import demo.dao.UserRepository;
import demo.domain.Product;
import demo.domain.User;
import io.jsonwebtoken.lang.Assert;
import jdk.jfr.Enabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
		assert (userRepository != null);
	}

	@Test
	void saveProduct() {
		User user = new User();
		user.setId("111");
		user.setPassword("1033");

		userRepository.saveAndFlush(user);
	}

}
