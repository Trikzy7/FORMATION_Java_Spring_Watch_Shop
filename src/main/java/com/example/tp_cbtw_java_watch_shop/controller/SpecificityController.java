package com.example.tp_cbtw_java_watch_shop.controller;

import com.example.tp_cbtw_java_watch_shop.model.Specificity;
import com.example.tp_cbtw_java_watch_shop.service.SpecificityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specificities")
public class SpecificityController {

    private final SpecificityService specificityService;

    public SpecificityController(SpecificityService specificityService) {
        this.specificityService = specificityService;
    }

    // Create a new specificity
    @PostMapping
    public ResponseEntity<Specificity> createSpecificity(@RequestBody Specificity specificity) {
        Specificity created = specificityService.createSpecificity(specificity);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Get all specificities
    @GetMapping
    public ResponseEntity<List<Specificity>> getAllSpecificities() {
        List<Specificity> specificities = specificityService.getAllSpecificities();
        return new ResponseEntity<>(specificities, HttpStatus.OK);
    }

    // Get specificity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Specificity> getSpecificityById(@PathVariable Long id) {
        Specificity specificity = specificityService.getSpecificityById(id);
        return new ResponseEntity<>(specificity, HttpStatus.OK);
    }

    // Update specificity by ID
    @PutMapping("/{id}")
    public ResponseEntity<Specificity> updateSpecificity(@PathVariable Long id, @RequestBody Specificity specificity) {
        Specificity updated = specificityService.updateSpecificity(id, specificity);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Delete specificity by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecificity(@PathVariable Long id) {
        specificityService.deleteSpecificity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}