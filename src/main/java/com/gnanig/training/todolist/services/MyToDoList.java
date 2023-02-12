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

    int update(int serialNum, String newDate, String newTask);

    int delete(int serialNum);

    int markComplete(int serialNum);
}



