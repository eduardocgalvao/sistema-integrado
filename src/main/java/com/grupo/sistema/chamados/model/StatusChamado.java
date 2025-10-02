package com.grupo.sistema.chamados.model;

public class StatusChamado {
    private int idStatus;
    private String status;
    private int idChamado;
    private Chamado chamado;

    public StatusChamado(int idStatus, String status){
        this.idStatus = idStatus;
        this.status = status;
        this.idChamado = idChamado;
    }

    public StatusChamado(){
        idStatus = 0;
        status = null;
        idChamado = 0;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public void setIdChamado(int idChamado) {
        this.idChamado = idChamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }

    public String getStatus() {
        return status;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public int getIdChamado() {
        return idChamado;
    }

    public Chamado getChamado() {
        return chamado;
    }
}
