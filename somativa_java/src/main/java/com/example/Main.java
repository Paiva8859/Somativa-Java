package com.example;

import javax.swing.SwingUtilities;

import com.example.Views.AdminView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminView adminView = new AdminView();
            adminView.setVisible(true);
        });
    }
}