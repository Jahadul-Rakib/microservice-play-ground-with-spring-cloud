package com.rakib.address.controller;

import com.rakib.address.domain.Address;
import com.rakib.address.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<?> postAddress(@RequestBody Address address) {
        return ResponseEntity.ok().body(addressService.saveAddress(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddress(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(addressService.getAddress(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllAddress() {
        return ResponseEntity.ok().body(addressService.getAllAddress());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@RequestBody Address address,
                                           @PathVariable(value = "id") Long id)
            throws Exception {
        return ResponseEntity.ok().body(addressService.updateAddress(address, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(addressService.deleteAddress(id));
    }
}
