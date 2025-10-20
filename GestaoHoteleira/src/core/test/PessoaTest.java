package core.test;

import base.enumeration.Funcionalidade;
import base.exception.PessoaException;
import core.service.PessoaService;

public class PessoaTest implements Test {

    // Atributos
    private PessoaService pessoaService;

    // Construtor
    public PessoaTest(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    // Métodos de testes

    public String testar(Funcionalidade funcionalidade) throws PessoaException {
        switch (funcionalidade) {
            case LISTAR:
                return this.listar();
            case CADASTRAR:
                return this.cadastrar();
            case ALTERAR:
                return this.alterar();
            case EXCLUIR:
                return this.excluir();
            default:
                return null;
        }
    }

    public String listar() throws PessoaException {
        return pessoaService.listar();
    }

    public String cadastrar() throws PessoaException {
        // Dados para cadastro
        String nomeCompleto = "João Luiz da Silva";
        String dataNascimento = "01/02/1990";
        String documento = "121.341.560-82";
        String pais = "Brasil";
        String estado = "SC";
        String cidade = "Tubarão";

        return pessoaService.cadastrar(nomeCompleto, dataNascimento, documento, pais, estado, cidade);
    }

    public String alterar() throws PessoaException {
        // Dados para alteração
        String id = "1";
        String nomeCompleto = "João Luiz da Silva Pereira";
        String dataNascimento = "01/02/1989";
        String documento = "670.022.930-87";
        String pais = "Argentina";
        String estado = "Santa Fe";
        String cidade = "Rosário";

        return pessoaService.alterar(id, nomeCompleto, dataNascimento, documento, pais, estado, cidade);
    }

    public String excluir() throws PessoaException {
        // Dados para exclusão
        String id = "10";

        return pessoaService.excluir(id);
    }

}
