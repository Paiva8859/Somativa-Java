package com.example;

import javax.swing.SwingUtilities;

import com.example.Views.HomeView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeView usuarioView = new HomeView();
            usuarioView.setVisible(true);
        });
    }
}