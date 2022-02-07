package main.api.controllers;

import io.swagger.annotations.ApiOperation;
import main.api.entitys.List;
import main.api.response.ListWithProductsResponse;
import main.api.services.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

@RestController
@RequestMapping("/api")
public class ListController {
    @Autowired
    private ListService listService;

    @GetMapping("/getlist/{id}")
    @ApiOperation(value = "get list by id", response = List.class)
    public ResponseEntity<?> getProductById(@PathVariable long id) {
        return listService.findById(id);
    }

    @GetMapping("/getlistwithkcal/{id}")
    @ApiOperation(value = "get list by id with total kCal", response = ListWithProductsResponse.class)
    public ResponseEntity<?> getListByIdWithTotalKCal(@PathVariable long id) {
        return listService.findByIdAndGetTotalKCal(id);
    }

    @GetMapping("/getalllists")
    @ApiOperation(value = "get all lists as List<List>", response = java.util.List.class)
    public ResponseEntity<?> getAllLists() {
        java.util.List<List> actualList = new ArrayList<>();
        Iterator<List> iterator = listService.findAll().iterator();
        iterator.forEachRemaining(actualList::add);
        return new ResponseEntity<>(actualList, HttpStatus.OK);
    }

    @PostMapping("/addnewlist")
    @ApiOperation(value = "add new list", response = ResponseEntity.class)
    public ResponseEntity<?> addNewListWithProducts(@RequestBody String name) {
        return listService.addNewList(name);
    }
    @PutMapping("/addproducttolist")
    @ApiOperation(value = "Put existed product to existed list", response = java.util.List.class)
    public ResponseEntity<?> addProductToList(@RequestBody long productId, @RequestBody long listId) {
        return listService.addProductToList(productId, listId);
    }
}
