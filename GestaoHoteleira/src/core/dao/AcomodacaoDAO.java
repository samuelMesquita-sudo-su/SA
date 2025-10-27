package core.dao;

import base.connection.ConexaoMySQL;
import base.exception.AcomodacaoException;
import core.model.Acomodacao;
import core.model.Funcionario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AcomodacaoDAO implements DAO<Acomodacao> {

    // Métodos de interação com banco de dados

    public ArrayList<Acomodacao> selecionar() throws AcomodacaoException {
        try {
            String sql = "SELECT " +
                            "a.id, " +
                            "a.nome, " +
                            "a.valor_diaria, " +
                            "a.limite_hospedes, " +
                            "a.descricao, " +
                            "a.id_funcionario_responsavel, " +
                            "f.cargo, " +
                            "f.salario, " +
                            "f.id_pessoa, " +
                            "p.nome_completo, " +
                            "p.data_nascimento, " +
                            "p.documento, " +
                            "p.pais, " +
                            "p.estado, " +
                            "p.cidade " +
                        "FROM acomodacao a " +
                        "JOIN funcionario f ON f.id = a.id_funcionario_responsavel " +
                        "JOIN pessoa p ON p.id = f.id_pessoa";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Acomodacao> acomodacoes = new ArrayList<>();
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
                    resultado.getLong("id"),
                    resultado.getString("nome"),
                    resultado.getDouble("valor_diaria"),
                    resultado.getInt("limite_hospedes"),
                    resultado.getString("descricao"),
                    funcionarioResponsavel
                );
                acomodacoes.add(acomodacao);
            }
            return acomodacoes;

        } catch (SQLException e) {
            throw new AcomodacaoException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean inserir(Acomodacao acomodacao) throws AcomodacaoException {
        try {
            String sql = "INSERT INTO acomodacao " +
                        "(nome, valor_diaria, limite_hospedes, descricao, id_funcionario_responsavel) " +
                        "VALUES (?,?,?,?,?)";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, acomodacao.getNome());
            preparacao.setDouble(2, acomodacao.getValorDiaria());
            preparacao.setInt(3, acomodacao.getLimiteHospedes());
            preparacao.setString(4, acomodacao.getDescricao());
            preparacao.setLong(5, acomodacao.getFuncionarioResponsavel().getId());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw new AcomodacaoException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean atualizar(Acomodacao acomodacao) throws AcomodacaoException {
        try {
            String sql = "UPDATE acomodacao " +
                        "SET " +
                            "nome = ?, " +
                            "valor_diaria = ? , " +
                            "limite_hospedes = ?, " +
                            "descricao = ?, " +
                            "id_funcionario_responsavel = ? " +
                        "WHERE id = ?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, acomodacao.getNome());
            preparacao.setDouble(2, acomodacao.getValorDiaria());
            preparacao.setInt(3, acomodacao.getLimiteHospedes());
            preparacao.setString(4, acomodacao.getDescricao());
            preparacao.setLong(5, acomodacao.getFuncionarioResponsavel().getId());
            preparacao.setLong(6, acomodacao.getId());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new AcomodacaoException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }
    public Boolean deletar(Long id) throws AcomodacaoException {
        try {
            String sql = "DELETE FROM acomodacao WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new AcomodacaoException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Acomodacao selecionarPorId(Long id) throws AcomodacaoException {
        try {
            String sql = "SELECT " +
                            "a.id, " +
                            "a.nome, " +
                            "a.valor_diaria, " +
                            "a.limite_hospedes, " +
                            "a.descricao, " +
                            "a.id_funcionario_responsavel, " +
                            "f.cargo, " +
                            "f.salario, " +
                            "f.id_pessoa, " +
                            "p.nome_completo, " +
                            "p.data_nascimento, " +
                            "p.documento, " +
                            "p.pais, " +
                            "p.estado, " +
                            "p.cidade " +
                        "FROM acomodacao a " +
                        "JOIN funcionario f ON f.id = a.id_funcionario_responsavel " +
                        "JOIN pessoa p ON p.id = f.id_pessoa " +
                        "WHERE a.id = ?";
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

                return new Acomodacao(
                    resultado.getLong("id"),
                    resultado.getString("nome"),
                    resultado.getDouble("valor_diaria"),
                    resultado.getInt("limite_hospedes"),
                    resultado.getString("descricao"),
                    funcionarioResponsavel
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new AcomodacaoException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

}
