package com.example.threads_alvaro;

import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteFragment extends Fragment {


    private ClienteViewModel mViewModel;
    Conexio con;
    private Button add;
    public static class insertUserTask extends AsyncTask<Void,Void,Void> {

        @Override
        public Void doInBackground(Void... voids) {
            Connection conn = null;

            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://172.31.90.30:5432/pruebas","postgres","postgres");
                Statement st = conn.createStatement();
                st.executeUpdate("insert into cliente values(default,'joanet','1234-03-03');");
                conn.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    Thread sqlThread = new Thread(){
        public void run(){
            Connection conn = null;

            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","patata","1234");
                Statement st = conn.createStatement();
                st.executeUpdate("insert into cliente values(default,'joanet','1234-03-03');");
                conn.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        mViewModel = ViewModelProviders.of(this).get(ClienteViewModel.class);
        View root=inflater.inflate(R.layout.cliente_fragment, container, false);
        add= root.findViewById(R.id.b_add);
        final insertUserTask insertUserTask = new insertUserTask();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlThread.start();

            }
        });






        return root;
    }



}
