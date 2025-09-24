package com.grupo.sistema.chamados.model;

public class User  {
    protected int idUser;
    private String nome;
    private String email;
    private String senha;
    private String setor;

    public User(String nome, String email, String setor, int idUser){
        this.nome = nome;
        this.email = email;
        this.setor = setor;
        this.idUser = idUser;
    }

    public User(){
        idUser = 0;
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

    public void setId(int idUser) {
        this.idUser = idUser;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getSetor() {
        return setor;
    }

    public int getIdUser() {
        return idUser;
    }

    @Override
    public String toString(){
        return "Usuario: " + nome + "\n email: " + email + "\n setor: " + setor + "\n ######################### \n";
    }
}
