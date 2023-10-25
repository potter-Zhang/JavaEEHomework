package whu.edu.Product.Management.Services;

import org.springframework.stereotype.Service;
import whu.edu.Product.Management.Models.Product;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {
    private Map<Long, Product> products = Collections.synchronizedMap(new HashMap<Long, Product>());

    public Product addProduct(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    public Product getProduct(Long id) {
        return products.get(id);
    }

    public void removeProduct(Long id) {
        products.remove(id);
    }

    public Product updateProduct(Product product) {
        removeProduct(product.getId());
        return addProduct(product);
    }
}
