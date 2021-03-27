package com.rakib.address.service.impl;

import com.rakib.address.domain.Address;
import com.rakib.address.repository.AddressRepository;
import com.rakib.address.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getAddress(Long addressId) {
        return addressRepository.getOne(addressId);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address updateAddress(Address address, Long addressId) throws Exception {
        if (Objects.nonNull(addressId)) {
            Address address1 = getAddress(addressId);
            if (Objects.nonNull(address)) {
                if (Objects.nonNull(address.getFathersName())) {
                    address1.setFathersName(address.getFathersName());
                }
                if (Objects.nonNull(address.getAreaCode())) {
                    address1.setAreaCode(address.getAreaCode());
                }
                if (Objects.nonNull(address.getCityName())) {
                    address1.setCityName(address.getCityName());
                }
                if (Objects.nonNull(address.getDetailAddress())) {
                    address1.setDetailAddress(address.getDetailAddress());
                }
            }
            return addressRepository.save(address1);
        }else {
            throw new Exception("Not Found");
        }
    }

    @Override
    public boolean deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
        return true;
    }
}
