package com.gnanig.training.todolist.controller;

import com.gnanig.training.todolist.RequestDataBody.DateTaskData;
import com.gnanig.training.todolist.services.MyToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping(path = "toDoList")
@RestController
public class MyToDoListChoices {


    @Autowired
    public MyToDoList myToDoList;

    @RequestMapping(path = "create", method = {RequestMethod.POST},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createToDo(@RequestBody DateTaskData requestData) {
        if (requestData.getDate() == null && requestData.getTask() == null)
            return ResponseEntity.ok("No data provided for the current To-Do.\n" + myToDoList.read());
        else {
            myToDoList.create(requestData.getDate(), requestData.getTask());
            return ResponseEntity.ok(myToDoList.read());
        }
    }

    @RequestMapping(path = "read", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> readToDo() {
        if (Objects.equals(myToDoList.read(), "{}"))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("To-Do list is empty, " +
                    "nothing to display.\n" + myToDoList.read());
        else
            return ResponseEntity.ok(myToDoList.read());
    }


    @RequestMapping(path = {"update/{serialNumber}"}, method = {RequestMethod.PUT},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateToDo(@RequestBody DateTaskData requestData,
                                             @PathVariable Integer serialNumber) {
        String tempNewDate = requestData.getNewDate();
        String tempNewTask = requestData.getNewTask();
        Boolean tempNewCompletionStatus = requestData.getNewCompletionStatus();

        if (myToDoList.returnLength(serialNumber)) {
            if (Objects.equals(myToDoList.read(), "{}"))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(myToDoList.read());
            else if (tempNewDate == null && tempNewTask == null && tempNewCompletionStatus == null) {
                myToDoList.update(serialNumber, tempNewDate, tempNewTask, tempNewCompletionStatus);
                return ResponseEntity.ok("No modification done in the To-Do.\n" + myToDoList.read());
            } else {
                myToDoList.update(serialNumber, tempNewDate, tempNewTask, tempNewCompletionStatus);
                return ResponseEntity.ok(myToDoList.read());
            }
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid serial number\n" + myToDoList.read());
    }

    @RequestMapping(path = {"delete/{serialNumber}"}, method = {RequestMethod.DELETE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> deleteToDo(@PathVariable Integer serialNumber) {
        if (myToDoList.returnLength(serialNumber)) {
            if (Objects.equals(myToDoList.read(), "[]"))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(myToDoList.read());
            else {
                myToDoList.delete(serialNumber);
                return ResponseEntity.ok(myToDoList.read());
            }
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid serial number\n" + myToDoList.read());
    }
}

