package com.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Gostos extends Preferencia{
    List<String> gostos = new ArrayList<>();

    // Construtor
    public Gostos(String tag) {
        super(tag);
    }

    // Getters and setters
    public List<String> getGostos() {
        return gostos;
    }

    public void setGostos(List<String> gostos) {
        this.gostos = gostos;
    }

    // Overrides da superclasse
    @Override
    void adicionarPreferencia() {
        System.out.println("Chegou no método de adicionar gosto"); // SOUT para teste
    }

    @Override
    void removerPreferencia() {
        System.out.println("Chegou no método de remover gosto"); // SOUT para teste
    }
}
