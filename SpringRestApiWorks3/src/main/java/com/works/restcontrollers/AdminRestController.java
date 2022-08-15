package com.works.restcontrollers;

import com.works.entities.Admin;
import com.works.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    final AdminService adminService;

    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/save")
   public ResponseEntity save(@RequestBody Admin admin){
        return adminService.save(admin);
    }
    @GetMapping("/list")
    public ResponseEntity list(){
        return adminService.list();
    }
}
