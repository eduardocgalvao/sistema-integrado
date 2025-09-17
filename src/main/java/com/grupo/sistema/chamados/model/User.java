package com.grupo.sistema.chamados.model;

import com.grupo.sistema.chamados.service.CadastroUsuario;
import java.util.Scanner;

public class User implements CadastroUsuario {
    private String nome;
    private String email;
    private String setor;

    public User(String nome, String email, String setor){
        this.nome = nome;
        this.email = email;
        this.setor = setor;
    }

    public User(){
        nome = "SEM NOME";
        email = "NULL";
        setor = "NULL";
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSetor() {
        return setor;
    }

    @Override
    public void cadastro(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        setNome(scanner.nextLine());

        System.out.print("Email: ");
        setEmail(scanner.nextLine());

        System.out.print("Setor: ");
        setSetor(scanner.nextLine());
    }
}
