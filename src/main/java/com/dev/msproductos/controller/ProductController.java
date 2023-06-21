package com.dev.msproductos.controller;

import com.dev.msproductos.model.Product;
import com.dev.msproductos.repository.ProductRepository;
import com.dev.msproductos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;
    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }
    @GetMapping("/{id}")
    public Product read(@PathVariable String id) {
        Optional<Product> employeeObj = productRepository.findById(id);
        if(employeeObj.isPresent()) {
            return employeeObj.get();
        }else {
            throw new RuntimeException("error al encontrar id "+id);
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        productService.delete(id);
    }
}
