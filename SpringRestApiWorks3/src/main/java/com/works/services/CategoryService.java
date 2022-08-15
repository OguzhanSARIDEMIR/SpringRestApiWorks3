package com.works.services;

import com.works.entities.Category;
import com.works.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
     public ResponseEntity save(Category category){
        Map<String ,Object> hm=new LinkedHashMap<>();
        categoryRepository.save(category);
        hm.put("status",true);
        hm.put("result",category);
        return new ResponseEntity(hm, HttpStatus.OK);
     }
     public ResponseEntity list(){
         Map<String ,Object> hm=new LinkedHashMap<>();
         hm.put("status",true);
         hm.put("result",categoryRepository.findAll());

         return new ResponseEntity(hm,HttpStatus.OK);


     }

}
