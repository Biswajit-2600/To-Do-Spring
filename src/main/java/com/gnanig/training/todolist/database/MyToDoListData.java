package com.gnanig.training.todolist.database;

import com.gnanig.training.todolist.RequestDataBody.DateTaskData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyToDoListData {

    static MyToDoListData obj = new MyToDoListData();

    List<DateTaskData> toDo;

    public List<DateTaskData> getToDo() {
        return toDo;
    }

    private MyToDoListData() {
        toDo = new ArrayList<>();
    }

    public static MyToDoListData getInstance() {
        return obj;
    }



}