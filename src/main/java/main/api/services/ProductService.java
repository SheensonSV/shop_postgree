package main.api.services;

import main.api.controllers.ControllerAdvice;
import main.api.entitys.Product;
import main.api.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public ResponseEntity<?> findAll() {
        Iterable<Product> iterable = productRepo.findAll();
        java.util.List<Product> productList = new ArrayList<>();

        for (Product product : iterable) {
            productList.add(product);
        }
        if (productList.size() != 0) {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
        else {
            return new ControllerAdvice().noElementsException();
        }
    }

    public Product addProduct(String name, String description, int kCal) {
        Product newProduct = new Product(name, description, kCal);
        productRepo.save(newProduct);
        return newProduct;
    }

    public ResponseEntity<?> findById(long id) {
        if (productRepo.findById(id).isPresent())
        {
            return new ResponseEntity<>(productRepo.findById(id).get(), HttpStatus.OK);
        }
        else {
            return new ControllerAdvice().noSuchElementException();
        }
    }

    public ResponseEntity<?> findByName(String name) {
        java.util.List<Product> productList = new ArrayList<>();
        productList = productRepo.findProductByName(name);
        if (!productList.isEmpty()) {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
        else {
            return new ControllerAdvice().noElementsException();
        }
    }

    public ResponseEntity<?> changeProduct(Product product) {
        if (productRepo.findById(product.getId()).isPresent()) {
            Product oldProduct = productRepo.findById(product.getId()).get();
            oldProduct.setDescription(product.getDescription());
            oldProduct.setName(product.getName());
            oldProduct.setKcal(product.getKcal());
            productRepo.save(oldProduct);
            return new ResponseEntity<>(oldProduct, HttpStatus.OK);
        }
        else {
            return new ControllerAdvice().noSuchElementException();
        }
    }

    public ResponseEntity<?> changeProductById(long id, String name, String description, int kCal) {
        if (productRepo.findById(id).isPresent()) {
            Product oldProduct = productRepo.findById(id).get();
            oldProduct.setDescription(description);
            oldProduct.setName(name);
            oldProduct.setKcal(kCal);
            productRepo.save(oldProduct);
            return new ResponseEntity<>(oldProduct, HttpStatus.OK);
        }
        else {
            return new ControllerAdvice().noSuchElementException();
        }
    }
}
