import core.controller.PessoaController;
import core.dao.PessoaDAO;
import base.exception.PessoaException;
import core.service.PessoaService;
import core.test.PessoaTest;

public class PrincipalPessoa {

    public static void main(String[] args) throws PessoaException {
        // Inicialização de objetos
        PessoaDAO pessoaDAO = new PessoaDAO();
        PessoaService pessoaService = new PessoaService(pessoaDAO);
        PessoaTest pessoaTest = new PessoaTest(pessoaService);
        PessoaController pessoaController = new PessoaController(pessoaTest);
        pessoaController.iniciar();
    }
}
