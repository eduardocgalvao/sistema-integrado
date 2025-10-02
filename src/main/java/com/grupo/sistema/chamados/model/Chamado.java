package com.grupo.sistema.chamados.model;

import java.util.Date;


public class Chamado {
    private int idChamado;
    private String equipamento;
    private String descricao;
    private Date data_abertura;
    private Date data_fechamento;
    private StatusChamado status;
    private User user;
    private String usuarioNome;
    private String statusNome;

    public Chamado(String equipamento, String descricao, Date data_abertura, Date data_fechamento, StatusChamado status, String usuarioNome){
        this.equipamento = equipamento;
        this.descricao = descricao;
        this.data_abertura = data_abertura;
        this.data_fechamento = data_fechamento;
        this.status = status;
        this.usuarioNome = usuarioNome;
    }

    public Chamado(){
        equipamento = null;
        descricao = null;
        data_abertura = null;
        data_fechamento = null;
        status = null;
        usuarioNome = null;

    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData_abertura(Date data_abertura) {
        this.data_abertura = data_abertura;
    }

    public void setData_fechamento(Date data_fechamento) {
        this.data_fechamento = data_fechamento;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public void setStatusNome(String statusNome) {
        this.statusNome = statusNome;
    }

    public void setIdChamado(int idChamado) {
        this.idChamado = idChamado;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getData_abertura() {
        return data_abertura;
    }

    public Date getData_fechamento() {
        return data_fechamento;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public String getStatusNome() {
        return statusNome;
    }

    public int getIdChamado() {
        return idChamado;
    }

    public User getUser() {
        return user;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    //sobrescreve a função para legibilidade da consulta no db
    @Override
    public String toString(){

        return  "Usuario: "  + usuarioNome + " \n Equipamento: " + equipamento + "\n descrição: " + descricao + "\n dataabertura: " + data_abertura + "\n data de fechamento:  " + data_fechamento + " \nStatus: " + statusNome + "\n#####################\n";
    }
}
