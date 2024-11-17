package com.example.retrofit_lambda_test;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.ArrayList;

public interface iCliente {
    @GET("/")
    Call<ArrayList<Cliente>> listarClientes();

}
