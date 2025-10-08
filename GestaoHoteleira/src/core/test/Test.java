package core.test;

import base.enumeration.Funcionalidade;

public interface Test {

    public String testar(Funcionalidade funcionalidade) throws Exception;
    public String listar() throws Exception;
    public String cadastrar() throws Exception;
    public String alterar() throws Exception;
    public String excluir() throws Exception;

}
