package whu.edu.ProductManagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import whu.edu.ProductManagement.dao.ProductDao;
import whu.edu.ProductManagement.domain.Product;
import whu.edu.ProductManagement.service.IProductService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author little prince
 * @since 2023-11-08
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('query')")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('update')")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        int status = productService.updateProduct(product);
        return ResponseEntity.ok("update successfully, status = " + status);
    }
}

