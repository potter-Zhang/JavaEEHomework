package whu.edu.ProductManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.transaction.annotation.Transactional;
import whu.edu.ProductManagement.dao.ProductDao;
import whu.edu.ProductManagement.domain.Product;
import whu.edu.ProductManagement.domain.Supplier;
import whu.edu.ProductManagement.exception.ProductManagementException;
import whu.edu.ProductManagement.service.IProductService;
import whu.edu.ProductManagement.service.ISupplierService;
import whu.edu.ProductManagement.service.impl.ProductServiceImpl;

import java.util.List;


@SpringBootTest
@Transactional
public class ProductTest {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private IProductService productService;
    @Autowired
    private ISupplierService supplierService;








}
