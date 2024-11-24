package com.cmmurielo.service;

import com.cmmurielo.dao.TaskDAO;
import com.cmmurielo.dao.TaskDAOImpl;
import com.cmmurielo.model.Task;

import java.sql.SQLException;
import java.util.List;

public class TaskService  {

    private TaskDAO taskDAO;

    public TaskService(TaskDAOImpl taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> getTasksActive() throws SQLException {
        return taskDAO.getAllTasksActive();
    }

    public void addTask(String description) throws SQLException {
        taskDAO.addTask(description);
    }

    public List<Task> getCompletedTasks() throws SQLException {
        return taskDAO.getCompletedTasks();
    }

    public List<Task> getNotCompletedTasks() throws SQLException {
        return taskDAO.getNotCompletedTasks();
    }

    public void completeTask(int idTask) throws SQLException {
        taskDAO.competeTask(idTask);
    }

    public void deleteTask(int idTask) throws SQLException {
        taskDAO.deleteTask(idTask);
    }

    public void deleteAllTaskCompleted() throws SQLException {
        taskDAO.deleteAllTaskCompleted();
    }

}
