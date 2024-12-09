package com.cmmurielo;

import com.cmmurielo.dao.TaskDAOImpl;
import com.cmmurielo.service.TaskService;
import com.cmmurielo.utils.Utilities;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        TaskDAOImpl taskDAO = new TaskDAOImpl();
        TaskService taskService = new TaskService(taskDAO);
        Utilities utilities = new Utilities();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            utilities.clearConsole();
            showMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
            case 1:
                System.out.println("Indica la descripcion para la tarea: ");
                String description = scanner.nextLine();
                taskService.addTask(description);
                break;
            case 2:
                System.out.println("Listando todas las tareas...\n");
                taskService.getTasksActive().forEach(task -> System.out.println(task.toString()));
                goBack(scanner);
                break;
            case 3:
                System.out.println("Listando solo las tareas pendientes...\n");
                taskService.getNotCompletedTasks().forEach(task -> System.out.println(task.toString()));
                goBack(scanner);
                break;
            case 4:
                System.out.println("Listando solo las tareas completadas...\n");
                taskService.getCompletedTasks().forEach(task -> System.out.println(task.toString()));
                goBack(scanner);
                break;
            case 5:
                System.out.println("Introduce el ID de la tarea a completar:");
                int taskIdToComplete = scanner.nextInt();
                taskService.completeTask(taskIdToComplete);
                goBack(scanner);
                break;
            case 6:
                System.out.println("Introduce el ID de la tarea a eliminar:");
                int taskIdToDelete = scanner.nextInt();
                taskService.deleteTask(taskIdToDelete);
                goBack(scanner);
                break;
            case 7:
                System.out.println("Eliminando todas las tareas completadas...");
                taskService.deleteAllTaskCompleted();
                goBack(scanner);
                break;
            case 0:
                System.out.println("Saliendo...");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        }
    }

    private static void goBack(Scanner scanner) {
        System.out.println("\nPresiona cualquier tecla para volver");
        scanner.nextLine();
    }

    private static void showMenu() {
        System.out.println("\nGESTOR DE TAREAS (TASKS)\n");
        System.out.println("Selecciona una opción:");
        System.out.println("\t1. Agrega una nueva tarea");
        System.out.println("\t2. Listar todas las tareas");
        System.out.println("\t3. Listar solo las tareas pendientes");
        System.out.println("\t4. Listar solo las tareas completadas");
        System.out.println("\t5. Completar una tarea");
        System.out.println("\t6. Eliminar una tarea");
        System.out.println("\t7. Eliminar todas las tareas completadas");
        System.out.println("\t0. SALIR");
    }
}
