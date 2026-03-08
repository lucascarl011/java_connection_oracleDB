package projeto.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoOracle {
    private static final String URL     = "jdbc:oracle:thin:@192.168.15.6:1521/xepdb1";
    private static final String USUARIO = "system";
    private static final String SENHA   = "123A56789o";

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public static void testarConexao() {
        System.out.println("Tentando conectar ao Oracle...");
        try (Connection conn = obterConexao()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexão estabelecida com sucesso!");
                System.out.println("Banco  : " + conn.getMetaData().getDatabaseProductName());
                System.out.println("Versão : " + conn.getMetaData().getDatabaseProductVersion());
            }
        } catch (SQLException e) {
            System.err.println("Falha na conexão: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
