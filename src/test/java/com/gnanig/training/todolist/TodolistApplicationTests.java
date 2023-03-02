package com.gnanig.training.todolist;

import com.gnanig.training.todolist.RequestDataBody.DateTaskData;
import com.gnanig.training.todolist.database.MyToDoListData;
import com.gnanig.training.todolist.services.MyToDoListImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TodolistApplicationTests {

    MyToDoListImpl obj = new MyToDoListImpl();

    @BeforeClass
    public static void dataLoad()
    {
        MyToDoListData data = MyToDoListData.getInstance();
        List<DateTaskData> toDolist = data.getToDo();
    }

    @Test
    void contextLoads() {
        obj.create("23.12.31","complete unit testing");
        obj.read();
    }

}
