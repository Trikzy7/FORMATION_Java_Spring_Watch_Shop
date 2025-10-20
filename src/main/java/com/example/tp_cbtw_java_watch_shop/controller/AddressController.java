package com.example.tp_cbtw_java_watch_shop.controller;

import com.example.tp_cbtw_java_watch_shop.dto.AddressRequestDTO;
import com.example.tp_cbtw_java_watch_shop.dto.AddressResponseDTO;
import com.example.tp_cbtw_java_watch_shop.security.JwtService;
import com.example.tp_cbtw_java_watch_shop.service.AddressService;
import com.example.tp_cbtw_java_watch_shop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "*")
public class AddressController {

    private final AddressService addressService;
    private final JwtService jwtService;
    private final UserService userService;

    public AddressController(AddressService addressService, JwtService jwtService, UserService userService) {
        this.addressService = addressService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAddresses(@RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractEmail(authHeader.replace("Bearer ", ""));
        Long userId = userService.findByEmail(email).getId();
        return ResponseEntity.ok(addressService.getAddresses(userId));
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> addAddress(@RequestHeader("Authorization") String authHeader,
                                                         @RequestBody AddressRequestDTO dto) {
        String email = jwtService.extractEmail(authHeader.replace("Bearer ", ""));
        Long userId = userService.findByEmail(email).getId();
        return ResponseEntity.ok(addressService.addAddress(userId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@RequestHeader("Authorization") String authHeader,
                                                            @PathVariable Long id,
                                                            @RequestBody AddressRequestDTO dto) {
        String email = jwtService.extractEmail(authHeader.replace("Bearer ", ""));
        Long userId = userService.findByEmail(email).getId();

        return ResponseEntity.ok(addressService.updateAddress(id, userId, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@RequestHeader("Authorization") String authHeader,
                                              @PathVariable Long id) {

        String email = jwtService.extractEmail(authHeader.replace("Bearer ", ""));
        Long userId = userService.findByEmail(email).getId();

        addressService.deleteAddress(id, userId);
        return ResponseEntity.noContent().build();
    }
}