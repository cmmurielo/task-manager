package com.cmmurielo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utilities {

    Logger logger = LoggerFactory.getLogger(Utilities.class);

    public LocalDateTime parseStringToLocalDateTime(String stringDate, String datePattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            return LocalDateTime.parse(stringDate, formatter);
        } catch (DateTimeParseException e) {
            logger.error("El formato de la fecha es incorrecto: {}", datePattern, e);
            return null;
        }
    }

    public String parseLocalDataTimeToString(LocalDateTime dateTime, String datePattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            return dateTime.format(formatter);
        } catch (DateTimeParseException e) {
            logger.error("El formato de la fecha es incorrecto: {}", datePattern, e);
            return null;
        }
    }

    public void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al limpiar la consola: " + e.getMessage());
        }
    }
}
