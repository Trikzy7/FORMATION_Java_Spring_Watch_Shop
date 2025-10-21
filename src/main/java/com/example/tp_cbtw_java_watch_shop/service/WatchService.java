package com.example.tp_cbtw_java_watch_shop.service;

import com.example.tp_cbtw_java_watch_shop.dto.WatchRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.WatchResponseDTO;
import com.example.tp_cbtw_java_watch_shop.mapper.WatchMapper;
import com.example.tp_cbtw_java_watch_shop.model.Watch;
import com.example.tp_cbtw_java_watch_shop.repository.WatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WatchService {

    private final WatchRepository watchRepository;

    public WatchService(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    // Create
    public WatchResponseDTO createWatch(WatchRequestDTO requestDTO) {
        Watch watch = WatchMapper.toEntity(requestDTO);
        Watch saved = watchRepository.save(watch);
        return WatchMapper.toDto(saved);
    }

    // Get all
    public List<WatchResponseDTO> getAllWatches() {
        return watchRepository.findAll()
                .stream()
                .map(WatchMapper::toDto)
                .collect(Collectors.toList());
    }

    // Get by ID
    public WatchResponseDTO getWatchById(Long id) {
        Watch watch = watchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Watch not found with id: " + id));
        return WatchMapper.toDto(watch);
    }

    // Update
    public WatchResponseDTO updateWatch(Long id, WatchRequestDTO requestDTO) {
        Watch existing = watchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Watch not found with id: " + id));

        existing.setName(requestDTO.getName());
        existing.setBrand(requestDTO.getBrand());
        existing.setDescription(requestDTO.getDescription());
        existing.setPrice(requestDTO.getPrice());
        existing.setImageUrl(requestDTO.getImageUrl());
        existing.setSpecificities(requestDTO.getSpecificities());
        existing.setCategory(requestDTO.getCategory());

        Watch updated = watchRepository.save(existing);
        return WatchMapper.toDto(updated);
    }

    // Delete
    public void deleteWatch(Long id) {
        Watch watch = watchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Watch not found with id: " + id));
        watchRepository.delete(watch);
    }

    public List<WatchResponseDTO> filterWatches(String brand, String category, Double minPrice, Double maxPrice) {
        return watchRepository.filterWatches(brand, category, minPrice, maxPrice)
                .stream()
                .map(WatchMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 🔹 Récupère une entité Watch depuis la base de données.
     * @param id identifiant de la montre
     * @return l’entité Watch correspondante
     * @throws RuntimeException si la montre n’existe pas
     */
    public Watch getWatchEntityById(Long id) {
        return watchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Montre non trouvée avec l'id : " + id));
    }

    /**
     * 🔹 Met à jour uniquement le stock d’une montre.
     * @param id identifiant de la montre
     * @param newStock nouvelle quantité en stock
     * @throws RuntimeException si la montre n’existe pas
     */
    public void updateWatchStock(Long id, int newStock) {
        Watch watch = getWatchEntityById(id);
        watch.setStockQuantity(newStock);
        watchRepository.save(watch);
    }
}