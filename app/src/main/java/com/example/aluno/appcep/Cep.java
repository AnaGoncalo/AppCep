package com.example.aluno.appcep;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Cep {

    private String cep;
    private String localidade;
    private String logradouro;

    public Cep() {

    }

    public Cep(String cep, String localidade, String logradouro) {
        this.cep = cep;
        this.localidade = localidade;
        this.logradouro = logradouro;
    }

    public String getCep(){
        return cep;
    }
    public String getLocalidade(){
        return localidade;
    }
    public String getLogradouro(){
        return logradouro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
}
