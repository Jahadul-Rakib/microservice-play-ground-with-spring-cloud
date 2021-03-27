package com.rakib.address.config;
import com.rakib.address.service.DTO.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "api-gateway")
public interface StudentFeignClient {
    @PostMapping("/student-service/api/v1/student")
    ResponseEntity<?> postStudent(@RequestBody StudentDTO student);

    @GetMapping("/student-service/api/v1/student/{id}")
    ResponseEntity<?> getStudent(@PathVariable(value = "id") Long id);

    @GetMapping("/student-service/api/v1/student")
    ResponseEntity<?> getAllStudent();

    @PutMapping("/student-service/api/v1/student/{id}")
    ResponseEntity<?> updateStudent(@RequestBody StudentDTO student, @PathVariable(value = "id") Long id);

    @DeleteMapping("/student-service/api/v1/student/{id}")
    ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long id);
}
