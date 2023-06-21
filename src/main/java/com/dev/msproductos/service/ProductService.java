package com.dev.msproductos.service;

import com.dev.msproductos.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    List<Product> byId(String id);
    Product create(Product product);

    Product update(Product product);

    void delete(String id);

}
