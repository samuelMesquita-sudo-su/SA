package core.dao;

import base.connection.ConexaoMySQL;
import base.exception.FuncionarioException;
import core.model.Funcionario;
import core.model.Pessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FuncionarioDAO implements DAO<Funcionario> {

    // Métodos de interação com banco de dados

    public ArrayList<Funcionario> selecionar() throws FuncionarioException {
        try {
            String sql = "SELECT " +
                            "f.id, " +
                            "f.id_pessoa, " +
                            "f.cargo, " +
                            "f.salario, " +
                            "p.nome_completo, " +
                            "p.data_nascimento, " +
                            "p.documento, " +
                            "p.pais, " +
                            "p.estado, " +
                            "p.cidade " +
                        "FROM funcionario f " +
                        "JOIN pessoa p ON p.id = f.id_pessoa";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Funcionario> funcionarios = new ArrayList<>();
            while(resultado.next()) {
                Funcionario funcionario = new Funcionario(
                    resultado.getLong("id_pessoa"),
                    resultado.getString("nome_completo"),
                    resultado.getDate("data_nascimento").toLocalDate(),
                    resultado.getString("documento"),
                    resultado.getString("pais"),
                    resultado.getString("estado"),
                    resultado.getString("cidade"),
                    resultado.getLong("id"),
                    resultado.getString("cargo"),
                    resultado.getDouble("salario")
                );
                funcionarios.add(funcionario);
            }
            return funcionarios;

        } catch (SQLException e) {
            throw new FuncionarioException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean inserir(Funcionario funcionario) throws FuncionarioException {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            if(!pessoaDAO.inserir(funcionario)) return false;
            Pessoa pessoa = pessoaDAO.selecionarUltima();
            if(pessoa == null) return false;

            String sql = "INSERT INTO funcionario " +
                        "(id_pessoa, cargo, salario) " +
                        "VALUES (?,?,?)";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, pessoa.getIdPessoa());
            preparacao.setString(2, funcionario.getCargo());
            preparacao.setDouble(3, funcionario.getSalario());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new FuncionarioException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean atualizar(Funcionario funcionario) throws FuncionarioException {
        try {
            Pessoa pessoa = new Pessoa(
                    funcionario.getIdPessoa(),
                    funcionario.getNomeCompleto(),
                    funcionario.getDataNascimento(),
                    funcionario.getDocumento(),
                    funcionario.getPais(),
                    funcionario.getEstado(),
                    funcionario.getCidade()
            );
            PessoaDAO pessoaDAO = new PessoaDAO();
            if(!pessoaDAO.atualizar(pessoa)) return false;

            String sql = "UPDATE funcionario " +
                        "SET " +
                            "cargo = ?, " +
                            "salario = ? " +
                        "WHERE id = ?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, funcionario.getCargo());
            preparacao.setDouble(2, funcionario.getSalario());
            preparacao.setLong(3, funcionario.getId());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new FuncionarioException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }
    public Boolean deletar(Long id) throws FuncionarioException {
        try {
            String sql = "DELETE FROM funcionario WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new FuncionarioException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Funcionario selecionarPorId(Long id) throws FuncionarioException {
        try {
            String sql = "SELECT " +
                            "f.id, " +
                            "f.id_pessoa, " +
                            "f.cargo, " +
                            "f.salario, " +
                            "p.nome_completo, " +
                            "p.data_nascimento, " +
                            "p.documento, " +
                            "p.pais, " +
                            "p.estado, " +
                            "p.cidade " +
                        "FROM funcionario f " +
                        "JOIN pessoa p ON p.id = f.id_pessoa " +
                        "WHERE f.id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if(resultado.next()) {
                return new Funcionario(
                        resultado.getLong("id_pessoa"),
                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade"),
                        resultado.getLong("id"),
                        resultado.getString("cargo"),
                        resultado.getDouble("salario")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new FuncionarioException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Funcionario selecionarPorDocumento(String documento) throws FuncionarioException {
        try {
            String sql = "SELECT " +
                    "f.id, " +
                    "f.id_pessoa, " +
                    "f.cargo, " +
                    "f.salario, " +
                    "p.nome_completo, " +
                    "p.data_nascimento, " +
                    "p.documento, " +
                    "p.pais, " +
                    "p.estado, " +
                    "p.cidade " +
                    "FROM funcionario f " +
                    "JOIN pessoa p ON p.id = f.id_pessoa " +
                    "WHERE p.documento = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, documento);
            ResultSet resultado = preparacao.executeQuery();

            if(resultado.next()) {
                return new Funcionario(
                    resultado.getLong("id_pessoa"),
                    resultado.getString("nome_completo"),
                    resultado.getDate("data_nascimento").toLocalDate(),
                    resultado.getString("documento"),
                    resultado.getString("pais"),
                    resultado.getString("estado"),
                    resultado.getString("cidade"),
                    resultado.getLong("id"),
                    resultado.getString("cargo"),
                    resultado.getDouble("salario")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new FuncionarioException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

}
