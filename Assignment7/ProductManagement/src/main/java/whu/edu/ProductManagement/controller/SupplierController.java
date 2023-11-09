package whu.edu.ProductManagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import whu.edu.ProductManagement.dao.SupplierDao;
import whu.edu.ProductManagement.domain.Product;
import whu.edu.ProductManagement.domain.Supplier;
import whu.edu.ProductManagement.service.ISupplierService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author little prince
 * @since 2023-11-08
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    ISupplierService supplierService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('query')")
    public ResponseEntity<Supplier> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(supplierService.getSupplier(id));
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('update')")
    public ResponseEntity<String> updateProduct(@RequestBody Supplier supplier) {

        int status = supplierService.updateSupplier(supplier);
        return ResponseEntity.ok("update successfully, status = " + status);
    }
}

