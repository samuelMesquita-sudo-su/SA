package core.service;

import base.enumeration.Funcionalidade;
import base.exception.AcomodacaoException;
import base.util.Utilidades;
import core.dao.AcomodacaoDAO;
import core.model.Acomodacao;
import core.model.Funcionario;

import java.util.ArrayList;

public class AcomodacaoService {

    // Atributos
    private AcomodacaoDAO acomodacaoDAO;

    // Construtor
    public AcomodacaoService(AcomodacaoDAO acomodacaoDAO) {
        this.acomodacaoDAO = acomodacaoDAO;
    }

// Métodos públicos

    public String listar() throws AcomodacaoException {
        ArrayList<Acomodacao> acomodacoes = acomodacaoDAO.selecionar();
        String lista = "";
        if(acomodacoes.size() > 0) {
            for (Acomodacao acomodacao : acomodacoes) {
                lista += acomodacao + "\n";
            }
        } else {
            lista = "Nenhuma acomodação encontrada.";
        }
        return lista;
    }

    public String cadastrar(
            String nome,
            Double valorDiaria,
            Integer limiteHospedes,
            String descricao,
            Funcionario funcionarioResponsavel
    ) throws AcomodacaoException {
        String mensagemErro = validarCampos(Funcionalidade.CADASTRAR, null, nome, valorDiaria, limiteHospedes, funcionarioResponsavel);
        if(!mensagemErro.isEmpty()) throw new AcomodacaoException(mensagemErro);

        Acomodacao acomodacao = new Acomodacao(
                nome,
                valorDiaria,
                limiteHospedes,
                descricao,
                funcionarioResponsavel
        );

        if(acomodacaoDAO.inserir(acomodacao)) {
            return "Acomodação cadastrada com sucesso!";
        } else {
            throw new AcomodacaoException("Não foi possível cadastrar a acomodação! Por favor, tente novamente.");
        }
    }

    public String alterar(
            String id,
            String nome,
            Double valorDiaria,
            Integer limiteHospedes,
            String descricao,
            Funcionario funcionarioResponsavel
    ) throws AcomodacaoException {
        String mensagemErro = validarCampos(Funcionalidade.ALTERAR, id, nome, valorDiaria, limiteHospedes, funcionarioResponsavel);
        if(!mensagemErro.isEmpty()) throw new AcomodacaoException(mensagemErro);

        Long idNumerico = Long.parseLong(id);

        Acomodacao acomodacao = new Acomodacao(
                idNumerico,
                nome,
                valorDiaria,
                limiteHospedes,
                descricao,
                funcionarioResponsavel
        );

        if(acomodacaoDAO.atualizar(acomodacao)) {
            return "Acomodação alterada com sucesso!";
        } else {
            throw new AcomodacaoException("Não foi possível alterar a acomodação! Por favor, tente novamente.");
        }
    }

    public String excluir(String id) throws AcomodacaoException {
        String mensagemErro = validarCampos(Funcionalidade.EXCLUIR, id, null, null, null, null);
        if(!mensagemErro.isEmpty()) throw new AcomodacaoException(mensagemErro);

        Long idNumerico = Long.parseLong(id);
        if(acomodacaoDAO.deletar(idNumerico)) {
            return "Acomodação excluída com sucesso!";
        } else {
            throw new AcomodacaoException("Não foi possível excluir a acomodação! Por favor, tente novamente.");
        }
    }

// Métodos privados

    private String validarCampos(
            Funcionalidade funcionalidade,
            String id,
            String nome,
            Double valorDiaria,
            Integer limiteHospedes,
            Funcionario funcionarioResponsavel
    ) throws AcomodacaoException {
        String erros = "";

        // Verificação de id
        if(funcionalidade == Funcionalidade.ALTERAR || funcionalidade == Funcionalidade.EXCLUIR) {
            if(!id.isEmpty()) {
                if (Utilidades.validarNumero(id)) {
                    Long idNumerico = Long.parseLong(id);
                    if(acomodacaoDAO.selecionarPorId(idNumerico) == null) erros += "\n- Id não encontrado.";
                } else {
                    erros += "\n- Id inválido.";
                }
            } else {
                erros += "\n- Id é obrigatório.";
            }
        }

        // Verificação dos outros campos
        if(funcionalidade == Funcionalidade.CADASTRAR || funcionalidade == Funcionalidade.ALTERAR) {
            if(nome == null || nome.trim().isEmpty()) {
                erros += "\n- Nome da acomodação é obrigatório.";
            }
            if(valorDiaria == null) {
                erros += "\n- Valor da diária é obrigatório.";
            }
            if(limiteHospedes == null) {
                erros += "\n- Limite de hóspedes é obrigatório.";
            }
            if(funcionarioResponsavel == null || funcionarioResponsavel.getId() == null) {
                erros += "\n- O ID do funcionário responsável é obrigatório.";
            }
        }

        // Montagem da mensagem de erro
        String mensagemErro = "";
        if(!erros.isEmpty()) {
            mensagemErro = "Não foi possível " + funcionalidade.name().toLowerCase() + " a acomodação! " +
                    "Erro(s) encontrado(s):" + erros;
        }
        return mensagemErro;
    }

}
