package com.ajulay.entity;

import com.ajulay.constants.TaskConstant;
import com.ajulay.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private final List<Task> tasks;
    private int pointer;

    public Page(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List next(){
        List<Task> newPageList = new ArrayList<>();
        if ((pointer + TaskConstant.TASK_LIST_LENGTH) < tasks.size() ) {
            for (int i = 0; i < TaskConstant.TASK_LIST_LENGTH; i++) {
                newPageList.add(tasks.get(pointer + i));
            }
            pointer = pointer + TaskConstant.TASK_LIST_LENGTH;
            return newPageList;
        }
        return tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }
}
