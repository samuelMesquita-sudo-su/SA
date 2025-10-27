package core.dao;

import base.connection.ConexaoMySQL;
import base.exception.PacoteException;
import core.model.Acomodacao;
import core.model.Funcionario;
import core.model.Pacote;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PacoteDAO implements DAO<Pacote> {

    // Métodos de interação com banco de dados

    public ArrayList<Pacote> selecionar() throws PacoteException {
        try {
            String sql = "SELECT " +
                            "p.id, " +
                            "p.nome, " +
                            "p.qtd_diarias, " +
                            "p.valor_total, " +
                            "p.id_acomodacao, " +
                            "a.nome as nome_acomodacao, " +
                            "a.valor_diaria, " +
                            "a.limite_hospedes, " +
                            "a.descricao, " +
                            "a.id_funcionario_responsavel, " +
                            "f.cargo, " +
                            "f.salario, " +
                            "f.id_pessoa, " +
                            "pe.nome_completo, " +
                            "pe.data_nascimento, " +
                            "pe.documento, " +
                            "pe.pais, " +
                            "pe.estado, " +
                            "pe.cidade " +
                        "FROM pacote p " +
                        "JOIN acomodacao a ON a.id = p.id_acomodacao " +
                        "JOIN funcionario f ON f.id = a.id_funcionario_responsavel " +
                        "JOIN pessoa pe ON pe.id = f.id_pessoa";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Pacote> pacotes = new ArrayList<>();
            while(resultado.next()) {
                Funcionario funcionarioResponsavel = new Funcionario(
                    resultado.getLong("id_pessoa"),
                    resultado.getString("nome_completo"),
                    resultado.getDate("data_nascimento").toLocalDate(),
                    resultado.getString("documento"),
                    resultado.getString("pais"),
                    resultado.getString("estado"),
                    resultado.getString("cidade"),
                    resultado.getLong("id_funcionario_responsavel"),
                    resultado.getString("cargo"),
                    resultado.getDouble("salario")
                );
                Acomodacao acomodacao = new Acomodacao(
                        resultado.getLong("id_acomodacao"),
                        resultado.getString("nome_acomodacao"),
                        resultado.getDouble("valor_diaria"),
                        resultado.getInt("limite_hospedes"),
                        resultado.getString("descricao"),
                        funcionarioResponsavel
                );
                Pacote pacote = new Pacote(
                    resultado.getLong("id"),
                    resultado.getString("nome"),
                    acomodacao,
                    resultado.getInt("qtd_diarias"),
                    resultado.getDouble("valor_total")
                );
                pacotes.add(pacote);
            }
            return pacotes;

        } catch (SQLException e) {
            throw new PacoteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean inserir(Pacote pacote) throws PacoteException {
        try {
            String sql = "INSERT INTO pacote " +
                        "(nome, id_acomodacao, qtd_diarias, valor_total) " +
                        "VALUES (?,?,?,?)";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, pacote.getNome());
            preparacao.setLong(2, pacote.getAcomodacao().getId());
            preparacao.setInt(3, pacote.getQtdDiarias());
            preparacao.setDouble(4, pacote.getValorTotal());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PacoteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean atualizar(Pacote pacote) throws PacoteException {
        try {
            String sql = "UPDATE pacote " +
                        "SET " +
                            "nome = ? , " +
                            "id_acomodacao = ?, " +
                            "qtd_diarias = ?, " +
                            "valor_total = ? " +
                        "WHERE id = ?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, pacote.getNome());
            preparacao.setLong(2, pacote.getAcomodacao().getId());
            preparacao.setInt(3, pacote.getQtdDiarias());
            preparacao.setDouble(4, pacote.getValorTotal());
            preparacao.setLong(5, pacote.getId());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PacoteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }
    public Boolean deletar(Long id) throws PacoteException {
        try {
            String sql = "DELETE FROM pacote WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new PacoteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Pacote selecionarPorId(Long id) throws PacoteException {
        try {
            String sql = "SELECT " +
                            "p.id, " +
                            "p.nome, " +
                            "p.qtd_diarias, " +
                            "p.valor_total, " +
                            "p.id_acomodacao, " +
                            "a.nome as nome_acomodacao, " +
                            "a.valor_diaria, " +
                            "a.limite_hospedes, " +
                            "a.descricao, " +
                            "a.id_funcionario_responsavel, " +
                            "f.cargo, " +
                            "f.salario, " +
                            "f.id_pessoa, " +
                            "pe.nome_completo, " +
                            "pe.data_nascimento, " +
                            "pe.documento, " +
                            "pe.pais, " +
                            "pe.estado, " +
                            "pe.cidade " +
                        "FROM pacote p " +
                        "JOIN acomodacao a ON a.id = p.id_acomodacao " +
                        "JOIN funcionario f ON f.id = a.id_funcionario_responsavel " +
                        "JOIN pessoa pe ON pe.id = f.id_pessoa " +
                        "WHERE p.id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if(resultado.next()) {
                Funcionario funcionarioResponsavel = new Funcionario(
                        resultado.getLong("id_pessoa"),
                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade"),
                        resultado.getLong("id_funcionario_responsavel"),
                        resultado.getString("cargo"),
                        resultado.getDouble("salario")
                );
                Acomodacao acomodacao = new Acomodacao(
                        resultado.getLong("id_acomodacao"),
                        resultado.getString("nome_acomodacao"),
                        resultado.getDouble("valor_diaria"),
                        resultado.getInt("limite_hospedes"),
                        resultado.getString("descricao"),
                        funcionarioResponsavel
                );
                return new Pacote(
                        resultado.getLong("id"),
                        resultado.getString("nome"),
                        acomodacao,
                        resultado.getInt("qtd_diarias"),
                        resultado.getDouble("valor_total")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new PacoteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

}
