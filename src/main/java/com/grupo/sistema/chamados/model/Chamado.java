package com.grupo.sistema.chamados.model;

import java.util.Date;

public class Chamado {
    private long idChamado;
    private String equipamento;
    private String descricao;
    private Date data_abertura;
    private Date data_fechamento;
    private StatusChamado status;

    public Chamado(String equipamento, String descricao, Date data_abertura, Date data_fechamento, StatusChamado status){
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

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public void setIdChamado(long idChamado) {
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
}
