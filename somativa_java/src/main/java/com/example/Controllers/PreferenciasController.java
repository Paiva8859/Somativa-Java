package com.example.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.Models.Anuncio;
import com.example.Models.Desgostos;
import com.example.Models.Gostos;

public class PreferenciasController {
    private List<Anuncio> anuncios;
    private Gostos gostos;
    private Desgostos desgostos;
    private String usuario; // Nome do usuário

    public PreferenciasController(List<Anuncio> anuncios, String usuario) {
        this.anuncios = anuncios;
        this.usuario = usuario;
        this.gostos = new Gostos();
        this.desgostos = new Desgostos();
        carregarPreferencias(); // Carrega as preferências ao iniciar
    }

    public void manipularTags(String acao, List<String> tags) {
        for (String tag : tags) {
            if ("subir".equals(acao)) {
                if (!gostos.getGostos().contains(tag) && !desgostos.getDesgostos().contains(tag)) {
                    gostos.adicionarPreferencia(tag);
                } else if (desgostos.getDesgostos().contains(tag)) {
                    desgostos.removerPreferencia(tag);
                    gostos.adicionarPreferencia(tag);
                }
            } else if ("descer".equals(acao)) {
                if (gostos.getGostos().contains(tag)) {
                    gostos.removerPreferencia(tag);
                } else if (!gostos.getGostos().contains(tag) && !desgostos.getDesgostos().contains(tag)) {
                    desgostos.adicionarPreferencia(tag);
                }
            }
        }
        salvarPreferencias(); // Salva as preferências após manipulação
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    private void salvarPreferencias() {
        List<String> linhasGostos = carregarPreferenciasExistentes("somativa_java/Data/gostos.txt");
        List<String> linhasDesgostos = carregarPreferenciasExistentes("somativa_java/Data/desgostos.txt");

        atualizarOuAdicionarPreferencias(linhasGostos, gostos.getGostos(), "gostos");
        atualizarOuAdicionarPreferencias(linhasDesgostos, desgostos.getDesgostos(), "desgostos");

        escreverPreferencias("somativa_java/Data/gostos.txt", linhasGostos);
        escreverPreferencias("somativa_java/Data/desgostos.txt", linhasDesgostos);
    }

    private List<String> carregarPreferenciasExistentes(String filePath) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                linhas.add(line);
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado ou erro ao ler.");
        }
        return linhas;
    }

    private void atualizarOuAdicionarPreferencias(List<String> linhas, List<String> preferencias, String tipo) {
        String usuarioLine = usuario + "," + String.join("|", preferencias);
        boolean usuarioEncontrado = false;

        for (int i = 0; i < linhas.size(); i++) {
            String[] parts = linhas.get(i).split(",", 2);
            if (parts.length > 1 && parts[0].equals(usuario)) {
                linhas.set(i, usuarioLine); // Atualiza a linha existente
                usuarioEncontrado = true;
                break;
            }
        }

        if (!usuarioEncontrado) {
            linhas.add(usuarioLine); // Adiciona uma nova linha se não encontrado
        }
    }

    private void escreverPreferencias(String filePath, List<String> linhas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String linha : linhas) {
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarPreferencias() {
        carregarGustos();
        carregarDesgostos();
    }

    private void carregarGustos() {
        // Carrega as tags de gostos se o usuário já tiver preferências
        for (String linha : carregarPreferenciasExistentes("somativa_java/Data/gostos.txt")) {
            String[] parts = linha.split(",", 2);
            if (parts.length > 1 && parts[0].equals(usuario)) {
                String[] tags = parts[1].split("\\|");
                for (String tag : tags) {
                    gostos.adicionarPreferencia(tag.trim());
                }
                break; // Sai do loop após encontrar o usuário
            }
        }
    }

    private void carregarDesgostos() {
        // Carrega as tags de desgostos se o usuário já tiver preferências
        for (String linha : carregarPreferenciasExistentes("somativa_java/Data/desgostos.txt")) {
            String[] parts = linha.split(",", 2);
            if (parts.length > 1 && parts[0].equals(usuario)) {
                String[] tags = parts[1].split("\\|");
                for (String tag : tags) {
                    desgostos.adicionarPreferencia(tag.trim());
                }
                break; // Sai do loop após encontrar o usuário
            }
        }
    }
}