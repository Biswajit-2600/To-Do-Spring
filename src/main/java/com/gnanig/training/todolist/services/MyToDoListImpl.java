package com.gnanig.training.todolist.services;

import com.gnanig.training.todolist.RequestDataBody.DateTaskData;
import com.gnanig.training.todolist.database.MyToDoListData;
import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyToDoListImpl implements MyToDoList {

    MyToDoListData data = MyToDoListData.getInstance();
    List<DateTaskData> toDolist = data.getToDo();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void create(String date, String task) {
        DateTaskData obj = new DateTaskData();
        if (task != null && date != null) {
            obj.setDate(date);
            obj.setTask(task);
            obj.setCompletionStatus("not completed");
        } else if (date != null) {
            obj.setDate(date);
            obj.setTask("not provided");
            obj.setCompletionStatus("not completed");
        } else if (task != null) {
            obj.setDate("not provided");
            obj.setTask(task);
            obj.setCompletionStatus("not completed");
        }
        toDolist.add(obj);
    }

    @Override
    public String read() {
        return gson.toJson(toDolist);
    }

    @Override
    public void update(int serialNum, String newDate, String newTask, Boolean newCompletionStatus) {
        if (serialNum <= toDolist.size()) {

            DateTaskData obj = toDolist.get(serialNum-1);
            if (newCompletionStatus!=null && newCompletionStatus)
                obj.setCompletionStatus("completed");
            if (newTask != null && newDate != null) {
                obj.setDate(newDate);
                obj.setTask(newTask);
            } else if (newDate != null)
                obj.setDate(newDate);
            else if (newTask != null)
                obj.setTask(newTask);
        }
    }

    @Override
    public void delete(int serialNum) {
        if (serialNum <= toDolist.size())
            toDolist.remove(serialNum);
    }

    @Override
    public boolean returnLength(int serialNumber) {
        return serialNumber <= toDolist.size();
    }
}
