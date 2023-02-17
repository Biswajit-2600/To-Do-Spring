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

    void create(String date, String task);

    String read();

    void update(int serialNum, String newDate, String newTask, Boolean newCompletionStatus);

    void delete(int serialNumber);

    boolean returnLength(int serialNumber);
}



