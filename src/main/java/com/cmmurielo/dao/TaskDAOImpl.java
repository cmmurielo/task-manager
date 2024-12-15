package com.cmmurielo.dao;

import static com.cmmurielo.utils.Constants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmmurielo.model.Task;
import com.cmmurielo.utils.QueryConstant;
import com.cmmurielo.utils.SQLiteConnection;

public class TaskDAOImpl implements TaskDAO {

    Logger logger = LoggerFactory.getLogger(TaskDAOImpl.class);

    SQLiteConnection postgresConnection = new SQLiteConnection();

    public List<Task> getAllTasksActive() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = QueryConstant.LIST_ALL_TASK_ACTIVE;

        try (Connection connection = postgresConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet allTaskActive = statement.executeQuery(query)) {

            while (allTaskActive.next()) {
                tasks.add(new Task(allTaskActive.getInt(ID), allTaskActive.getString(DESCRIPTION),
                        allTaskActive.getBoolean(IS_COMPLETED), allTaskActive.getString(CREATED),
                        allTaskActive.getBoolean(ACTIVE)));
            }
        }
        return tasks;
    }

    public void addTask(String description) throws SQLException {
        Task task = new Task(description);
        String query = QueryConstant.ADD_TASK;
        try (Connection connection = postgresConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setBoolean(2, task.getCompleted());
            preparedStatement.setString(3, task.getCreated());
            preparedStatement.setBoolean(4, task.getActive());
            preparedStatement.executeUpdate();
            logger.info("Tarea creada");
        }
    }

    public List<Task> getNotCompletedTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = QueryConstant.LIST_NOT_COMPLETE_TASKS;

        try (Connection connection = postgresConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet notCompletedTask = statement.executeQuery(query)) {

            while (notCompletedTask.next()) {
                tasks.add(new Task(notCompletedTask.getInt(ID), notCompletedTask.getString(DESCRIPTION),
                        notCompletedTask.getBoolean(IS_COMPLETED), notCompletedTask.getString(CREATED),
                        notCompletedTask.getBoolean(ACTIVE)));
            }
        }
        return tasks;
    }

    public List<Task> getCompletedTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = QueryConstant.LIST_COMPLETE_TASKS;

        try (Connection connection = postgresConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet completedTask = statement.executeQuery(query)) {

            while (completedTask.next()) {
                tasks.add(new Task(completedTask.getInt(ID), completedTask.getString(DESCRIPTION),
                        completedTask.getBoolean(IS_COMPLETED), completedTask.getString(CREATED),
                        completedTask.getBoolean(ACTIVE)));
            }
        }
        return tasks;
    }

    public void competeTask(int idTask) throws SQLException {
        String query = QueryConstant.COMPLETE_TASK;
        try (Connection connection = postgresConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, idTask);
            preparedStatement.executeUpdate();
            logger.info("Tarea completada");
        } catch (SQLException e) {
            System.out.println("No existe la tarea con el identificador: " + idTask);
        }
    }

    public void deleteTask(int idTask) {
        String query = QueryConstant.DELETE_TASK;
        try (Connection connection = postgresConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, idTask);
            preparedStatement.executeUpdate();
            logger.info("Tarea eliminada (desactivada)");
        } catch (SQLException e) {
            System.out.println("No existe la tarea con el identificador: " + idTask);
        }
    }

    public void deleteAllTaskCompleted() {
        String query = QueryConstant.DELETE_ALL_TASK_COMPLETED;
        try (Connection connection = postgresConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.executeUpdate();
            logger.info("Tareas completadas eliminadas (desactivada)s");
        } catch (SQLException e) {
            logger.error("No se logra eliminar las tareas completadas", e);
        }
    }
}
