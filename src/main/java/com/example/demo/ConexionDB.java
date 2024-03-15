package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

    public static Connection getConection() {
        Connection con = null;

        String servidor = "localhost";
        String usuario = "root";
        String password = "taliTakumi514";
        String puerto = "3306";
        String db = "prueba";

        try {
            String url = "jdbc:mariadb://" + servidor + ":" + puerto + "/" + db;
            con = DriverManager.getConnection(url, usuario, password);
        } catch (Exception ex) {
            con = null;
        }

        return con;
    }

}
