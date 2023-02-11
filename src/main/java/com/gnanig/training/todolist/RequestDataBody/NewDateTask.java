package com.gnanig.training.todolist.RequestDataBody;

public class NewDateTask {
    private String newDate, newTask;
    private int serialNumber;

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public String getNewTask() {
        return newTask;
    }

    public void setNewTask(String newTask) {
        this.newTask = newTask;
    }
}
