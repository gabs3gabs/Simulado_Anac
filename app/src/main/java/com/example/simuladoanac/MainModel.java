package com.example.simuladoanac;

public class MainModel {

    private String nome;
    private int provas;
    private String url;

    public MainModel(){
        //Firebase
    }

    public MainModel(String nome, int provas, String url) {
        this.nome = nome;
        this.provas = provas;
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getProvas() {
        return provas;
    }

    public void setProvas(int provas) {
        this.provas = provas;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
