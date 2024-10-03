package com.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Anuncio {
    // Atributos
    private String titulo;
    private String descricao;
    private String imagem;
    private List<String> tags = new ArrayList<>();

    // Construtor
    public Anuncio(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    // Getters and setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
