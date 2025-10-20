package core.dao;

import base.connection.ConexaoMySQL;
import base.exception.PessoaException;
import core.model.Pessoa;

import java.sql.*;
import java.util.ArrayList;

public class PessoaDAO implements DAO<Pessoa> {

    // Métodos de interação com banco de dados

    public ArrayList<Pessoa> selecionar() throws PessoaException {
        try {
            String sql = "SELECT " +
                            "id, " +
                            "nome_completo, " +
                            "data_nascimento, " +
                            "documento, " +
                            "pais, " +
                            "estado, " +
                            "cidade " +
                        "FROM pessoa";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Pessoa> pessoas = new ArrayList<>();
            while(resultado.next()) {
                Pessoa pessoa = new Pessoa(
                    resultado.getLong("id"),
                    resultado.getString("nome_completo"),
                    resultado.getDate("data_nascimento").toLocalDate(),
                    resultado.getString("documento"),
                    resultado.getString("pais"),
                    resultado.getString("estado"),
                    resultado.getString("cidade")
                );
                pessoas.add(pessoa);
            }
            return pessoas;

        } catch (SQLException e) {
            throw new PessoaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean inserir(Pessoa pessoa) throws PessoaException {
        try {
            String sql = "INSERT INTO pessoa " +
                        "(nome_completo, data_nascimento, documento, pais, estado, cidade) " +
                        "VALUES (?,?,?,?,?,?)";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, pessoa.getNomeCompleto());
            preparacao.setDate(2, Date.valueOf(pessoa.getDataNascimento()));
            preparacao.setString(3, pessoa.getDocumento());
            preparacao.setString(4, pessoa.getPais());
            preparacao.setString(5, pessoa.getEstado());
            preparacao.setString(6, pessoa.getCidade());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new PessoaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean atualizar(Pessoa pessoa) throws PessoaException {
        try {
            String sql = "UPDATE pessoa " +
                        "SET " +
                            "nome_completo = ?, " +
                            "data_nascimento = ? , " +
                            "documento = ?, " +
                            "pais = ?, " +
                            "estado = ?, " +
                            "cidade = ? " +
                        "WHERE id = ?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, pessoa.getNomeCompleto());
            preparacao.setDate(2, Date.valueOf(pessoa.getDataNascimento()));
            preparacao.setString(3, pessoa.getDocumento());
            preparacao.setString(4, pessoa.getPais());
            preparacao.setString(5, pessoa.getEstado());
            preparacao.setString(6, pessoa.getCidade());
            preparacao.setLong(7, pessoa.getId());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new PessoaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }
    public Boolean deletar(Long id) throws PessoaException {
        try {
            String sql = "DELETE FROM pessoa WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new PessoaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Pessoa selecionarPorId(Long id) throws PessoaException {
        try {
            String sql = "SELECT " +
                            "id, " +
                            "nome_completo, " +
                            "data_nascimento, " +
                            "documento, " +
                            "pais, " +
                            "estado, " +
                            "cidade " +
                        "FROM pessoa " +
                        "WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if(resultado.next()) {
                return new Pessoa(
                    resultado.getLong("id"),
                    resultado.getString("nome_completo"),
                    resultado.getDate("data_nascimento").toLocalDate(),
                    resultado.getString("documento"),
                    resultado.getString("pais"),
                    resultado.getString("estado"),
                    resultado.getString("cidade")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new PessoaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Pessoa selecionarUltima() throws PessoaException {
        try {
            String sql = "SELECT " +
                            "id, " +
                            "nome_completo, " +
                            "data_nascimento, " +
                            "documento, " +
                            "pais, " +
                            "estado, " +
                            "cidade " +
                            "FROM pessoa " +
                        "ORDER BY id DESC " +
                        "LIMIT 1";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            if(resultado.next()) {
                return new Pessoa(
                        resultado.getLong("id"),
                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new PessoaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Pessoa selecionarPorDocumento(String documento) throws PessoaException {
        try {
            String sql = "SELECT " +
                    "id, " +
                    "nome_completo, " +
                    "data_nascimento, " +
                    "documento, " +
                    "pais, " +
                    "estado, " +
                    "cidade " +
                    "FROM pessoa " +
                    "WHERE documento = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, documento);
            ResultSet resultado = preparacao.executeQuery();

            if(resultado.next()) {
                return new Pessoa(
                        resultado.getLong("id"),
                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new PessoaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

}
