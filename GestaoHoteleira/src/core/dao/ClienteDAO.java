package core.dao;

import base.connection.ConexaoMySQL;
import base.exception.ClienteException;
import core.model.Cliente;
import core.model.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO implements DAO<Cliente> {

    // Métodos de interação com banco de dados

    public ArrayList<Cliente> selecionar() throws ClienteException {
        try {
            String sql = "SELECT " +
                            "c.id, " +
                            "c.id_pessoa, " +
                            "c.fidelidade, " +
                            "c.observacao, " +
                            "p.nome_completo, " +
                            "p.data_nascimento, " +
                            "p.documento, " +
                            "p.pais, " +
                            "p.estado, " +
                            "p.cidade " +
                        "FROM cliente c " +
                        "JOIN pessoa p ON p.id = c.id_pessoa";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Cliente> clientes = new ArrayList<>();
            while(resultado.next()) {
                Cliente cliente = new Cliente(
                    resultado.getLong("id_pessoa"),
                    resultado.getString("nome_completo"),
                    resultado.getDate("data_nascimento").toLocalDate(),
                    resultado.getString("documento"),
                    resultado.getString("pais"),
                    resultado.getString("estado"),
                    resultado.getString("cidade"),
                    resultado.getLong("id"),
                    resultado.getBoolean("fidelidade"),
                    resultado.getString("observacao")
                );
                clientes.add(cliente);
            }
            return clientes;

        } catch (SQLException e) {
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean inserir(Cliente cliente) throws ClienteException {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            if(!pessoaDAO.inserir(cliente)) return false;
            Pessoa pessoa = pessoaDAO.selecionarUltima();
            if(pessoa == null) return false;

            String sql = "INSERT INTO cliente " +
                        "(id_pessoa, fidelidade, observacao) " +
                        "VALUES (?,?,?)";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, pessoa.getIdPessoa());
            preparacao.setBoolean(2, cliente.getFidelidade());
            preparacao.setString(3, cliente.getObservacao());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("aqui");
            e.printStackTrace();
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean atualizar(Cliente cliente) throws ClienteException {
        try {
            Pessoa pessoa = new Pessoa(
                cliente.getIdPessoa(),
                cliente.getNomeCompleto(),
                cliente.getDataNascimento(),
                cliente.getDocumento(),
                cliente.getPais(),
                cliente.getEstado(),
                cliente.getCidade()
            );
            PessoaDAO pessoaDAO = new PessoaDAO();
            if(!pessoaDAO.atualizar(pessoa)) return false;

            String sql = "UPDATE cliente " +
                        "SET " +
                            "fidelidade = ?, " +
                            "observacao = ? " +
                        "WHERE id = ?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setBoolean(1, cliente.getFidelidade());
            preparacao.setString(2, cliente.getObservacao());
            preparacao.setLong(3, cliente.getId());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }
    public Boolean deletar(Long id) throws ClienteException {
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Cliente selecionarPorId(Long id) throws ClienteException {
        try {
            String sql = "SELECT " +
                            "c.id, " +
                            "c.id_pessoa, " +
                            "c.fidelidade, " +
                            "c.observacao, " +
                            "p.nome_completo, " +
                            "p.data_nascimento, " +
                            "p.documento, " +
                            "p.pais, " +
                            "p.estado, " +
                            "p.cidade " +
                        "FROM cliente c " +
                        "JOIN pessoa p ON p.id = c.id_pessoa " +
                        "WHERE c.id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if(resultado.next()) {
                return new Cliente(
                    resultado.getLong("id_pessoa"),
                    resultado.getString("nome_completo"),
                    resultado.getDate("data_nascimento").toLocalDate(),
                    resultado.getString("documento"),
                    resultado.getString("pais"),
                    resultado.getString("estado"),
                    resultado.getString("cidade"),
                    resultado.getLong("id"),
                    resultado.getBoolean("fidelidade"),
                    resultado.getString("observacao")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Cliente selecionarPorDocumento(String documento) throws ClienteException {
        try {
            String sql = "SELECT " +
                    "c.id, " +
                    "c.id_pessoa, " +
                    "c.fidelidade, " +
                    "c.observacao, " +
                    "p.nome_completo, " +
                    "p.data_nascimento, " +
                    "p.documento, " +
                    "p.pais, " +
                    "p.estado, " +
                    "p.cidade " +
                    "FROM cliente c " +
                    "JOIN pessoa p ON p.id = c.id_pessoa " +
                    "WHERE p.documento = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, documento);
            ResultSet resultado = preparacao.executeQuery();

            if(resultado.next()) {
                return new Cliente(
                    resultado.getLong("id_pessoa"),
                    resultado.getString("nome_completo"),
                    resultado.getDate("data_nascimento").toLocalDate(),
                    resultado.getString("documento"),
                    resultado.getString("pais"),
                    resultado.getString("estado"),
                    resultado.getString("cidade"),
                    resultado.getLong("id"),
                    resultado.getBoolean("fidelidade"),
                    resultado.getString("observacao")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ClienteException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

}
