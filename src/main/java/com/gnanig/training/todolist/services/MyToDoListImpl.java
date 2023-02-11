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
        obj.setCompletionStatus("not completed");
        list.add(obj);
    }

    @Override
    public String read() {
        return gson.toJson(list);
    }

    @Override
    public String update(int serialNum, String newDate, String newTask) {
        if (list.isEmpty()) {
            return "List is empty, nothing to update.";
        } else {
            if (serialNum <= list.size()) {
                OriginalDateTask obj = list.get(serialNum - 1);

                if (newTask != null && newDate != null) {
                    obj.setTask(newTask);
                    obj.setDate(newDate);
                    return "Task updated successfully.";
                } else if (newDate != null) {
                    obj.setDate(newDate);
                    return "Task updated successfully.";
                } else if (newTask != null) {
                    obj.setTask(newTask);
                    return "Task updated successfully.";
                } else
                    return "No update done in the To-Do";
            } else
                return "Invalid serial number.";
        }
    }

    @Override
    public String markComplete(int serialNum) {
        if (list.isEmpty()) {
            return "List is empty, nothing to mark.";
        } else {
            if (serialNum <= list.size()) {
                OriginalDateTask obj = list.get(serialNum - 1);
                obj.setCompletionStatus("completed");
                return "Task marked successfully.";
            } else {
                return "Invalid serial number.";
            }
        }
    }

    @Override
    public String delete(int serialNum) {
        if (list.isEmpty()) {
            return "List is empty, nothing to delete.";
        } else {
            if (serialNum <= list.size()) {
                list.remove(serialNum - 1);
                return "Task deleted successfully";
            } else {
                return "Invalid serial number.";
            }
        }
    }
}
