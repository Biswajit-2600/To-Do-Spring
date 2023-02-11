/**
 * This is a simple Interface-based To-Do-List Application
 * It implements Java libraries and OOP concepts
 * Allows the user to perform CRUD operations on the to-do-list
 * Uses Spring Boot to convert it to a REST API and implements all the functionalities using POSTMAN
 *
 * @author :- Biswajit Panda
 * @version :- 1.0.0
 */
package com.gnanig.training.todolist.services;

public interface MyToDoList {

    void create(String date, String to_do);

    String read();

    String update(int serialNum, String newDate, String newTask);

    String delete(int serialNum);

    String markComplete(int serialNum);
}

// return type should be wrapped request response format


