package core.dao;

import base.connection.ConexaoMySQL;
import base.exception.AcomodacaoException;
import base.exception.PessoaException;
import core.model.Acomodacao;
import core.model.Funcionario;
import core.model.Pessoa;

import java.sql.*;
import java.util.ArrayList;

public class AcomodacaoDAO implements DAO<Acomodacao>{

    public ArrayList<Acomodacao> selecionar() throws AcomodacaoException {
        try {
            String sql = "SELECT id, nome, valor_diaria, limite_hospedes, descricao, id_funcionario_responsavel FROM ACOMODACAO";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Acomodacao> acomodacoes = new ArrayList<>();
            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultado.getLong("id_funcionario_responsavel"));

                Acomodacao acomodacao = new Acomodacao(
                        resultado.getLong("id"),
                        resultado.getString("nome"),
                        resultado.getDouble("valor_diaria"),
                        resultado.getInt("limite_hospedes"),
                        resultado.getString("descricao"),
                        funcionario
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
            String sql = "INSERT INTO acomodacao(nome , valor_diaria, limite_hospedes, descricao, id_funcionario_responsavel) " +
                    "VALUES(?, ?, ?, ?, ?)";

            Funcionario funcionario = new Funcionario();
            funcionario.setId(Long.valueOf("id_funcionario_responsavel"));

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, acomodacao.getNome());
            preparacao.setDouble(2, acomodacao.getValorDiaria());
            preparacao.setInt(3, acomodacao.getLimiteHospedes());
            preparacao.setString(4, acomodacao.getDescricao());
            preparacao.setLong(5, acomodacao.getFuncionarioResponsavel().getId());
            return preparacao.executeUpdate() > 0;

        } catch (SQLException e) {
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
        } catch (SQLException e) {
            throw new AcomodacaoException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean deletar(Long id) throws AcomodacaoException {
        try {
            String sql = "DELETE FROM acomodacao WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            return preparacao.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new AcomodacaoException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Acomodacao selecionarPorId(Long id) throws AcomodacaoException {
        try {
            String sql = "SELECT id, nome, valor_diaria, limite_hospedes, descricao, id_funcionario_responsavel FROM acomodacao WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultado.getLong("id_funcionario_responsavel"));

                return new Acomodacao(
                        resultado.getLong("id"),
                        resultado.getString("nome"),
                        resultado.getDouble("valor_diaria"),
                        resultado.getInt("limite_hospedes"),
                        resultado.getString("descricao"),
                        funcionario
                );
            } else{
                return null;
            }
        } catch (SQLException e) {
            throw new AcomodacaoException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Acomodacao selecionarUltima() throws AcomodacaoException{
        try {
            String sql="SELECT id, nome, valor_diaria, limite_hospedes, descricao, id_funcionario_responsavel FROM acomodacao ORDER BY id DESC LIMIT 1";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            if (resultado.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultado.getLong("id_funcionario_responsavel"));

                return new Acomodacao(
                        resultado.getLong("id"),
                        resultado.getString("nome"),
                        resultado.getDouble("valor_diaria"),
                        resultado.getInt("limite_hospedes"),
                        resultado.getString("descricao"),
                        funcionario
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new AcomodacaoException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Acomodacao selecionarPorNome(String nome) throws AcomodacaoException{
        try{
            String sql="SELECT id, nome, valor_diaria, limite_hospedes, descricao, id_funcionario_responsavel FROM acomodacao WHERE nome=?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, nome);
            ResultSet resultado = preparacao.executeQuery();

            if (resultado.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultado.getLong("id_funcionario_responsavel"));

                return new Acomodacao(
                        resultado.getLong("id"),
                        resultado.getString("nome"),
                        resultado.getDouble("valor_diaria"),
                        resultado.getInt("limite_hospedes"),
                        resultado.getString("descricao"),
                        funcionario
                );
            } else {
                return null;
            }
        } catch (SQLException e){
            throw new AcomodacaoException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }
}
