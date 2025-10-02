package com.grupo.sistema.ativos.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ativo {
    private int id;
    private String tipo;
    private String marca;
    private String modelo;
    private String numSerie;
    private String setor;
    private Integer responsavelId;
    private String status;
    private BigDecimal valor;
    private LocalDate garantiaFim;
    private String descricao;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    // Getters e Setters

    private String responsavelNome;

    public String getResponsavelNome()
    { return responsavelNome; }
    public void setResponsavelNome(String responsavelNome)
    { this.responsavelNome = responsavelNome; }

    public int getId()
    { return id; }

    public void setId(int id)
    { this.id = id; }

    public String getTipo()
    { return tipo; }

    public void setTipo(String tipo)
    { this.tipo = tipo; }

    public String getMarca()
    { return marca; }

    public void setMarca(String marca)
    { this.marca = marca; }

    public String getModelo()
    { return modelo; }

    public void setModelo(String modelo)
    { this.modelo = modelo; }

    public String getNumSerie()
    { return numSerie; }

    public void setNumSerie(String numSerie)
    { this.numSerie = numSerie; }

    public String getSetor()
    { return setor; }

    public void setSetor(String setor)
    { this.setor = setor; }

    public Integer getResponsavelId()
    { return responsavelId; }

    public void setResponsavelId(Integer responsavelId)
    { this.responsavelId = responsavelId; }

    public String getStatus()
    { return status; }

    public void setStatus(String status)
    { this.status = status; }

    public BigDecimal getValor()
    { return valor; }

    public void setValor(BigDecimal valor)
    { this.valor = valor; }

    public LocalDate getGarantiaFim()
    { return garantiaFim; }

    public void setGarantiaFim(LocalDate garantiaFim)
    { this.garantiaFim = garantiaFim; }

    public String getDescricao()
    { return descricao; }

    public void setDescricao(String descricao)
    { this.descricao = descricao; }

    public LocalDateTime getCriadoEm()
    { return criadoEm; }

    public void setCriadoEm(LocalDateTime criadoEm)
    { this.criadoEm = criadoEm; }

    public LocalDateTime getAtualizadoEm()
    { return atualizadoEm; }

    public void setAtualizadoEm(LocalDateTime atualizadoEm)
    { this.atualizadoEm = atualizadoEm; }
}
