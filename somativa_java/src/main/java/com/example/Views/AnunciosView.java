package com.example.Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.example.Controllers.AnuncioController;
import com.example.Controllers.PreferenciasController;
import com.example.Models.Anuncio;

public class AnunciosView extends JFrame {
    private AnuncioController anuncioController;
    private PreferenciasController preferenciasController;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private int currentIndex = 0;
    private Timer visualizacaoTimer;
    private boolean visualizacaoConcluida = false;

    public AnunciosView(String usuario) { // Adiciona parâmetro para nome do usuário
        // Configurações da janela
        setTitle("Exibição de Anúncio");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        anuncioController = new AnuncioController();
        preferenciasController = new PreferenciasController(anuncioController.getAnuncios(), usuario); // Passa o usuário
        System.out.println("Total de anúncios carregados: " + anuncioController.getAnuncios().size());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Botões para navegar pelos anúncios
        JButton nextButton = new JButton("Próximo");
        JButton prevButton = new JButton("Anterior");
        JButton saberMaisButton = new JButton("Saber Mais");
        JButton naoInteresseButton = new JButton("Não Tenho Interesse");

        // Ação do botão próximo
        nextButton.addActionListener(e -> {
            currentIndex = (currentIndex + 1) % anuncioController.getAnuncios().size();
            updateCard();
        });

        // Ação do botão anterior
        prevButton.addActionListener(e -> {
            currentIndex = (currentIndex - 1 + anuncioController.getAnuncios().size()) % anuncioController.getAnuncios().size();
            updateCard();
        });

        // Ação do botão "Saber Mais"
        saberMaisButton.addActionListener(e -> {
            Anuncio anuncio = anuncioController.getAnuncios().get(currentIndex);
            mostrarDetalhes(anuncio);
            preferenciasController.manipularTags("subir", anuncio.getTags());
            resetTimer();
        });

        // Ação do botão "Não Tenho Interesse"
        naoInteresseButton.addActionListener(e -> {
            Anuncio anuncio = anuncioController.getAnuncios().get(currentIndex);
            preferenciasController.manipularTags("descer", anuncio.getTags());
            resetTimer();
        });

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(saberMaisButton);
        buttonPanel.add(naoInteresseButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona todos os anúncios ao cardPanel
        for (Anuncio anuncio : anuncioController.getAnuncios()) {
            cardPanel.add(createAnuncioPanel(anuncio), anuncio.getTitulo());
        }

        add(cardPanel, BorderLayout.CENTER);
        updateCard(); // Exibe o primeiro anúncio

        // Configura o Timer para 5 segundos
        visualizacaoTimer = new Timer(5000, e -> {
            if (!visualizacaoConcluida) {
                Anuncio anuncio = anuncioController.getAnuncios().get(currentIndex);
                preferenciasController.manipularTags("subir", anuncio.getTags());
            }
        });

        visualizacaoTimer.start(); // Inicia o Timer

        // Configura a janela para ser visível
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    private JPanel createAnuncioPanel(Anuncio anuncio) {
        JPanel anuncioPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes

        // Título do anúncio com fonte aumentada
        JLabel tituloLabel = new JLabel(anuncio.getTitulo());
        tituloLabel.setFont(tituloLabel.getFont().deriveFont(16f)); // Aumenta o tamanho da fonte
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expande horizontalmente
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza
        anuncioPanel.add(tituloLabel, gbc);

        // Imagem do anúncio
        ImageIcon originalIcon = new ImageIcon(anuncio.getImagem());
        Image scaledImage = originalIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); // Tamanho fixo
        JLabel imagemLabel = new JLabel(new ImageIcon(scaledImage));
        imagemLabel.setPreferredSize(new Dimension(200, 150)); // Define tamanho fixo
        gbc.gridy = 1; // Ajustar para nova posição
        gbc.weighty = 1.0; // Permite que a imagem expanda verticalmente
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza
        anuncioPanel.add(imagemLabel, gbc);

        return anuncioPanel;
    }

    private void mostrarDetalhes(Anuncio anuncio) {
        // Criar uma nova janela para mostrar os detalhes do anúncio
        JDialog detalhesDialog = new JDialog(this, "Detalhes do Anúncio", true);
        detalhesDialog.setLayout(new BorderLayout());
        detalhesDialog.setSize(300, 200);

        JLabel tituloLabel = new JLabel("Título: " + anuncio.getTitulo());
        JLabel descricaoLabel = new JLabel("Descrição: " + anuncio.getDescricao());
        ImageIcon originalIcon = new ImageIcon(anuncio.getImagem());
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 75, Image.SCALE_SMOOTH);
        JLabel imagemLabel = new JLabel(new ImageIcon(scaledImage));

        JPanel detalhesPanel = new JPanel(new GridLayout(3, 1));
        detalhesPanel.add(tituloLabel);
        detalhesPanel.add(descricaoLabel);
        detalhesPanel.add(imagemLabel);

        detalhesDialog.add(detalhesPanel, BorderLayout.CENTER);
        detalhesDialog.setLocationRelativeTo(this);
        detalhesDialog.setVisible(true);
    }

    private void updateCard() {
        if (!anuncioController.getAnuncios().isEmpty()) {
            visualizacaoConcluida = false; // Reinicia o estado de visualização
            cardLayout.show(cardPanel, anuncioController.getAnuncios().get(currentIndex).getTitulo());
        }
    }

    private void resetTimer() {
        visualizacaoTimer.restart(); // Reinicia o Timer
        visualizacaoConcluida = true; // Marca a visualização como concluída
    }
}