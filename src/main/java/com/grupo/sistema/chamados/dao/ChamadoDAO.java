package com.grupo.sistema.chamados.dao;

import com.grupo.sistema.chamados.model.Chamado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import com.grupo.sistema.chamados.db.ConnectionFactory;
import com.grupo.sistema.chamados.model.User;

public class ChamadoDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public void abrirChamado(Chamado chamado){
        // Comando MySQL para inserir dados no banco
        String sql = "INSERT INTO chamado (equipamento, descricao, data_abertura, data_fechamento, idUsuario ) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

            try {
                //Prepara o banco de dados
                ps = ConnectionFactory.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                //Recebe os valores
                ps.setString(1, chamado.getEquipamento());
                ps.setString(2, chamado.getDescricao());
                //Se a data não for nula ele armazena no banco de dados a data atual
                if(chamado.getData_abertura() != null){
                    ps.setDate(3, new java.sql.Date(chamado.getData_abertura().getTime()));
                } else{
                    ps.setNull(3, java.sql.Types.DATE);
                }

                if (chamado.getData_fechamento() != null){
                    ps.setDate(4, new java.sql.Date(chamado.getData_fechamento().getTime()));
                }else{
                    ps.setNull(4, java.sql.Types.DATE);
                }
                ps.setLong(5, chamado.getUser().getIdUser());

                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int idGerado = rs.getInt(1);
                chamado.getIdChamado();


                ps.close();

            }  catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
    }

    public List<Chamado> SearchChamado(){
        List<Chamado> chamados = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try {
            ps = ConnectionFactory.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Chamado ch = new Chamado();

                ch.setIdChamado(rs.getInt("idChamado"));
                ch.setEquipamento(rs.getString("equipamento"));
                ch.setDescricao(rs.getString("descricao"));
                ch.setData_abertura(rs.getDate("data_abertura"));
                ch.setData_fechamento(rs.getDate("data_abertura"));
                chamados.add(ch);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return chamados;
    }
}
