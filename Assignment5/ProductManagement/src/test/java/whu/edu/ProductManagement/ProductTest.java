package whu.edu.ProductManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.transaction.annotation.Transactional;
import whu.edu.ProductManagement.domain.Product;
import whu.edu.ProductManagement.exception.ProductManagementException;
import whu.edu.ProductManagement.service.IProductService;
import whu.edu.ProductManagement.service.impl.ProductServiceImpl;


@SpringBootTest
@Transactional
public class ProductTest {

    @Autowired
    private IProductService productService;

    @BeforeEach
    public void initProducts() throws ProductManagementException {
        Product product = new Product();
        product.setId(100L);
        product.setName("apple");
        product.setPrice(1.1f);
        product.setSupplier("harry");

        productService.addProduct(product);
    }

    @Test
    public void contextLoadTest() {
        assert (productService != null);
    }

    @Test
    public void getProductTest() throws ProductManagementException {

        Product testProduct = productService.getProductById(100L);
        assert (testProduct.getId().equals(100L));
        assert (testProduct.getName().equals("apple"));
        assert (testProduct.getSupplier().equals("harry"));

    }

    @Test
    public void removeProductTest() throws ProductManagementException {
        productService.removeProduct(100L);
        assert (productService.getProductById(100L) == null);
    }

    @Test
    public void updateProductTest() throws ProductManagementException {
        Product newProduct = new Product();
        newProduct.setId(100L);
        newProduct.setName("banana");
        newProduct.setPrice(2.2f);
        newProduct.setSupplier("potter");

        productService.updateProduct(newProduct);

        assert (productService.getProductById(100L).equals(newProduct));
    }




}
