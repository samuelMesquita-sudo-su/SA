package core.service;

import base.enumeration.Funcionalidade;
import base.exception.PessoaException;
import base.util.Utilidades;
import core.model.Pessoa;
import core.dao.PessoaDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class PessoaService {

    // Atributos
    private PessoaDAO pessoaDAO;

    // Construtor
    public PessoaService(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    // Métodos públicos

    public String listar() throws PessoaException {
        ArrayList<Pessoa> pessoas =  pessoaDAO.selecionar();
        String lista = "";
        if(pessoas.size() > 0) {
            for (Pessoa pessoa : pessoas) {
                lista += pessoa + "\n";
            }
        } else {
            lista = "Nenhuma pessoa encontrada.";
        }
        return lista;
    }

    public String cadastrar(
        String nomeCompleto,
        String dataNascimentoFormatoBR,
        String documento,
        String pais,
        String estado,
        String cidade
    ) throws PessoaException {
        String mensagemErro = validarCampos(Funcionalidade.CADASTRAR, null, nomeCompleto, dataNascimentoFormatoBR, documento);
        if(!mensagemErro.isEmpty()) throw new PessoaException(mensagemErro);

        LocalDate dataNascimento = Utilidades.formatarDataLocalDate(dataNascimentoFormatoBR);
        Pessoa pessoa = new Pessoa(
            nomeCompleto,
            dataNascimento,
            documento,
            pais,
            estado,
            cidade
        );

        if(pessoaDAO.inserir(pessoa)) {
            return "Pessoa cadastrada com sucesso!";
        } else {
            throw new PessoaException("Não foi possível cadastrar a pessoa! Por favor, tente novamente.");
        }
    }

    public String alterar(
            String id,
            String nomeCompleto,
            String dataNascimentoFormatoBR,
            String documento,
            String pais,
            String estado,
            String cidade
    ) throws PessoaException {
        String mensagemErro = validarCampos(Funcionalidade.ALTERAR, id, nomeCompleto, dataNascimentoFormatoBR, documento);
        if(!mensagemErro.isEmpty()) throw new PessoaException(mensagemErro);

        LocalDate dataNascimento = Utilidades.formatarDataLocalDate(dataNascimentoFormatoBR);
        Long idNumerico = Long.parseLong(id);

        Pessoa pessoa = new Pessoa(
                idNumerico,
                nomeCompleto,
                dataNascimento,
                documento,
                pais,
                estado,
                cidade
        );

        if(pessoaDAO.atualizar(pessoa)) {
            return "Pessoa alterada com sucesso!";
        } else {
            throw new PessoaException("Não foi possível alterar a pessoa!! Por favor, tente novamente.");
        }
    }

    public String excluir(String id) throws PessoaException {
        String mensagemErro = validarCampos(Funcionalidade.EXCLUIR, id, null, null, null);
        if(!mensagemErro.isEmpty()) throw new PessoaException(mensagemErro);

        Long idNumerico = Long.parseLong(id);
        if(pessoaDAO.deletar(idNumerico)) {
            return "Pessoa excluída com sucesso!";
        } else {
            throw new PessoaException("Não foi possível excluir a pessoa! Por favor, tente novamente.");
        }
    }

    // Métodos privados

    private String validarCampos(
        Funcionalidade funcionalidade,
        String id,
        String nomeCompleto,
        String dataNascimentoFormatoBR,
        String documento
    ) throws PessoaException {
        String erros = "";
        // Verificação de id
        if(funcionalidade == Funcionalidade.ALTERAR || funcionalidade == Funcionalidade.EXCLUIR) {
            if(!id.isEmpty()) {
                if (Utilidades.validarNumero(id)) {
                    Long idNumerico = Long.parseLong(id);
                    if(pessoaDAO.selecionarPorId(idNumerico) == null) erros += "\n- Id não encontrado.";
                } else {
                    erros += "\n- Id inválido.";
                }
            } else {
                erros += "\n- Id é obrigatório.";
            }
        }

        // Verificação de outros campos
        if(funcionalidade == Funcionalidade.CADASTRAR || funcionalidade == Funcionalidade.ALTERAR) {
            // Nome completo
            if(nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
                erros += "\n- Nome completo é obrigatório.";
            }
            // Data de nascimento
            if(dataNascimentoFormatoBR == null || dataNascimentoFormatoBR.trim().isEmpty()) {
                erros += "\n- Data de nascimento é obrigatória.";
            } else if(!Utilidades.validarDataBR(dataNascimentoFormatoBR)) {
                erros += "\n- Data de nascimento inválida.";
            }
            // Documento
            if(documento == null || documento.trim().isEmpty()) {
                erros += "\n- Documento é obrigatório.";
            }

            if(!documento.isEmpty()) {
                Pessoa pessoaDocumento = pessoaDAO.selecionarPorDocumento(documento);
                if(pessoaDocumento != null) {
                    if(funcionalidade == Funcionalidade.ALTERAR) {
                        if(Utilidades.validarNumero(id)) {
                            Long idNumerico = Long.parseLong(id);
                            if(!pessoaDocumento.getId().equals(idNumerico)) erros += "\n- Documento já existente.";
                        }
                    } else {
                        erros += "\n- Documento já existente.";
                    }
                }
            }
        }

        // Montagem da mensagem de erro
        String mensagemErro = "";
        if(!erros.isEmpty()) {
            mensagemErro = "Não foi possível " + funcionalidade.name().toLowerCase() + " a pessoa! " +
                "Erro(s) encontrado(s):" + erros;
        }
        return mensagemErro;
    }

}
