package core.dao;

import java.util.ArrayList;

public interface DAO<T> {

    public ArrayList<T> selecionar() throws Exception;
    public Boolean inserir(T obj) throws Exception;
    public Boolean atualizar(T obj) throws Exception;
    public Boolean deletar(Long id) throws Exception;
    public T selecionarPorId(Long id) throws Exception;

}
