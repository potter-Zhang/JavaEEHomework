package Assignment6.Demo;

import Assignment6.Demo.Api.UserService;
import Assignment6.Demo.Domain.User;
import Assignment6.Demo.Monitor.ApiMonitor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
		assert (userService != null);
	}

	@Test
	void fewApiCallTest() throws Exception {
		User user1 = new User();
		user1.setName("harry");
		user1.setAge(10);
		userService.addUser(user1);

		User user2 = new User();
		user1.setName("potter");
		user1.setAge(20);
		userService.addUser(user1);

		User user3 = new User();
		user1.setName("Mary");
		user1.setAge(30);
		userService.addUser(user1);

		userService.getUser(0);

		userService.deleteUser(user1);
		userService.deleteUser(user2);
		userService.deleteUser(user3);
		userService.deleteUser(user1);

		ApiMonitor.showDatas();
	}
	@Test
	public void massiveApiCallTest() throws Exception {
		User user = new User();
		user.setName("harry");
		for (int i = 0; i < 1000; i++) {
			user.setAge(i);
			userService.addUser(user);
		}

		for (int i = 0; i < 1000; i++) {
			userService.getUser(i);
		}

		for (int i = 0; i < 1000; i++) {
			userService.sumAge();
		}


		for (int i = 1000; i < 1010; i++) {
			int finalI = i;
			assertThrows(RuntimeException.class, () -> userService.getUser(finalI));
		}

		for (int i = 0; i < 1000; i++) {
			user.setAge(i);
			userService.deleteUser(user);
		}
		ApiMonitor.showDatas();


	}

}
