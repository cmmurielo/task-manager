package com.cmmurielo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static com.cmmurielo.utils.Constants.*;

public class SQLiteConnection {

    Logger logger = LoggerFactory.getLogger(SQLiteConnection.class);

    private final String url;

    public SQLiteConnection() {
        Properties properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (is != null) {
                properties.load(is);
            } else {
                logger.error("Archivo de propiedades no encontrado en el classpath.");
            }
        } catch (IOException e) {
            logger.error("Error al cargar el archivo de propiedades", e);
        }
        this.url = properties.getProperty("sqlite.url");
        try (Connection conn = this.getConnection()) {
            if (conn != null) {
                createTableIfNotExists(conn);
            }
        } catch (SQLException e) {
            logger.error("Error al crear la tabla", e);
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            logger.error("Error al conectar a la Base de datos: {}", url, e);
            throw new RuntimeException(e);
        }
    }

    private void createTableIfNotExists(Connection conn) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS tasks ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "description TEXT NOT NULL, "
                + "is_completed BOOLEAN NOT NULL, "
                + "created TEXT NOT NULL, "
                + "active BOOLEAN NOT NULL" + ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            logger.info("Tabla 'Task' verificada/existente.");
        } catch (SQLException e) {
            logger.error("Error al crear/verificar la tabla 'tasks'", e);
        }
    }

}
