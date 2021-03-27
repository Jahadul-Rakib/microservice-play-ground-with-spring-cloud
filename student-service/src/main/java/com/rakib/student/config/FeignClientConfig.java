package com.rakib.student.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "api-gateway")
public interface FeignClientConfig {
    @GetMapping("/address-service/api/v1/address/{id}")
    ResponseEntity<?> getAddress(@PathVariable(value = "id") Long id);
}
