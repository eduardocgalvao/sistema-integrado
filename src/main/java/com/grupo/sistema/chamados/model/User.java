package com.grupo.sistema.chamados.model;

public class User  {
    protected long idUser;
    private String nome;
    private String email;
    private String setor;

    public User(String nome, String email, String setor, long idUser){
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

    public void setId(long idUser) {
        this.idUser = idUser;
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

    public long getIdUser() {
        return idUser;
    }
}
