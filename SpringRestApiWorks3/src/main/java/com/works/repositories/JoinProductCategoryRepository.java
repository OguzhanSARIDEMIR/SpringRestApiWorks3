package com.works.repositories;

import com.works.entities.JoinProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JoinProductCategoryRepository extends JpaRepository<JoinProductCategory, Integer> {

    @Query(value = "SELECT p.pid,p.cid,p.ptitle,p.price,c.title FROM product as p INNER JOIN category as c ON p.cid=c.cid",nativeQuery = true)
    List<JoinProductCategory> allProduct();

    @Query(value = "SELECT p.pid,p.cid,p.ptitle,p.price,c.title FROM product as p INNER JOIN category as c ON p.cid=c.cid WHERE p.ptitle LIKE ?1",nativeQuery = true)
    List<JoinProductCategory> searchProduct(String q);

    @Query(value = "SELECT p.pid,p.cid,p.ptitle,p.price,c.title FROM product as p INNER JOIN category as c ON p.cid=c.cid LIMIT ?1,?2",nativeQuery = true)
    List<JoinProductCategory> pageProduct(int start,int count);

    @Query(value = "SELECT COUNT(*) as size FROM product as p INNER JOIN category as c ON p.cid=c.cid ",nativeQuery = true)
    int totalsize();

    @Query(value = "SELECT p.pid,p.cid,p.ptitle,p.price,c.title FROM product as p INNER JOIN category as c ON p.cid=c.cid",nativeQuery = true)
    List<JoinProductCategory>finAllPage(String q,Pageable pageable);

    @Query(value = "SELECT count(*) as total FROM product as p INNER JOIN category as c ON p.cid=c.cid WHERE p.ptitle LIKE ?1",nativeQuery = true)
    int countTotal(String ptitle);



}