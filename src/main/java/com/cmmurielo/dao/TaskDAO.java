package com.cmmurielo.dao;

import com.cmmurielo.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {
    List<Task> getAllTasksActive() throws SQLException;

    void addTask(String description) throws SQLException;

    List<Task> getNotCompletedTasks() throws SQLException;

    List<Task> getCompletedTasks() throws SQLException;

    void competeTask(int idTask) throws SQLException;

    void deleteTask(int idTask) throws SQLException;

    void deleteAllTaskCompleted() throws SQLException;
}
