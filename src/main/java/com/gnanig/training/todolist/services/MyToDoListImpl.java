package com.gnanig.training.todolist.services;

import com.gnanig.training.todolist.RequestDataBody.OriginalDateTask;
import com.gnanig.training.todolist.database.MyToDoListData;
import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MyToDoListImpl implements MyToDoList {

    Scanner scn = new Scanner(System.in);
    MyToDoListData data = MyToDoListData.getInstance();
    List<OriginalDateTask> list = data.getToDo();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void create(String date, String to_do) {
        OriginalDateTask obj = new OriginalDateTask();
        obj.setDate(date);
        obj.setTask(to_do);
        if (date != null || to_do != null)
            obj.setCompletionStatus("not completed");
        list.add(obj);
    }

    @Override
    public String read() {
        return gson.toJson(list);
    }

    @Override
    public int update(int serialNum, String newDate, String newTask) {
        if (serialNum <= list.size()) {

            OriginalDateTask obj = list.get(serialNum - 1);

            if (newTask != null && newDate != null) {
                obj.setTask(newTask);
                obj.setDate(newDate);
            } else if (newDate != null)
                obj.setDate(newDate);
            else if (newTask != null)
                obj.setTask(newTask);
            return 1;
        } else
            return 0;
    }

    @Override
    public int markComplete(int serialNum) {
        if (serialNum <= list.size()) {
            OriginalDateTask obj = list.get(serialNum - 1);
            obj.setCompletionStatus("completed");
            return 1;
        } else
            return 0;
    }


    @Override
    public int delete(int serialNum) {
        if (serialNum <= list.size()) {
            list.remove(serialNum - 1);
            return 1;
        } else {
            return 0;
        }
    }
}
