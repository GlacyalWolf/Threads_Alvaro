package com.example.threads_alvaro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexio {
    Thread sqlThread = new Thread(){
        public void run(){
            Connection conn = null;

            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgres://localhost:5432/pruebas","postgres","cresxaisto1999");
                Statement st = conn.createStatement();
                st.executeUpdate("insert into cliente values(default,'joanet','1234-03-03');");
                conn.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }


        }
    };
}
