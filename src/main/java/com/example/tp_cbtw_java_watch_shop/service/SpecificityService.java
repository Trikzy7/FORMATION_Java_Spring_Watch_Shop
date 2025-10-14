package com.example.tp_cbtw_java_watch_shop.service;

import com.example.tp_cbtw_java_watch_shop.model.Specificity;
import com.example.tp_cbtw_java_watch_shop.repository.SpecificityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpecificityService {

    private final SpecificityRepository specificityRepository;

    public SpecificityService(SpecificityRepository specificityRepository) {
        this.specificityRepository = specificityRepository;
    }

    // Create
    public Specificity createSpecificity(Specificity specificity) {
        return specificityRepository.save(specificity);
    }

    // Get all
    public List<Specificity> getAllSpecificities() {
        return specificityRepository.findAll();
    }

    // Get by ID
    public Specificity getSpecificityById(Long id) {
        return specificityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specificity not found with id: " + id));
    }

    // Update
    public Specificity updateSpecificity(Long id, Specificity updatedSpecificity) {
        Specificity existing = specificityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specificity not found with id: " + id));

        existing.setValue(updatedSpecificity.getValue());

        return specificityRepository.save(existing);
    }

    // Delete
    public void deleteSpecificity(Long id) {
        Specificity existing = specificityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specificity not found with id: " + id));

        specificityRepository.delete(existing);
    }
}