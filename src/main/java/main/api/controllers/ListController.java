package main.api.controllers;

import main.api.entitys.List;
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
    public ResponseEntity<?> getProductById(@PathVariable long id) {
            return listService.findById(id);
    }

    @GetMapping("/getlistwithkcal/{id}")
    public ResponseEntity<?> getListByIdWithTotalKCal(@PathVariable long id) {
        return listService.findByIdAndGetTotalKCal(id);
    }

    @GetMapping("/getalllists")
    public ResponseEntity<?> getAllLists() {
        java.util.List<List> actualList = new ArrayList<>();
        Iterator<List> iterator = listService.findAll().iterator();
        iterator.forEachRemaining(actualList::add);
        return new ResponseEntity<>(actualList, HttpStatus.OK);
    }

    @PostMapping("/addnewlist")
    public ResponseEntity<?> addNewListWithProducts(@RequestBody String name) {
        return listService.addNewList(name);
    }
    @PutMapping("/addproducttolist")
    public ResponseEntity<?> addProductToList(@RequestBody long productId, @RequestBody long listId) {
        return listService.addProductToList(productId, listId);
    }
}
