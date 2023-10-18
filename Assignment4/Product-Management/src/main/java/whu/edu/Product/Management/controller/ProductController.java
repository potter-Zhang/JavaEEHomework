package whu.edu.Product.Management.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whu.edu.Product.Management.Models.Product;
import whu.edu.Product.Management.Services.ProductService;


@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(product);
        }
    }

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping("")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.removeProduct(id);
        return ResponseEntity.ok().build();
    }
}
