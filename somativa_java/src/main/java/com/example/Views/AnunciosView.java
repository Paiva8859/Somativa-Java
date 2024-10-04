package com.example.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.example.Controllers.AnuncioController;
import com.example.Models.Anuncio;

public class AnunciosView extends JFrame {
    private AnuncioController anuncioController;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private int currentIndex = 0;

    public AnunciosView() {
        // Configurações da janela
        setTitle("Exibição de Anúncio");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        anuncioController = new AnuncioController();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Botões para navegar pelos anúncios
        JButton nextButton = new JButton("Próximo");
        JButton prevButton = new JButton("Anterior");
        
        // Ação do botão próximo
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % anuncioController.getAnuncios().size();
                updateCard();
            }
        });

        // Ação do botão anterior
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex - 1 + anuncioController.getAnuncios().size()) % anuncioController.getAnuncios().size();
                updateCard();
            }
        });

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Adiciona todos os anúncios ao cardPanel
        for (Anuncio anuncio : anuncioController.getAnuncios()) {
            cardPanel.add(createAnuncioPanel(anuncio), anuncio.getTitulo());
        }

        add(cardPanel, BorderLayout.CENTER);
        updateCard(); // Exibe o primeiro anúncio

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

        // Descrição do anúncio
        JLabel descricaoLabel = new JLabel(anuncio.getDescricao());
        gbc.gridy = 1;
        anuncioPanel.add(descricaoLabel, gbc); // Corrigido para adicionar ao anúncioPanel

        // Imagem do anúncio
        ImageIcon originalIcon = new ImageIcon(anuncio.getImagem());
        Image scaledImage = originalIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); // Tamanho fixo
        JLabel imagemLabel = new JLabel(new ImageIcon(scaledImage));
        imagemLabel.setPreferredSize(new Dimension(200, 150)); // Define tamanho fixo
        gbc.gridy = 2;
        gbc.weighty = 1.0; // Permite que a imagem expanda verticalmente
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza
        anuncioPanel.add(imagemLabel, gbc);

        return anuncioPanel;
    }

    private void updateCard() {
        if (!anuncioController.getAnuncios().isEmpty()) {
            cardLayout.show(cardPanel, anuncioController.getAnuncios().get(currentIndex).getTitulo());
        }
    }
}
