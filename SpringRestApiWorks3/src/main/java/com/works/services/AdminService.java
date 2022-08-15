package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AdminService {
    final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public ResponseEntity save(Admin admin){
        Map<String , Object> hm=new LinkedHashMap<>();
        adminRepository.save(admin);
        hm.put("status",true);
        hm.put("result",admin);

        return new ResponseEntity(hm, HttpStatus.OK);
    }
    public ResponseEntity list(){
        Map<String , Object> hm=new LinkedHashMap<>();
        hm.put("status",true);
        hm.put("result",adminRepository.findAll());

        return new ResponseEntity(hm,HttpStatus.OK);
    }
}
