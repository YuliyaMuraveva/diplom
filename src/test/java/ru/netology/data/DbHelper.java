package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbHelper {
    private final static QueryRunner runner = new QueryRunner();
    private static Properties prop = prop();
    private final static Connection conn = getConnect();


    private static Properties prop() {
        Properties properties = new Properties();
        try (InputStream is = DbHelper.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(is);
        } catch(IOException ex) { ex.printStackTrace(); }
        return properties;
    }


    @SneakyThrows
    private static Connection getConnect() {
        return DriverManager.getConnection(
                prop.getProperty("spring.datasource.url"),
                prop.getProperty("spring.datasource.username"),
                prop.getProperty("spring.datasource.password")
        );
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        val status = "SELECT status FROM payment_entity ORDER BY created DESC";
        return runner.query(conn, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public static Integer getPaymentAmount() {
        val amount = "SELECT amount FROM payment_entity ORDER BY created DESC";
        return runner.query(conn, amount, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCreditRequestStatus() {
        val status = "SELECT status FROM credit_request_entity ORDER BY created DESC";
        return runner.query(conn, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCreditId() {
        val id = "SELECT credit_id FROM order_entity ORDER BY created DESC";
        return runner.query(conn, id, new ScalarHandler<>());
    }
}
