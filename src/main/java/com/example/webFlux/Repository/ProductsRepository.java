package com.example.webFlux.Repository;

import com.example.webFlux.Model.Products;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends ReactiveMongoRepository<Products, String> {

}
