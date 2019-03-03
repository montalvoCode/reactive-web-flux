package com.example.webFlux.Controller;

import com.example.webFlux.Model.Products;
import com.example.webFlux.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping(value = "/list-products", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Products> listProducts(){
        return productsService.listProducts();
    }

    @GetMapping(value = "/list-id-products/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ResponseEntity<Products>> listIdProducts(@PathVariable(value = "id") String id){
        return productsService.listIdProducts(id)
                .map(x -> ResponseEntity.ok(x))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create-products", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Products> createProducts(@RequestBody Products products){
        return productsService.createProducts(products);
    }

    @PutMapping(value = "/update-products/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ResponseEntity<Products>> updateProducts(@PathVariable(value = "id") String id,
                                                         @RequestBody Products products){
        return productsService.updateProducts(id, products)
                .map(updateProduct -> new ResponseEntity<>(updateProduct, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/delete-products/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ResponseEntity<Void>> deleteProducts(@PathVariable(value = "id") String id){
        return productsService.deleteProducts(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
