package com.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Desgostos extends Preferencia {
    private List<String> desgostos = new ArrayList<>();

    // Construtor
    public Desgostos() {
        super(null);
    }

    public List<String> getDesgostos() {
        return desgostos;
    }

    @Override
    public
    void adicionarPreferencia(String tag) {
        if (!desgostos.contains(tag)) {
            desgostos.add(tag);
            System.out.println("Desgosto adicionado: " + tag);
        }
    }

    @Override
    public
    void removerPreferencia(String tag) {
        if (desgostos.remove(tag)) {
            System.out.println("Desgosto removido: " + tag);
        }
    }
}
