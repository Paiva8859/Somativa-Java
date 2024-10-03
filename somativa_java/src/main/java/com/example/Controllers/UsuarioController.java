package com.example.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.Models.Usuario;

public class UsuarioController {
    private static final String FILE_NAME = "somativa_java/Data/usuarios.txt";
    private List<Usuario> usuarios;

    // Construtor
    public UsuarioController() {
        this.usuarios = new ArrayList<>();
        carregarUsuarios();
    }

    // Método para adicionar um usuário
    public String adicionarUsuario(String nome, String email, String senha) {
        if (isEmailExistente(email)) {
            return "Email já cadastrado!";
        }
        Usuario usuario = new Usuario(nome, email, senha);
        usuarios.add(usuario);
        salvarUsuarios();
        return "Usuário cadastrado com sucesso!";
    }

    // Método para verificar se o email já existe
    private boolean isEmailExistente(String email) {
        return usuarios.stream().anyMatch(usuario -> usuario.getEmail().equals(email));
    }

    // Método para autenticar um usuário
    public String autenticarUsuario(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha) && !usuario.isAdm()) {
                return "Login bem-sucedido!";
            }
        }
        return "Email ou senha inválidos!";
    }

    // Método para adicionar um admin
    public String adicionarAdm(String nome, String email, String senha) {
        if (isEmailExistente(email)) {
            return "Email já cadastrado!";
        }
        Usuario adm = new Usuario(nome, email, senha);
        adm.setAdm(true);
        usuarios.add(adm);
        salvarUsuarios();
        return "Admin cadastrado com sucesso!";
    }

    // Método para autenticar um admin
    public String autenticarAdmin(String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha) && usuario.isAdm()) {
                return "Login bem-sucedido!";
            }
        }
        return "Email ou senha inválidos!";
    }

    // Método para salvar os usuários no arquivo
    private void salvarUsuarios() {
        File file = new File(FILE_NAME);
        try {
            // Se o arquivo não existir, crie um novo
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Cria diretórios se não existirem
                file.createNewFile(); // Cria o arquivo
            }
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Usuario usuario : usuarios) {
                    writer.write(usuario.getNome() + "," + usuario.getEmail() + "," + usuario.getSenha() + "," + usuario.isAdm());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    // Método para carregar usuários do arquivo
    private void carregarUsuarios() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // Se o arquivo não existir, não há usuários para carregar
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    Usuario usuario = new Usuario(dados[0], dados[1], dados[2]);
                    usuario.setAdm(Boolean.parseBoolean(dados[3]));
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
