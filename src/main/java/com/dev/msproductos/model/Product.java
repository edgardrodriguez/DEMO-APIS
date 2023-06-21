package com.dev.msproductos.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@Data
@Document(collection = "product")
public class Product {

    @Id
    private String id;
    private String nombre;
    private Integer cantidad;
    private Integer precio;


}
