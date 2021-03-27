package com.rakib.student.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakib.student.config.FeignClientConfig;
import com.rakib.student.domain.AddressDTO;
import com.rakib.student.domain.ResponseDTO;
import com.rakib.student.domain.Student;
import com.rakib.student.repository.StudentRepository;
import com.rakib.student.service.StudentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;
    private final FeignClientConfig feignClientConfig;

    public StudentServiceImpl(StudentRepository studentRepository, FeignClientConfig feignClientConfig) {
        this.studentRepository = studentRepository;
        this.feignClientConfig = feignClientConfig;
    }

    @Override
    public Student saveStudent(Student address) {
        return studentRepository.save(address);
    }

    @Override
    @CircuitBreaker(name = "api-call-service", fallbackMethod = "fallbackGetStudent")
    public ResponseDTO getStudent(Long addressId) {
        Student student = studentRepository.getOne(addressId);
        ResponseEntity<?> address = feignClientConfig.getAddress(student.getAddressId());
        System.out.println(address);
        ObjectMapper mapper = new ObjectMapper();
        AddressDTO addressDTO = mapper.convertValue(address.getBody(), AddressDTO.class);
        return ResponseDTO.builder().student(student).addressDTO(addressDTO).build();
    }

    public ResponseDTO fallbackGetStudent(Long addressId, Throwable throwable) {
        LOGGER.error(throwable.getMessage());
        return new ResponseDTO();
    }

    @Override
    @CircuitBreaker(name = "api-call-service", fallbackMethod = "fallbackGetAllStudent")
    public List<ResponseDTO> getAllStudent() {
        List<ResponseDTO> responseDTOList = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();
        studentList.forEach(student -> {
            ResponseEntity<?> address = feignClientConfig.getAddress(student.getAddressId());
            System.out.println(address);
            ObjectMapper mapper = new ObjectMapper();
            AddressDTO body = mapper.convertValue(address.getBody(), AddressDTO.class);
            ResponseDTO responseDTO = ResponseDTO.builder().addressDTO(body).student(student).build();
            responseDTOList.add(responseDTO);
        });
        return responseDTOList;
    }

    public List<ResponseDTO> fallbackGetAllStudent( Throwable throwable) {
        LOGGER.error(throwable.getMessage());
        return Collections.emptyList();
    }


    @Override
    public Student updateStudent(Student student, Long addressId) throws Exception {
        if (Objects.nonNull(addressId)) {
            Student student1 = getStudent(addressId).getStudent();
            if (Objects.nonNull(student)) {
                if (Objects.nonNull(student.getAddressId())) {
                    student1.setAddressId(student.getAddressId());
                }
                if (Objects.nonNull(student.getEmailAddress())) {
                    student1.setEmailAddress(student.getEmailAddress());
                }
                if (Objects.nonNull(student.getFirstName())) {
                    student1.setFirstName(student.getFirstName());
                }
                if (Objects.nonNull(student.getLastName())) {
                    student1.setLastName(student.getLastName());
                }
            }
            return studentRepository.save(student1);
        } else {
            throw new Exception("Not Found");
        }
    }

    @Override
    public boolean deleteStudent(Long aLong) {
        studentRepository.deleteById(aLong);
        return true;
    }
}
