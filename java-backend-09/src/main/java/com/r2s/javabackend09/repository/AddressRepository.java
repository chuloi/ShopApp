package com.r2s.javabackend09.repository;

import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.javabackend09.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAddressesByUserId(int user_id);
    void deleteByUserId(int user_id);
    Optional<Address> findByUserId(Integer userId);
}
