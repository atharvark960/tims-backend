package com.quest.tims.controller;

import com.quest.tims.entity.Supplier;
import com.quest.tims.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // GET /suppliers: Retrieve all suppliers
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // GET /suppliers/{id}: Retrieve a single supplier by ID
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable int id) {
        return supplierService.getSupplierById(id);
    }

    // POST /suppliers: Add a new supplier
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    // DELETE /suppliers/{id}: Delete a supplier by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable int id) {
        return supplierService.deleteSupplier(id);
    }
}
