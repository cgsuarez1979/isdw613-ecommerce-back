package com.myecommerce.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myecommerce.model.Product;
import com.myecommerce.repositories.ProductRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping
    public List<Product> getAllProducts() {
        //return Arrays.asList(new String[] {"Producto 1", "Producto 2"});
        return productRepository.findAll();
    }
    

    @GetMapping("/getProductsByName/{name}/{page}")
    public List<Product> getProductsByName(@PathVariable String name, @PathVariable String page) {    
        Pageable pageable = PageRequest.of(Integer.parseInt(page), 2); //Paginas empiezan con el indice 0
        return productRepository.findByNameLike("%" + name + "%", pageable);
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> optProduct = productRepository.findById(Integer.parseInt(id));
        if (optProduct.isPresent()) {
            // return optProduct.get();
            return ResponseEntity.ok(optProduct.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/uploadProduct")
    public String uploadProductsFromFile(@RequestParam("file") MultipartFile file) {
        try {
            InputStream is = file.getInputStream();
            BufferedReader breader = new BufferedReader(new InputStreamReader(is));
            while (breader.ready()) {
                String line = breader.readLine();
                System.out.println(line);
            }
            return "OK";
        } catch (IOException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }
    
    @GetMapping("/download")
    public ResponseEntity<Resource> download() throws IOException {
        File file = new File(
                "/Users/csuarez/Documents/personal/EPN/cursos/ISDW613_Aplicaciones_Web/workspace/my-ecommerce-back/data/products.csv");
        InputStreamSource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body((Resource)resource);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @PatchMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        productRepository.deleteById(Integer.parseInt(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    

    
}
