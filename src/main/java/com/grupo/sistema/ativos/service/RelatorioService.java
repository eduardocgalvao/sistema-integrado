package com.grupo.sistema.ativos.service;

import com.grupo.sistema.ativos.model.Ativo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioService {

    // Exporta CSV agrupando por setor
    public static void exportarCSVPorSetor(List<Ativo> ativos, String caminhoArquivo) throws IOException {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            // Cabeçalho
            writer.append("Setor;ID;Tipo;Marca;Modelo;Nº Série;Responsável;Status;Valor;Garantia;Descrição\n");

            // Agrupar por setor
            Map<String, List<Ativo>> porSetor = ativos.stream()
                    .collect(Collectors.groupingBy(a -> a.getSetor() != null ? a.getSetor() : "Não definido"));

            for (var entry : porSetor.entrySet()) {
                String setor = entry.getKey();
                for (Ativo a : entry.getValue()) {
                    writer.append(setor).append(";");
                    writer.append(String.valueOf(a.getId())).append(";");
                    writer.append(a.getTipo()).append(";");
                    writer.append(a.getMarca() != null ? a.getMarca() : "").append(";");
                    writer.append(a.getModelo() != null ? a.getModelo() : "").append(";");
                    writer.append(a.getNumSerie() != null ? a.getNumSerie() : "").append(";");
                    writer.append(a.getResponsavelNome() != null ? a.getResponsavelNome() : "").append(";");
                    writer.append(a.getStatus()).append(";");
                    writer.append(a.getValor() != null ? a.getValor().toString() : "").append(";");
                    writer.append(a.getGarantiaFim() != null ? a.getGarantiaFim().toString() : "").append(";");
                    writer.append(a.getDescricao() != null ? a.getDescricao().replace(";", ",") : "");
                    writer.append("\n");
                }
            }
        }
    }

    // Exporta TXT agrupando por responsável
    public static void exportarTXTPorResponsavel(List<Ativo> ativos, String caminhoArquivo) throws IOException {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            Map<String, List<Ativo>> porResponsavel = ativos.stream()
                    .collect(Collectors.groupingBy(a -> a.getResponsavelNome() != null ? a.getResponsavelNome() : "Sem responsável"));

            for (var entry : porResponsavel.entrySet()) {
                writer.write("===== Responsável: " + entry.getKey() + " =====\n\n");
                for (Ativo a : entry.getValue()) {
                    writer.write("ID: " + a.getId() + "\n");
                    writer.write("Tipo: " + a.getTipo() + "\n");
                    writer.write("Marca: " + a.getMarca() + "\n");
                    writer.write("Modelo: " + a.getModelo() + "\n");
                    writer.write("Nº Série: " + a.getNumSerie() + "\n");
                    writer.write("Setor: " + a.getSetor() + "\n");
                    writer.write("Status: " + a.getStatus() + "\n");
                    writer.write("Valor: " + (a.getValor() != null ? a.getValor() : "N/A") + "\n");
                    writer.write("Garantia: " + (a.getGarantiaFim() != null ? a.getGarantiaFim() : "N/A") + "\n");
                    writer.write("Descrição: " + a.getDescricao() + "\n");
                    writer.write("--------------------------------------\n");
                }
                writer.write("\n\n");
            }
        }
    }
}
