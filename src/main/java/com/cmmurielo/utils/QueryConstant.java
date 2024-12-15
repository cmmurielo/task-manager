package com.cmmurielo.utils;

public class QueryConstant {
    public static final String LIST_ALL_TASK_ACTIVE = "SELECT * FROM tasks WHERE active = true ORDER BY id DESC";
    public static final String ADD_TASK = "INSERT INTO tasks (description, is_completed, created, active) values (?, ?, ?, ?)";
    public static final String COMPLETE_TASK = "UPDATE tasks SET is_completed=true WHERE id=? AND active=true;";
    public static final String DELETE_TASK = "UPDATE tasks SET active=false WHERE id=? AND active=true;";
    public static final String DELETE_ALL_TASK_COMPLETED = "UPDATE tasks SET active=false WHERE is_completed=true;";
    public static final String LIST_NOT_COMPLETE_TASKS = "SELECT * FROM tasks WHERE is_completed = false AND active=true ORDER BY id DESC";
    public static final String LIST_COMPLETE_TASKS = "SELECT * FROM tasks WHERE is_completed = true AND active=true ORDER BY id DESC";

    private QueryConstant() {
    }
}
