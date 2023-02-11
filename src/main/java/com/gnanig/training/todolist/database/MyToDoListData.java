package com.gnanig.training.todolist.database;

import com.gnanig.training.todolist.RequestDataBody.OriginalDateTask;

import java.util.ArrayList;
import java.util.List;

public class MyToDoListData {

    static MyToDoListData obj = new MyToDoListData();

    List<OriginalDateTask> toDo;

    public List<OriginalDateTask> getToDo() {
        return toDo;
    }

    private MyToDoListData() {
        toDo = new ArrayList<>();
    }

    public static MyToDoListData getInstance() {
        return obj;
    }



}