package whu.edu.Product.Management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import whu.edu.Product.Management.Models.Product;
import whu.edu.Product.Management.controller.ProductController;


import java.net.URI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductManagementApplicationTests {

	@Value(value="${local.server.port}")
	private int port;
	@Autowired
	private ProductController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void getShouldReturnNoContent() {

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/1", Product.class)).isEqualTo(new Product());
	}



}
