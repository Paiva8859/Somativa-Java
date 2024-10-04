package com.example.Views;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.example.Controllers.AnuncioController;

public class AdminView extends JFrame {
    private AnuncioController anuncioController;

    public AdminView() {
        anuncioController = new AnuncioController();
        setTitle("Painel do Administrador");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton btnAdicionarAnuncio = new JButton("Adicionar Anúncio");
        btnAdicionarAnuncio.addActionListener(e -> abrirJanelaAdicionarAnuncio());
        add(btnAdicionarAnuncio);
    }

    private void abrirJanelaAdicionarAnuncio() {
        JDialog dialog = new JDialog(this, "Adicionar Anúncio", true);
        dialog.setSize(300, 300); // Aumentando o tamanho da janela
        dialog.setLayout(new GridLayout(6, 2)); // Aumentando para 6 linhas

        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField();
        JLabel lblDescricao = new JLabel("Descrição:");
        JTextArea txtDescricao = new JTextArea();
        JLabel lblImagem = new JLabel("Imagem:");
        JButton btnSelecionarImagem = new JButton("Selecionar Imagem");
        JLabel lblTags = new JLabel("Tags (separadas por vírgula):");
        JTextField txtTags = new JTextField(); // Novo campo para tags
        JButton btnAdicionar = new JButton("Adicionar");

        // Variável para armazenar o caminho da imagem selecionada
        String[] imagemPath = new String[1];

        btnSelecionarImagem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(dialog);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imagemPath[0] = selectedFile.getAbsolutePath(); // Armazena o caminho da imagem selecionada
                JOptionPane.showMessageDialog(dialog, "Imagem selecionada: " + imagemPath[0]);
            }
        });

        btnAdicionar.addActionListener(e -> {
            String titulo = txtTitulo.getText();
            String descricao = txtDescricao.getText();
            List<String> tags = List.of(txtTags.getText().split(",")); // Obtém as tags

            // Adicionar o anúncio usando o controller
            String mensagem = anuncioController.adicionarAnuncio(titulo, descricao, imagemPath[0], tags);
            JOptionPane.showMessageDialog(dialog, mensagem);
            dialog.dispose();
        });

        dialog.add(lblTitulo);
        dialog.add(txtTitulo);
        dialog.add(lblDescricao);
        dialog.add(new JScrollPane(txtDescricao));
        dialog.add(lblImagem);
        dialog.add(btnSelecionarImagem);
        dialog.add(lblTags);
        dialog.add(txtTags); // Adiciona o campo para tags
        dialog.add(btnAdicionar);

        dialog.setVisible(true);
    }
}
