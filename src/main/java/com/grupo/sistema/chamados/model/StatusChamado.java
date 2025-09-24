package com.grupo.sistema.chamados.model;

public class StatusChamado {
    private int idStatus;
    private String status;

    public StatusChamado(int idStatus, String status){
        this.idStatus = idStatus;
        this.status = status;
    }

    public StatusChamado(){
        idStatus = 0;
        status = null;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getStatus() {
        return status;
    }

    public int getIdStatus() {
        return idStatus;
    }
}
