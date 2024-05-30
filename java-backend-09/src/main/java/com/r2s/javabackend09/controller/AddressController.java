package com.r2s.javabackend09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.javabackend09.dto.request.AddressRequest;
import com.r2s.javabackend09.dto.response.AddressReponse;
import com.r2s.javabackend09.service.AddressService;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/get/{user_id}/addresses")
    public List<AddressReponse> getUserAddresses(@PathVariable("user_id") int userId) {
        return addressService.getAddressesByUserId(userId);
    }

    @PostMapping("/add/{user_id}/addresses")
    public ResponseEntity<String> addAddress(@PathVariable("user_id") int user_id, @RequestBody AddressRequest addressRequest) {
        addressService.addAddress(user_id, addressRequest);
        return ResponseEntity.ok("Address added successfully");
    }
    
    @PostMapping("/update/{user_id}/addresses")
    public ResponseEntity<Void> updateAddress(@PathVariable int user_id, @RequestBody AddressRequest addressRequest) {
        addressService.updateAddress(user_id, addressRequest);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete/{user_id}/addresses")
    public void deleteAddressesForUser(@PathVariable("user_id") int userId) {
        this.addressService .deleteAddressesForUser(userId);
    }
}