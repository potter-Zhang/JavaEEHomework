package whu.edu.ProductManagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import whu.edu.ProductManagement.domain.Product;
import whu.edu.ProductManagement.exception.ProductManagementException;
import whu.edu.ProductManagement.service.IProductService;

import javax.xml.transform.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author little prince
 * @since 2023-10-25
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(Long id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(product);
            }
        } catch (ProductManagementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.addProduct(product));
        } catch (ProductManagementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        try {

            return ResponseEntity.ok(productService.updateProduct(product));

        } catch (ProductManagementException e) {
            return ResponseEntity.badRequest().build();
        }
    }




}

