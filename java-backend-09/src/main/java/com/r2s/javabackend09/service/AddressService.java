package com.r2s.javabackend09.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.javabackend09.dto.request.AddressRequest;
import com.r2s.javabackend09.dto.response.AddressReponse;
import com.r2s.javabackend09.model.Address;
import com.r2s.javabackend09.model.User;
import com.r2s.javabackend09.repository.AddressRepository;
import com.r2s.javabackend09.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AddressService {
    
    @Autowired 
    AddressRepository addressRepository;

    public List<AddressReponse> getAddressesByUserId(int user_id) {
        List<Address> addresses = addressRepository.findAddressesByUserId(user_id);
        List<AddressReponse> responseList = new ArrayList<>();

        for (Address address : addresses) {
        	AddressReponse response = new AddressReponse();
            response.setStreet(address.getStreet());
            response.setCity(address.getCity());
            responseList.add(response);
        }

        return responseList;
    }



    @Transactional
    public void deleteAddressesForUser(int user_id) {
        addressRepository.deleteByUserId(user_id);
    }

    @Autowired
    private UserRepository userRepository;
    @Transactional
    public void addAddress(int user_id, AddressRequest addressRequest) {
        User user = userRepository.findById(user_id).orElse(null);
        if (user == null) {
            return;
        }

        Address address = new Address();
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        address.setUser(user);
        addressRepository.save(address);
    }
    
    @Transactional
    public void updateAddress(int user_id, AddressRequest addressRequest) {
        User user = userRepository.findById(user_id).orElse(null);
        if (user == null) {
            return;
        }

        Address address = addressRepository.findByUserId(user_id).orElse(null);
        if (address == null) {
            return;
        }

        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        address.setUser(user);
        addressRepository.save(address);
    }

}