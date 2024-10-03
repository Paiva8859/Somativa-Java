package com.example.Views;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.example.Controllers.UsuarioController;

public class UsuarioView extends JFrame {
    private UsuarioController usuarioController;

    public UsuarioView() {
        usuarioController = new UsuarioController();
        setTitle("Gerenciador de Usuários");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton btnRegistro = new JButton("Registrar Usuário");
        JButton btnLogin = new JButton("Login Usuário");
        JButton btnRegistroAdm = new JButton("Registrar Administrador");
        JButton btnLoginAdm = new JButton("Login Administrador");

        btnRegistro.addActionListener(e -> abrirJanelaRegistro());
        btnLogin.addActionListener(e -> abrirJanelaLogin());
        btnRegistroAdm.addActionListener(e -> abrirJanelaRegistroAdm());
        btnLoginAdm.addActionListener(e -> abrirJanelaLoginAdm());

        add(btnRegistro);
        add(btnLogin);
        add(btnRegistroAdm);
        add(btnLoginAdm);
    }

    private void abrirJanelaRegistro() {
        JDialog dialog = new JDialog(this, "Registro de Usuário", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(4, 2));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();
        JButton btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText();
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());

            String mensagem = usuarioController.adicionarUsuario(nome, email, senha);
            JOptionPane.showMessageDialog(dialog, mensagem);
            dialog.dispose();
        });

        dialog.add(lblNome);
        dialog.add(txtNome);
        dialog.add(lblEmail);
        dialog.add(txtEmail);
        dialog.add(lblSenha);
        dialog.add(txtSenha);
        dialog.add(btnCadastrar);

        dialog.setVisible(true);
    }

    private void abrirJanelaLogin() {
        JDialog dialog = new JDialog(this, "Login de Usuário", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(3, 2));

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();
        JButton btnLogin = new JButton("Login");

        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());

            String mensagem = usuarioController.autenticarUsuario(email, senha);
            JOptionPane.showMessageDialog(dialog, mensagem);
            if (mensagem.equals("Login bem-sucedido!")) {
                dialog.dispose();
            }
        });

        dialog.add(lblEmail);
        dialog.add(txtEmail);
        dialog.add(lblSenha);
        dialog.add(txtSenha);
        dialog.add(btnLogin);

        dialog.setVisible(true);
    }

    private void abrirJanelaRegistroAdm() {
        JDialog dialog = new JDialog(this, "Registro de Administrador", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(4, 2));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();
        JButton btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText();
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());

            String mensagem = usuarioController.adicionarAdm(nome, email, senha);
            JOptionPane.showMessageDialog(dialog, mensagem);
            dialog.dispose();
        });

        dialog.add(lblNome);
        dialog.add(txtNome);
        dialog.add(lblEmail);
        dialog.add(txtEmail);
        dialog.add(lblSenha);
        dialog.add(txtSenha);
        dialog.add(btnCadastrar);

        dialog.setVisible(true);
    }

    private void abrirJanelaLoginAdm() {
        JDialog dialog = new JDialog(this, "Login de Administrador", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(3, 2));

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();
        JButton btnLogin = new JButton("Login");

        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());

            String mensagem = usuarioController.autenticarUsuario(email, senha);
            JOptionPane.showMessageDialog(dialog, mensagem);
            if (mensagem.equals("Login bem-sucedido!")) {
                dialog.dispose();
            }
        });

        dialog.add(lblEmail);
        dialog.add(txtEmail);
        dialog.add(lblSenha);
        dialog.add(txtSenha);
        dialog.add(btnLogin);

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuarioView view = new UsuarioView();
            view.setVisible(true);
        });
    }
}
