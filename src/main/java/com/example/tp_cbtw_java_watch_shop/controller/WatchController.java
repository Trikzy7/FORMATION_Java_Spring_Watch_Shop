package com.example.tp_cbtw_java_watch_shop.controller;

import com.example.tp_cbtw_java_watch_shop.dto.WatchRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.WatchResponseDTO;
import com.example.tp_cbtw_java_watch_shop.model.Watch;
import com.example.tp_cbtw_java_watch_shop.service.WatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/watches")
public class WatchController {

    private final WatchService watchService;

    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    // Create a new watch
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<WatchResponseDTO> createWatch(@Valid @RequestBody WatchRequestDTO watchRequestDTO) {
        WatchResponseDTO createdWatch = watchService.createWatch(watchRequestDTO);
        return new ResponseEntity<>(createdWatch, HttpStatus.CREATED);
    }

    // Get all watches
    @GetMapping
    public ResponseEntity<List<WatchResponseDTO>> getAllWatches() {
        List<WatchResponseDTO> watches = watchService.getAllWatches();
        return new ResponseEntity<>(watches, HttpStatus.OK);
    }

    // Get watch by ID
    @GetMapping("/{id}")
    public ResponseEntity<WatchResponseDTO> getWatchById(@PathVariable Long id) {
        WatchResponseDTO watch = watchService.getWatchById(id);
        return new ResponseEntity<>(watch, HttpStatus.OK);
    }

    // Update watch by ID
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<WatchResponseDTO> updateWatch(@PathVariable Long id, @Valid @RequestBody WatchRequestDTO watchRequestDTO) {
        WatchResponseDTO updatedWatch = watchService.updateWatch(id, watchRequestDTO);
        return new ResponseEntity<>(updatedWatch, HttpStatus.OK);
    }

    // Delete watch by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatch(@PathVariable Long id) {
        watchService.deleteWatch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filter")
    public List<WatchResponseDTO> filterWatches(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        return watchService.filterWatches(brand, category, minPrice, maxPrice);
    }

    /**
     * Met à jour la quantité en stock d'une montre existante.
     * Exemple : PATCH /api/watches/10/stock?quantity=5
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/stock")
    public ResponseEntity<String> updateWatchStock(
            @PathVariable Long id,
            @RequestParam("quantity") int newStock) {

        // Appel du service
        watchService.updateWatchStock(id, newStock);

        return new ResponseEntity<>(
                "✅ Stock de la montre avec l'ID " + id + " mis à jour à " + newStock,
                HttpStatus.OK
        );
    }

    /**
     * Retire une quantité du stock d'une montre.
     * Exemple : PATCH /api/watches/10/remove-stock?quantity=2
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/remove-stock")
    public ResponseEntity<?> removeStock(
            @PathVariable Long id,
            @RequestParam("quantity") int quantity) {

        watchService.decreaseWatchStock(id, quantity);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Stock de la montre diminué");
        response.put("watchId", id);
        response.put("removedQuantity", quantity);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Ajoute une quantité au stock d'une montre.
     * Exemple : PATCH /api/watches/10/add-stock?quantity=5
     */
    @PatchMapping("/{id}/add-stock")
    public ResponseEntity<?> addStock(
            @PathVariable Long id,
            @RequestParam("quantity") int quantity) {

        watchService.increaseWatchStock(id, quantity);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Stock de la montre augmenté");
        response.put("watchId", id);
        response.put("addedQuantity", quantity);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}