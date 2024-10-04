package com.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Gostos extends Preferencia {
    private List<String> gostos = new ArrayList<>();

    // Construtor
    public Gostos() {
        super(null);
    }

    public List<String> getGostos() {
        return gostos;
    }

    @Override
    public
    void adicionarPreferencia(String tag) {
        if (!gostos.contains(tag)) {
            gostos.add(tag);
            System.out.println("Gosto adicionado: " + tag);
        }
    }

    @Override
    public
    void removerPreferencia(String tag) {
        if (gostos.remove(tag)) {
            System.out.println("Gosto removido: " + tag);
        }
    }
}
