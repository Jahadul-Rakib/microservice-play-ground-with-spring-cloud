package com.rakib.address.service;

import com.rakib.address.domain.Address;

import java.util.List;

public interface AddressService {
    Address saveAddress(Address address);
    Address getAddress(Long addressId);
    List<Address> getAllAddress();
    Address updateAddress(Address address, Long addressId) throws Exception;
    boolean deleteAddress(Long addressId);
}
