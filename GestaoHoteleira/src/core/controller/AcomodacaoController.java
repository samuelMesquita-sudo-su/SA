package core.controller;

import base.enumeration.Funcionalidade;
import base.exception.AcomodacaoException;
import core.test.AcomodacaoTest;

import java.util.Scanner;

public class AcomodacaoController {

    // Atributos
    private AcomodacaoTest acomodacaoTest;

    // Construtor
    public AcomodacaoController(AcomodacaoTest acomodacaoTest) {
        this.acomodacaoTest = acomodacaoTest;
    }

    // Gerenciador de testes
    public void iniciar() throws AcomodacaoException {
        Scanner entrada = new Scanner(System.in);
        String opcao = null;

        System.out.println(
                "=== TESTE MODULO ACOMODAÇÃO ===\n"
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
                    System.out.println(acomodacaoTest.testar(funcionalidade));
                } catch(AcomodacaoException excecao) {
                    System.err.println(excecao.getMessage());
                }
            }

        } while(!opcao.isEmpty());
    }

}
