package com.gnanig.training.todolist.RequestDataBody;

public class DateTaskData {

    private String date, task, completionStatus, newDate, newTask;
    Boolean newCompletionStatus;

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

    public boolean getNewCompletionStatus() {
        return newCompletionStatus;
    }

    public void setNewCompletionStatus(boolean newCompletionStatus) {
        this.newCompletionStatus = newCompletionStatus;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String toString() {
        return "[" + date + " " + task + "]";
    }
}
