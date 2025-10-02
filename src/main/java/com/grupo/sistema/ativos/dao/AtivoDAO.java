package com.grupo.sistema.ativos.dao;

import com.grupo.sistema.ativos.model.Ativo;
import com.grupo.sistema.config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtivoDAO {

    public void salvar(Ativo a) throws SQLException {
        String sql = """
            INSERT INTO ativos (tipo, marca, modelo, num_serie, setor, responsavel_id,
                                status, valor, garantia_fim, descricao)
            VALUES (?,?,?,?,?,?,?,?,?,?)
        """;
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getTipo());
            ps.setString(2, a.getMarca());
            ps.setString(3, a.getModelo());
            ps.setString(4, a.getNumSerie());
            ps.setString(5, a.getSetor());
            if (a.getResponsavelId() == null) ps.setNull(6, Types.INTEGER);
            else ps.setInt(6, a.getResponsavelId());
            ps.setString(7, a.getStatus());
            if (a.getValor() != null) ps.setBigDecimal(8, a.getValor());
            else ps.setNull(8, Types.DECIMAL);
            if (a.getGarantiaFim() != null) ps.setDate(9, Date.valueOf(a.getGarantiaFim()));
            else ps.setNull(9, Types.DATE);
            ps.setString(10, a.getDescricao());
            ps.executeUpdate();
        }
    }

    public List<Ativo> listarTodos() throws SQLException {
        String sql = """
            SELECT a.*, u.nome as responsavel_nome
            FROM ativos a
            LEFT JOIN usuarios u ON a.responsavel_id = u.id
        """;
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            List<Ativo> lista = new ArrayList<>();
            while (rs.next()) lista.add(mapRow(rs));
            return lista;
        }
    }

    public List<Ativo> listarPorResponsavel(int respId) throws SQLException {
        String sql = """
            SELECT a.*, u.nome as responsavel_nome
            FROM ativos a
            LEFT JOIN usuarios u ON a.responsavel_id = u.id
            WHERE a.responsavel_id = ?
        """;
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, respId);

            ResultSet rs = ps.executeQuery();
            List<Ativo> lista = new ArrayList<>();
            while (rs.next()) lista.add(mapRow(rs));
            return lista;
        }
    }

    private Ativo mapRow(ResultSet rs) throws SQLException {
        Ativo a = new Ativo();
        a.setId(rs.getInt("id"));
        a.setTipo(rs.getString("tipo"));
        a.setMarca(rs.getString("marca"));
        a.setModelo(rs.getString("modelo"));
        a.setNumSerie(rs.getString("num_serie"));
        a.setSetor(rs.getString("setor"));
        int r = rs.getInt("responsavel_id");
        if (!rs.wasNull()) a.setResponsavelId(r);
        a.setResponsavelNome(rs.getString("responsavel_nome"));
        a.setStatus(rs.getString("status"));
        a.setValor(rs.getBigDecimal("valor"));
        Date g = rs.getDate("garantia_fim");
        if (g != null) a.setGarantiaFim(g.toLocalDate());
        a.setDescricao(rs.getString("descricao"));
        Timestamp c = rs.getTimestamp("criado_em");
        if (c != null) a.setCriadoEm(c.toLocalDateTime());
        Timestamp u = rs.getTimestamp("atualizado_em");
        if (u != null) a.setAtualizadoEm(u.toLocalDateTime());
        return a;
    }

    public void atualizar(Ativo a) throws SQLException {
        String sql = """
        UPDATE ativos SET tipo=?, marca=?, modelo=?, num_serie=?, setor=?,
        responsavel_id=?, status=?, valor=?, garantia_fim=?, descricao=?
        WHERE id=?
        """;
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getTipo());
            ps.setString(2, a.getMarca());
            ps.setString(3, a.getModelo());
            ps.setString(4, a.getNumSerie());
            ps.setString(5, a.getSetor());
            if (a.getResponsavelId() == null) ps.setNull(6, Types.INTEGER);
            else ps.setInt(6, a.getResponsavelId());
            ps.setString(7, a.getStatus());
            if (a.getValor() != null) ps.setBigDecimal(8, a.getValor());
            else ps.setNull(8, Types.DECIMAL);
            if (a.getGarantiaFim() != null) ps.setDate(9, Date.valueOf(a.getGarantiaFim()));
            else ps.setNull(9, Types.DATE);
            ps.setString(10, a.getDescricao());
            ps.setInt(11, a.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM ativos WHERE id=?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

}
