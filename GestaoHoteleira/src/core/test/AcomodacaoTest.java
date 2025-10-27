package core.test;

import base.enumeration.Funcionalidade;
import base.exception.AcomodacaoException;
import core.model.Funcionario;
import core.service.AcomodacaoService;

public class AcomodacaoTest implements Test {
    // Atributos
    private AcomodacaoService acomodacaoService;

    // Construtor
    public AcomodacaoTest(AcomodacaoService acomodacaoService) {
        this.acomodacaoService = acomodacaoService;
    }

    // Métodos de testes
    public String testar(Funcionalidade funcionalidade) throws AcomodacaoException {
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

    public String listar() throws AcomodacaoException {
        return acomodacaoService.listar();
    }

    public String cadastrar() throws AcomodacaoException {
        // Dados de acomodação para cadastro
        String nome = "Suíte Luxo Vista Mar";
        Double valorDiaria = 550.0;
        Integer limiteHospedes = 4;
        String descricao = "Acomodação premium com varanda e vista para o mar.";

        // Funcionário responsável (somente o id já existente no banco)
        Funcionario funcionarioResponsavel = new Funcionario();
        funcionarioResponsavel.setId(1L); // exemplo de ID válido

        return acomodacaoService.cadastrar(
                nome,
                valorDiaria,
                limiteHospedes,
                descricao,
                funcionarioResponsavel
        );
    }

    public String alterar() throws AcomodacaoException {
        // Dados para alteração
        String id = "1";
        String nome = "Suíte Master Reformada";
        Double valorDiaria = 600.0;
        Integer limiteHospedes = 5;
        String descricao = "Acomodação reformada com jacuzzi e vista panorâmica.";

        // Novo funcionário responsável
        Funcionario funcionarioResponsavel = new Funcionario();
        funcionarioResponsavel.setId(2L); // exemplo de outro funcionário

        return acomodacaoService.alterar(
                id,
                nome,
                valorDiaria,
                limiteHospedes,
                descricao,
                funcionarioResponsavel
        );
    }

    public String excluir() throws AcomodacaoException {
        // Dados para exclusão
        String id = "3";
        return acomodacaoService.excluir(id);
    }

}
