package com.works.restcontrollers;


import com.works.entities.Product;
import com.works.services.ProductService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/product")
public class ProductRestController {
    final ProductService productService;
    final CacheManager cacheManager;

    public ProductRestController(ProductService productService, CacheManager cacheManager) {
        this.productService = productService;
        this.cacheManager = cacheManager;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Product product) {
        cacheManager.getCache("productList").clear();
        return productService.save(product);
    }

    @GetMapping("/list")
    @Cacheable("productList")
    public ResponseEntity list() {
        return productService.list();

    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam(defaultValue = "") String q) {
        return productService.search(q);
    }

    @GetMapping("/page")
    public ResponseEntity page(@RequestParam(defaultValue = "0") int start, @RequestParam(defaultValue = "5") int count) {
        return productService.pageProduct(start, count);
    }

    @GetMapping("/searchpage")
    public ResponseEntity searchpage(@RequestParam(defaultValue = "") String q, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return productService.searchPage(q, page, size);
    }

    @GetMapping("/allProductCategoryID")
    public ResponseEntity allProductCategoryID(@RequestParam(defaultValue = "") int cid) {
        return productService.allProductCategoryID(cid);
    }
    @Scheduled(fixedDelay = 10000)
    public void reseCache(){
        cacheManager.getCache("productList").clear();
        System.out.println("ResetCache Cool");
    }
}
