package com.gnanig.training.todolist;


import com.gnanig.training.todolist.RequestDataBody.DateTaskData;
import com.gnanig.training.todolist.database.MyToDoListData;
import com.gnanig.training.todolist.services.MyToDoList;
import com.gnanig.training.todolist.services.MyToDoListImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

class TodolistApplicationTests {

    MyToDoList obj = new MyToDoListImpl();
//    DateTaskData dateTask = new DateTaskData();

    @Test
    void dateTaskTest() {
//        dateTask.setTask("complete unit testing");
//        dateTask.setDate("23.12.31");
//        dateTask.setCompletionStatus("not completed");
//        obj.create("23.12.31", "complete unit testing");
        Assertions.assertEquals("[]",obj.read());
    }

}
