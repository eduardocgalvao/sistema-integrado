package com.grupo.sistema.chamados.model;

import java.util.Date;


public class Chamado {
    private int idChamado;
    private String equipamento;
    private String descricao;
    private Date data_abertura;
    private Date data_fechamento;
    private String status;
    private User user;

    public Chamado(String equipamento, String descricao, Date data_abertura, Date data_fechamento, String status){
        this.equipamento = equipamento;
        this.descricao = descricao;
        this.data_abertura = data_abertura;
        this.data_fechamento = data_fechamento;
        this.status = status;
    }

    public Chamado(){
        equipamento = null;
        descricao = null;
        data_abertura = null;
        data_fechamento = null;
        status = null;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getStatus() {
        return status;
    }

    public long getIdChamado() {
        return idChamado;
    }

    public User getUser() {
        return user;
    }
}
