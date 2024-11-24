package com.cmmurielo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.cmmurielo.utils.Constants.*;

public class PostgresConnection {

    Logger logger = LoggerFactory.getLogger(PostgresConnection.class);

    private final String url;
    private final String username;
    private final String password;

    public PostgresConnection() {
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

        this.url = properties.getProperty(POSTGRES_DB);
        this.username = properties.getProperty(POSTGRES_USERNAME);
        this.password = properties.getProperty(POSTGRES_PASSWORD);
    }

    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.error("Error al conectar a la Base de datos: {}", url, e);
            throw new RuntimeException(e);
        }
    }
}
