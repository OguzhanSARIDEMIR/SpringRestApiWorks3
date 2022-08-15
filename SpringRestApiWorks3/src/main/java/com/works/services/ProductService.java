package com.works.services;

import com.works.entities.Product;
import com.works.repositories.JoinProductCategoryRepository;
import com.works.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ProductService {
    final ProductRepository productRepository;
    final JoinProductCategoryRepository joinProductCategoryRepository;

    public ProductService(ProductRepository productRepository, JoinProductCategoryRepository joinProductCategoryRepository) {
        this.productRepository = productRepository;
        this.joinProductCategoryRepository = joinProductCategoryRepository;
    }

    public ResponseEntity save(Product product) {
        Map<String, Object> hm = new LinkedHashMap<>();
        productRepository.save(product);
        hm.put("status", true);
        hm.put("result", product);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity list() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", joinProductCategoryRepository.allProduct());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity search(String q) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", joinProductCategoryRepository.searchProduct("%" + q + "%"));
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity pageProduct(int start, int count) {
        Map<String, Object> hm = new LinkedHashMap<>();
        if (count > 5) {
            count = 5;

        }
        hm.put("status", true);
        hm.put("total", joinProductCategoryRepository.totalsize());
        hm.put("result", joinProductCategoryRepository.pageProduct(start * count, 5));
        return new ResponseEntity(hm, HttpStatus.OK);
    }
    public ResponseEntity searchPage(String q,int page, int size) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        if (size > 5) {
            size = 5;

        }
        Pageable pageable= PageRequest.of(page,size, Sort.by("price").ascending());
        hm.put("total",joinProductCategoryRepository.countTotal("%"+ q+ "%"));
        hm.put("result", joinProductCategoryRepository.finAllPage("%"+ q+ "%",pageable));
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity allProductCategoryID(int cid){
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status",true);
        hm.put("result", productRepository.allProductCategoryID(cid));
        return new ResponseEntity(hm,HttpStatus.OK);
    }
}

