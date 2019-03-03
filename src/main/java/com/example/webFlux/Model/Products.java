package com.example.webFlux.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "products")
@Data
public class Products {

    @Id
    private String id;

    private String productName;

    private Integer stock;

    private Double price;

    @NotNull
    private Date createdAt = new Date();

    private Integer status;

}
