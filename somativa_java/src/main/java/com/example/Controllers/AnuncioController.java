package com.example.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.example.Models.Anuncio;

public class AnuncioController {
    private static final String FILE_NAME = "Data/anuncios.txt";
    private List<Anuncio> anuncios;

    // Construtor
    public AnuncioController() {
        this.anuncios = new ArrayList<>();
        criarDiretorio();
        carregarAnuncios();
    }

    // Método para criar a pasta Data, se não existir
    private void criarDiretorio() {
        Path directoryPath = Paths.get("Data");
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para adicionar um anúncio
    public void adicionarAnuncio(String titulo, String descricao, String imagem) {
        Anuncio anuncio = new Anuncio(titulo, descricao);
        anuncio.setImagem(imagem);
        anuncios.add(anuncio);
        salvarAnuncios();
    }

    // Método para salvar anúncios no arquivo
    private void salvarAnuncios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Anuncio anuncio : anuncios) {
                writer.write(anuncio.getTitulo() + "," + anuncio.getDescricao() + "," + anuncio.getImagem());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar anúncios do arquivo
    private void carregarAnuncios() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // Se o arquivo não existir, não há anúncios para carregar
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 3) {
                    Anuncio anuncio = new Anuncio(dados[0], dados[1]);
                    anuncio.setImagem(dados[2]);
                    anuncios.add(anuncio);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para obter todos os anúncios
    public List<Anuncio> getAnuncios() {
        return anuncios;
    }
}
