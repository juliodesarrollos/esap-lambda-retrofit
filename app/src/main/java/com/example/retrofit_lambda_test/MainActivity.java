package com.example.retrofit_lambda_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit_lambda_test.iCliente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private TextView textView;
    private static String url = "https://iidkii3go3gzomcde6cqk72qya0lankk.lambda-url.us-east-2.on.aws";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.txtClientes);
        listarClientes();
    }

    public void listarClientes() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        final iCliente inter = retrofit.create(iCliente.class);
        Call<ArrayList<Cliente>> cal = inter.listarClientes();
        cal.enqueue(new Callback<ArrayList<Cliente>>() {
            @Override
            public void onResponse(Call<ArrayList<Cliente>> call, Response<ArrayList<Cliente>> response) {
                clientes.clear();
                clientes = response.body();
                appendClientes();
            }

            @Override
            public void onFailure(Call<ArrayList<Cliente>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void appendClientes () {
        for (Cliente cliente:clientes) {
            textView.setText("");
            textView.append("ID: " + cliente.getCliente_id() + " correo: " + cliente.getCliente_correo());
        }
    }
}