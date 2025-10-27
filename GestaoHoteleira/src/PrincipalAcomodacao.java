import base.exception.AcomodacaoException;
import core.controller.AcomodacaoController;
import core.dao.AcomodacaoDAO;
import core.service.AcomodacaoService;
import core.test.AcomodacaoTest;

public class PrincipalAcomodacao {

    public static void main(String[] args) throws AcomodacaoException {
        // Inicialização de objetos
        AcomodacaoDAO acomodacaoDAO = new AcomodacaoDAO();
        AcomodacaoService acomodacaoService = new AcomodacaoService(acomodacaoDAO);
        AcomodacaoTest acomodacaoTest = new AcomodacaoTest(acomodacaoService);
        AcomodacaoController acomodacaoController = new AcomodacaoController(acomodacaoTest);
        acomodacaoController.iniciar();
    }
}

