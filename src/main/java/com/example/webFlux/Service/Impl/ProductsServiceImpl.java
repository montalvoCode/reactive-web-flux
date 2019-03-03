package com.example.webFlux.Service.Impl;

import com.example.webFlux.Model.Products;
import com.example.webFlux.Repository.ProductsRepository;
import com.example.webFlux.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;


    @Override
    public Flux<Products> listProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Mono<Products> listIdProducts(String id) {
        return productsRepository.findById(id);
    }

    @Override
    public Mono<Products> createProducts(Products products) {
        return productsRepository.save(products);
    }

    @Override
    public Mono<Products> updateProducts(String id, Products products) {
        return productsRepository.findById(id)
                .flatMap(existingProducts -> {
                    existingProducts.setProductName(products.getProductName());
                    existingProducts.setStock(products.getStock());
                    existingProducts.setPrice(products.getPrice());
                    existingProducts.setStatus(products.getStatus());
                    return productsRepository.save(existingProducts);
                });
    }

    @Override
    public Mono<Void> deleteProducts(String id) {
        return productsRepository.findById(id)
                .flatMap(existingProduct -> productsRepository.delete(existingProduct));
    }
}
