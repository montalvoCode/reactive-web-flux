package com.example.webFlux.Service;

import com.example.webFlux.Model.Products;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductsService {

    Flux<Products> listProducts();

    Mono<Products> listIdProducts(String id);

    Mono<Products> createProducts(Products products);

    Mono<Products> updateProducts(String id, Products products);

    Mono<Void> deleteProducts(String id);

}
