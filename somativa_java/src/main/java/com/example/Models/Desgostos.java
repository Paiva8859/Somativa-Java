package com.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Desgostos extends Preferencia{
    List<String> desgostos = new ArrayList<>();

    // Construtor
    public Desgostos(String tag) {
        super(tag);
    }

    // Getters and setters
    public List<String> getDesgostos() {
        return desgostos;
    }

    public void setDesgostos(List<String> desgostos) {
        this.desgostos = desgostos;
    }

    // Overrides da superclasse
    @Override
    void adicionarPreferencia() {
        System.out.println("Chegou no método de adicionar desgosto"); // SOUT para teste
    }

    @Override
    void removerPreferencia() {
        System.out.println("Chegou no método de remover desgosto"); // SOUT para teste
    }
}

