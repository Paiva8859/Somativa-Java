package com.example.Models;

public abstract class Preferencia {
    String tag;

    // Construtor
    public Preferencia(String tag) {
        this.tag = tag;
    }
    
    // Getters and Setters
    public String getTag() {
        return tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }

    // Métodos abstratos
    abstract void adicionarPreferencia(String tag);
    abstract void removerPreferencia(String tag);
}
