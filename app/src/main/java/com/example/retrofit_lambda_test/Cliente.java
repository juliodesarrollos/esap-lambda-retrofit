package com.example.retrofit_lambda_test;

public class Cliente {
    private int cliente_id;
    private String cliente_correo;

    public Cliente (int cliente_id, String cliente_correo){
        this.cliente_id = cliente_id;
        this.cliente_correo = cliente_correo;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getCliente_correo() {
        return cliente_correo;
    }

    public void setCliente_correo(String cliente_correo) {
        this.cliente_correo = cliente_correo;
    }
}
