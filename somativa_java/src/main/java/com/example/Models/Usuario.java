package com.example.Models;

public class Usuario {
    // Atributos
    String nome;
    String email;
    String senha;
    boolean isAdm = false;
    
    // Construtor
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    // Getters and setters
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public boolean isAdm() {
        return isAdm;
    }
    
    public void setAdm(boolean isAdm) {
        this.isAdm = isAdm;
    }
}
