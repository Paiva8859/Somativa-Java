package com.example;

import javax.swing.SwingUtilities;

import com.example.Views.AdminView;
import com.example.Views.AnunciosView;

public class Main {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> {
        //     HomeView usuarioView = new HomeView();
        //     usuarioView.setVisible(true);
        // });

        SwingUtilities.invokeLater(() -> {
            AdminView adminView = new AdminView();
            adminView.setVisible(true);
        });

        SwingUtilities.invokeLater(() -> {
            String usuario = "UsuarioTeste"; 
            AnunciosView anunciosView = new AnunciosView(usuario);
            anunciosView.setVisible(true);
        });
    }
}