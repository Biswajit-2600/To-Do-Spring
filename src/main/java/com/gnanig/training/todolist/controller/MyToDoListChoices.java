package com.gnanig.training.todolist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gnanig.training.todolist.RequestDataBody.NewDateTask;
import com.gnanig.training.todolist.RequestDataBody.OriginalDateTask;
import com.gnanig.training.todolist.services.MyToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MyToDoListChoices {


    @Autowired
    public MyToDoList myToDoList;

    @RequestMapping(path = "list/read", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> readToDo() {
        if (Objects.equals(myToDoList.read(), "[]"))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(myToDoList.read());
        else
            return ResponseEntity.ok(myToDoList.read());
    }

    @RequestMapping(path = "list/create", method = {RequestMethod.POST},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createToDo(@RequestBody OriginalDateTask requestData) {
        if (requestData.getDate() == null || requestData.getTask() == null) {
            myToDoList.create(requestData.getDate(), requestData.getTask());
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(myToDoList.read());
        } else {
            myToDoList.create(requestData.getDate(), requestData.getTask());
            return ResponseEntity.accepted().body(myToDoList.read());
        }
    }

    @RequestMapping(path = "list/update", method = {RequestMethod.PUT},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateToDo(@RequestBody NewDateTask requestData) {
        if (requestData.getNewDate() == null || requestData.getNewTask() == null) {
            myToDoList.update(requestData.getSerialNumber(), requestData.getNewDate(), requestData.getNewTask());
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(myToDoList.read());
        } else {
            myToDoList.update(requestData.getSerialNumber(), requestData.getNewDate(), requestData.getNewTask());
            return ResponseEntity.ok(myToDoList.read());
        }
    }

    @RequestMapping(path = "list/delete", method = {RequestMethod.DELETE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> deleteToDo(@RequestBody NewDateTask requestData) {
        if (Objects.equals(myToDoList.read(), "[]"))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(myToDoList.read());
        else {
            myToDoList.delete(requestData.getSerialNumber());
            return ResponseEntity.ok(myToDoList.read());
        }
    }

    @RequestMapping(path = "list/mark.as.complete", method = {RequestMethod.PUT},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> markToDo(@RequestBody NewDateTask requestData) {
        if (Objects.equals(myToDoList.read(), "[]"))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(myToDoList.read());
        else {
            myToDoList.markComplete(requestData.getSerialNumber());
            return ResponseEntity.ok(myToDoList.read());
        }
    }
}

