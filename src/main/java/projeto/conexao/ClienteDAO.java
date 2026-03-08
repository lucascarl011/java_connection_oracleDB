package projeto.conexao;

import java.sql.*;
import java.util.List;

public class ClienteDAO {

    // Inserção única — retorna o ID gerado pelo Oracle
    public int inserir(Cliente cliente) {
        String sql = "INSERT INTO CLIENTES (NOME, EMAIL, TELEFONE) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoOracle.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID"})) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGerado = rs.getInt(1);
                        cliente.setId(idGerado);
                        System.out.println("Cliente inserido: " + cliente);
                        return idGerado;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    // Inserção em lote — muito mais eficiente para muitos registros
    public int inserirEmLote(List<Cliente> clientes) {
        String sql = "INSERT INTO CLIENTES (NOME, EMAIL, TELEFONE) VALUES (?, ?, ?)";
        int totalInseridos = 0;

        try (Connection conn = ConexaoOracle.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false); // controle manual da transação

            for (Cliente c : clientes) {
                stmt.setString(1, c.getNome());
                stmt.setString(2, c.getEmail());
                stmt.setString(3, c.getTelefone());
                stmt.addBatch();
            }

            int[] resultados = stmt.executeBatch();
            conn.commit();

            for (int r : resultados) {
                if (r > 0) totalInseridos++;
            }
            System.out.println("Lote inserido: " + totalInseridos + "/" + clientes.size() + " registros.");

        } catch (SQLException e) {
            System.err.println("Erro no batch — transação revertida: " + e.getMessage());
            e.printStackTrace();
        }

        return totalInseridos;
    }
}
