package com.quest.tims.service;

import com.quest.tims.entity.Supplier;
import com.quest.tims.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    // Retrieve all suppliers
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return ResponseEntity.ok(suppliers);
    }

    // Retrieve a supplier by ID
    public ResponseEntity<Supplier> getSupplierById(int id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            return ResponseEntity.ok(supplier.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    // Add a new supplier
    public ResponseEntity<Supplier> createSupplier(Supplier supplier) {
        Supplier createdSupplier = supplierRepository.save(supplier);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdSupplier);
    }

    // Delete a supplier by ID
    public ResponseEntity<String> deleteSupplier(int id) {
        if (!supplierRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Supplier with ID " + id + " does not exist");
        }
        supplierRepository.deleteById(id);
        return ResponseEntity.ok("Supplier with ID " + id + " deleted successfully.");
    }
}
