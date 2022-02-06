package main.api.services;

import main.api.controllers.ControllerAdvice;
import main.api.dto.DTOMessage;
import main.api.dto.DTOSuccessfully;
import main.api.entitys.List;
import main.api.entitys.Product;
import main.api.repo.ListRepo;
import main.api.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ListService {
    @Autowired
    private ListRepo listRepo;
    @Autowired
    private ProductRepo productRepo;

    public ResponseEntity<?> findById(long id) {
        if (listRepo.findById(id).isPresent())
        {
            return new ResponseEntity<>(listRepo.findById(id).get(), HttpStatus.OK);
        }
        else {
            return new ControllerAdvice().noSuchElementException();
        }
    }

    public java.util.List<List> findAll() {
        Iterable<List> iterable = listRepo.findAll();
        java.util.List<List> listList = new ArrayList<>();
        for (List list : iterable) {
            listList.add(list);
        }
        return listList;
    }

    public List addNewList(long id, String name, ArrayList<Product> products) {
        List newList = new List();
        newList.setId(id);
        newList.setName(name);
        newList.setProductList(products);
        return newList;
    }

    public ResponseEntity<?> addNewList(String name) {
        List newList = new List();
        newList.setName(name);
        listRepo.save(newList);
        return new ResponseEntity<>(new DTOSuccessfully(null, new Date().getTime() / 1000, new DTOMessage()), HttpStatus.OK);
    }

    public ResponseEntity<?> addProductToList(long productId, long listId) {
        if (listRepo.findById(listId).isPresent()) {
            List currentList = listRepo.findById(listId).get();
            java.util.List<Product> changedProductList = currentList.getProductList();
            if (productRepo.findById(productId).isPresent()) {
                changedProductList.add(productRepo.findById(productId).get());
            }
            currentList.setProductList(changedProductList);
            return new ResponseEntity<>(currentList, HttpStatus.OK);
        }
        return new ControllerAdvice().noElementsException();
    }
}
