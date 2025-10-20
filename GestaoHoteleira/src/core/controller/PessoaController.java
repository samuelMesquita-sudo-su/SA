package core.controller;

import base.enumeration.Funcionalidade;
import base.exception.PessoaException;
import core.test.PessoaTest;

import java.util.Scanner;

public class PessoaController {

    // Atributos
    private PessoaTest pessoaTest;

    // Construtor
    public PessoaController(PessoaTest pessoaTest) {
        this.pessoaTest = pessoaTest;
    }

    // Gerenciador de testes
    public void iniciar() throws PessoaException {
        Scanner entrada = new Scanner(System.in);
        String opcao = null;

        System.out.println(
                "=== TESTE MODULO PESSOA ===\n"
                        + "1 - Listar\n"
                        + "2 - Cadastrar\n"
                        + "3 - Alterar\n"
                        + "4 - Excluir"
        );

        do {
            System.out.println("\nEscolha a funcionalidade:");
            opcao = entrada.nextLine();

            Funcionalidade funcionalidade = null;
            switch (opcao) {
                case "1":
                    funcionalidade = Funcionalidade.LISTAR;
                    break;
                case "2":
                    funcionalidade = Funcionalidade.CADASTRAR;
                    break;
                case "3":
                    funcionalidade = Funcionalidade.ALTERAR;
                    break;
                case "4":
                    funcionalidade = Funcionalidade.EXCLUIR;
                    break;
            }

            if(funcionalidade != null) {
                try {
                    System.out.println("Funcionalidade: " + funcionalidade);
                    System.out.println(pessoaTest.testar(funcionalidade));
                } catch(PessoaException excecao) {
                    System.err.println(excecao.getMessage());
                }
            }

        } while(!opcao.isEmpty());
    }

}
