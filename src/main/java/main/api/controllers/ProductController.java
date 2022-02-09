package main.api.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import main.api.dto.DTOMessage;
import main.api.dto.DTOSuccessfully;
import main.api.entitys.List;
import main.api.entitys.Product;
import main.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getproduct/{id}")
    @ApiOperation(value = "get product by id", response = Product.class)
    public ResponseEntity<?> getProductById(@PathVariable long id) {
        return productService.findById(id);
    }

    @GetMapping("/getallproducts")
    @ApiOperation(value = "get all products", response = java.util.List.class)
    public ResponseEntity<?> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/addnewproduct")
    @ApiOperation(value = "add new products", response = ResponseEntity.class)
    public ResponseEntity<?> addNewProduct(@RequestBody String name, @RequestBody String description, @RequestBody int kCal) {
        productService.addProduct(name, description, kCal);
        return ResponseEntity.ok(new DTOSuccessfully(null, new Date().getTime() / 1000, new DTOMessage()));
    }

    @PutMapping("/changeproduct/{id}")
    @ApiOperation(value = "change product", response = Product.class)
    public ResponseEntity<?> changeExistedProduct(@RequestParam long id,
                                                  @RequestBody String name,
                                                  @RequestBody String description,
                                                  @RequestBody int kCal) {
        return productService.changeProductById(id, name, description, kCal);
    }
}
