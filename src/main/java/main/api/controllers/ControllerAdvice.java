package main.api.controllers;

import lombok.Data;
import main.api.exceptions.EmptyDataBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<BadRequest> noSuchElementException() {
        return new ResponseEntity<>(new BadRequest(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmptyDataBaseException.class)
    public ResponseEntity<EmptyDB> noElementsException() {
        return new ResponseEntity<>(new EmptyDB(), HttpStatus.NOT_FOUND);
    }

    @Data
    public class BadRequest {
        private String name = "bad #id";
    }

    @Data
    public class EmptyDB {
        private String name = "There are no records in DataBase.";
    }
}
