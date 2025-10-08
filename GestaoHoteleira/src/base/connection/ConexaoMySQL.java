package base.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    // Atributos de conexão
    private static final String url = "jdbc:mysql://localhost:3306/gestao_hoteleira";
    private static final String usuario = "root";
    private static final String senha = "";
    private static Connection conexao;

    // Cria ou pega a conexão
    public static Connection get() {
        try {
            if(conexao == null) {
                conexao = DriverManager.getConnection(url, usuario, senha);
            }
            return conexao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
