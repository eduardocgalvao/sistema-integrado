package com.grupo.sistema.chamados.dao;

import com.grupo.sistema.chamados.model.Chamado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import com.grupo.sistema.chamados.db.ConnectionFactory;
import com.grupo.sistema.chamados.model.StatusChamado;
import com.grupo.sistema.chamados.model.User;

public class ChamadoDAO {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public  void abrirChamado(Chamado chamado){
        // Comando MySQL para inserir dados no banco
        String sql = "INSERT INTO chamado (equipamento, descricao, data_abertura, data_fechamento, idUsuario, idStatus ) VALUES (?, ?, ?, ?, ?, ?)";

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
                ps.setInt(6, chamado.getStatus().getIdStatus());

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

    public List<Chamado> searchChamado() {
        List<Chamado> chamados = new ArrayList<>();
        String sql = "SELECT " +
                "     u.nome AS usuarioNome, " +
                "     c.equipamento, " +
                "     c.descricao,  " +
                "     c.data_abertura, " +
                "     c.data_fechamento," +
                "     s.nome AS statusNome " +
                "  FROM chamado c " +
                "JOIN usuario u ON c.idUsuario = u.idUsuario " +
                "JOIN statuschamado s ON c.idStatus = s.idStatus";


        try {
            //garante a conexão o db
            ps = ConnectionFactory.getConexao().prepareStatement(sql);
            //executa a consulta no db
            rs = ps.executeQuery();

            //percorre as linhas do db
            while (rs.next()) {
                Chamado ch = new Chamado();
                User u = new User();
                StatusChamado s = new StatusChamado();

                //inner join entre chamado e usuario
                ch.setUsuarioNome(rs.getString("usuarioNome"));
                ch.setEquipamento(rs.getString("equipamento"));
                ch.setDescricao(rs.getString("descricao"));
                ch.setData_abertura(rs.getDate("data_abertura"));
                ch.setData_fechamento(rs.getDate("data_fechamento"));
                ch.setStatusNome(rs.getString("statusNome"));
                chamados.add(ch);


            }
            //lista os chamados
            System.out.println(chamados.toString());
            //fecha a execução
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[DEBUG] ChamadoDAO.searchChamado() encontrou " + chamados.size() + " chamados.");
        for (Chamado c : chamados) {
            System.out.println("[DEBUG] Chamado: " + c.getEquipamento());

            return chamados;
        }
        return chamados;
    }



    //Edição dos dados do chamado (admin)
    public void editChamado(Chamado chamado){
        String sql = "UPDATE chamado SET equipamento = ?, descricao = ?,  where idUsuario = ?";

        try {
            ps = ConnectionFactory.getConexao().prepareStatement(sql);
            ps.setString(1, chamado.getEquipamento());
            ps.setString(2, chamado.getDescricao());
            ps.setInt(3, chamado.getIdChamado());
            ps.executeUpdate();
            ps.close();
            System.out.println("Usuário atualizado com sucesso");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //Exclusão de chamado (admin)
    public void deleteChamado(Chamado chamado){
        String sql = "DELETE FROM chamado WHERE idChamado = ?";

        try {
            ps = ConnectionFactory.getConexao().prepareStatement(sql);
            ps.setInt(1, chamado.getIdChamado());
            ps.execute();


            if (ps.execute() == false){
                System.out.println("Chamado excluido com sucesso.");
            }else{
                System.out.println("Chamado não encontrado");
            }

            ps.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
