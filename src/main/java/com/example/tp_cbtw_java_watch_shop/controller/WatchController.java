package com.example.tp_cbtw_java_watch_shop.controller;

import com.example.tp_cbtw_java_watch_shop.dto.WatchRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.WatchResponseDTO;
import com.example.tp_cbtw_java_watch_shop.service.WatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watches")
public class WatchController {

    private final WatchService watchService;

    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    // Create a new watch
    @PostMapping
    public ResponseEntity<WatchResponseDTO> createWatch(@RequestBody WatchRequestDTO watchRequestDTO) {
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
    @PutMapping("/{id}")
    public ResponseEntity<WatchResponseDTO> updateWatch(@PathVariable Long id, @RequestBody WatchRequestDTO watchRequestDTO) {
        WatchResponseDTO updatedWatch = watchService.updateWatch(id, watchRequestDTO);
        return new ResponseEntity<>(updatedWatch, HttpStatus.OK);
    }

    // Delete watch by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatch(@PathVariable Long id) {
        watchService.deleteWatch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}