package com.example;

import javax.swing.SwingUtilities;

import com.example.Views.UsuarioView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuarioView view = new UsuarioView();
            view.setVisible(true);
        });
    }
}