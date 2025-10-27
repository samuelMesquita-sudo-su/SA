package core.dao;

import base.connection.ConexaoMySQL;
import base.exception.ReservaException;
import core.model.Acomodacao;
import core.model.Cliente;
import core.model.Funcionario;
import core.model.Reserva;

import java.sql.*;
import java.util.ArrayList;

public class ReservaDAO implements DAO<Reserva> {

    // Métodos de interação com banco de dados

    public ArrayList<Reserva> selecionar() throws ReservaException {
        try {
            String sql = "SELECT " +
                            "r.id, " +
                            "r.data_inicio, " +
                            "r.data_fim, " +
                            "r.valor_total, " +
                            "r.qtd_hospedes, " +
                            "r.id_acomodacao, " +
                            "r.id_cliente_responsavel, " +
                            "a.nome as nome_acomodacao, " +
                            "a.valor_diaria, " +
                            "a.limite_hospedes, " +
                            "a.descricao, " +
                            "a.id_funcionario_responsavel, " +
                            "f.cargo, " +
                            "f.salario, " +
                            "f.id_pessoa as id_pessoa_funcionario, " +
                            "pf.nome_completo as nome_completo_funcionario, " +
                            "pf.data_nascimento as data_nascimento_funcionario, " +
                            "pf.documento as documento_funcionario, " +
                            "pf.pais as pais_funcionario, " +
                            "pf.estado as estado_funcionario, " +
                            "pf.cidade as cidade_funcionario, " +
                            "c.fidelidade, " +
                            "c.observacao, " +
                            "c.id_pessoa as id_pessoa_cliente, " +
                            "pc.nome_completo as nome_completo_cliente, " +
                            "pc.data_nascimento as data_nascimento_cliente, " +
                            "pc.documento as documento_cliente, " +
                            "pc.pais as pais_cliente, " +
                            "pc.estado as estado_cliente, " +
                            "pc.cidade as cidade_cliente " +
                        "FROM reserva r " +
                        "JOIN acomodacao a ON a.id = r.id_acomodacao " +
                        "JOIN funcionario f ON f.id = a.id_funcionario_responsavel " +
                        "JOIN pessoa pf ON pf.id = f.id_pessoa " +
                        "JOIN cliente c ON c.id = r.id_cliente_responsavel " +
                        "JOIN pessoa pc ON pc.id = c.id_pessoa";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Reserva> reservas = new ArrayList<>();
            while(resultado.next()) {
                Funcionario funcionarioResponsavel = new Funcionario(
                    resultado.getLong("id_pessoa_funcionario"),
                    resultado.getString("nome_completo_funcionario"),
                    resultado.getDate("data_nascimento_funcionario").toLocalDate(),
                    resultado.getString("documento_funcionario"),
                    resultado.getString("pais_funcionario"),
                    resultado.getString("estado_funcionario"),
                    resultado.getString("cidade_funcionario"),
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
                Cliente clienteResponsavel = new Cliente(
                    resultado.getLong("id_pessoa_cliente"),
                    resultado.getString("nome_completo_cliente"),
                    resultado.getDate("data_nascimento_cliente").toLocalDate(),
                    resultado.getString("documento_cliente"),
                    resultado.getString("pais_cliente"),
                    resultado.getString("estado_cliente"),
                    resultado.getString("cidade_cliente"),
                    resultado.getLong("id_cliente_responsavel"),
                    resultado.getBoolean("fidelidade"),
                    resultado.getString("observacao")
                );
                Reserva reserva = new Reserva(
                    resultado.getLong("id"),
                    resultado.getDate("data_inicio").toLocalDate(),
                    resultado.getDate("data_fim").toLocalDate(),
                    acomodacao,
                    clienteResponsavel,
                    resultado.getInt("qtd_hospedes"),
                    resultado.getDouble("valor_total")
                );
                reservas.add(reserva);
            }
            return reservas;

        } catch (SQLException e) {
            throw new ReservaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean inserir(Reserva reserva) throws ReservaException {
        try {
            String sql = "INSERT INTO reserva " +
                        "(id_acomodacao, id_cliente_responsavel, data_inicio, data_fim, qtd_hospedes, valor_total) " +
                        "VALUES (?,?,?,?,?,?)";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, reserva.getAcomodacao().getId());
            preparacao.setLong(2, reserva.getClienteResponsavel().getId());
            preparacao.setDate(3, Date.valueOf(reserva.getDataInicio()));
            preparacao.setDate(4, Date.valueOf(reserva.getDataFim()));
            preparacao.setInt(5, reserva.getQtdHospedes());
            preparacao.setDouble(6, reserva.getValorTotal());;
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ReservaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Boolean atualizar(Reserva reserva) throws ReservaException {
        try {
            String sql = "UPDATE reserva " +
                        "SET " +
                            "id_acomodacao = ? , " +
                            "id_cliente_responsavel = ?, " +
                            "data_inicio = ?, " +
                            "data_fim = ?, " +
                            "qtd_hospedes = ?, " +
                            "valor_total = ? " +
                        "WHERE id = ?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, reserva.getAcomodacao().getId());
            preparacao.setLong(2, reserva.getClienteResponsavel().getId());
            preparacao.setDate(3, Date.valueOf(reserva.getDataInicio()));
            preparacao.setDate(4, Date.valueOf(reserva.getDataFim()));
            preparacao.setInt(5, reserva.getQtdHospedes());
            preparacao.setDouble(6, reserva.getValorTotal());;
            preparacao.setLong(7, reserva.getId());
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ReservaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }
    public Boolean deletar(Long id) throws ReservaException {
        try {
            String sql = "DELETE FROM reserva WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new ReservaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

    public Reserva selecionarPorId(Long id) throws ReservaException {
        try {
            String sql = "SELECT " +
                            "r.id, " +
                            "r.data_inicio, " +
                            "r.data_fim, " +
                            "r.valor_total, " +
                            "r.qtd_hospedes, " +
                            "r.id_acomodacao, " +
                            "r.id_cliente_responsavel, " +
                            "a.nome as nome_acomodacao, " +
                            "a.valor_diaria, " +
                            "a.limite_hospedes, " +
                            "a.descricao, " +
                            "a.id_funcionario_responsavel, " +
                            "f.cargo, " +
                            "f.salario, " +
                            "f.id_pessoa as id_pessoa_funcionario, " +
                            "pf.nome_completo as nome_completo_funcionario, " +
                            "pf.data_nascimento as data_nascimento_funcionario, " +
                            "pf.documento as documento_funcionario, " +
                            "pf.pais as pais_funcionario, " +
                            "pf.estado as estado_funcionario, " +
                            "pf.cidade as cidade_funcionario, " +
                            "c.fidelidade, " +
                            "c.observacao, " +
                            "c.id_pessoa as id_pessoa_cliente, " +
                            "pc.nome_completo as nome_completo_cliente, " +
                            "pc.data_nascimento as data_nascimento_cliente, " +
                            "pc.documento as documento_cliente, " +
                            "pc.pais as pais_cliente, " +
                            "pc.estado as estado_cliente, " +
                            "pc.cidade as cidade_cliente " +
                            "FROM reserva r " +
                            "JOIN acomodacao a ON a.id = r.id_acomodacao " +
                            "JOIN funcionario f ON f.id = a.id_funcionario_responsavel " +
                            "JOIN pessoa pf ON pf.id = f.id_pessoa " +
                            "JOIN cliente c ON c.id = r.id_cliente_responsavel " +
                            "JOIN pessoa pc ON pc.id = c.id_pessoa " +
                        "WHERE r.id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if(resultado.next()) {
                Funcionario funcionarioResponsavel = new Funcionario(
                        resultado.getLong("id_pessoa_funcionario"),
                        resultado.getString("nome_completo_funcionario"),
                        resultado.getDate("data_nascimento_funcionario").toLocalDate(),
                        resultado.getString("documento_funcionario"),
                        resultado.getString("pais_funcionario"),
                        resultado.getString("estado_funcionario"),
                        resultado.getString("cidade_funcionario"),
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
                Cliente clienteResponsavel = new Cliente(
                        resultado.getLong("id_pessoa_cliente"),
                        resultado.getString("nome_completo_cliente"),
                        resultado.getDate("data_nascimento_cliente").toLocalDate(),
                        resultado.getString("documento_cliente"),
                        resultado.getString("pais_cliente"),
                        resultado.getString("estado_cliente"),
                        resultado.getString("cidade_cliente"),
                        resultado.getLong("id_cliente_responsavel"),
                        resultado.getBoolean("fidelidade"),
                        resultado.getString("observacao")
                );
                return new Reserva(
                    resultado.getLong("id"),
                    resultado.getDate("data_inicio").toLocalDate(),
                    resultado.getDate("data_fim").toLocalDate(),
                    acomodacao,
                    clienteResponsavel,
                    resultado.getInt("qtd_hospedes"),
                    resultado.getDouble("valor_total")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new ReservaException("Erro desconhecido! Por favor, tente novamente mais tarde.");
        }
    }

}
