package projeto.conexao;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Testar conexão
        ConexaoOracle.testarConexao();

        ClienteDAO dao = new ClienteDAO();

        // Inserção única
        System.out.println("\n=== Inserção única ===");
        Cliente c1 = new Cliente("Maria Silva", "maria@email.com", "(11) 91234-5678");
        dao.inserir(c1);

        // Inserção em lote
        System.out.println("\n=== Inserção em lote ===");
        List<Cliente> lote = Arrays.asList(
                new Cliente("João Souza", "joao@email.com", "(21) 98765-4321"),
                new Cliente("Ana Pereira", "ana@email.com", "(31) 97654-3210"),
                new Cliente("Carlos Lima", "carlos@email.com", "(41) 96543-2109"),
                new Cliente("Beatriz Costa", "beatriz@email.com", "(51) 95432-1098")
        );
        dao.inserirEmLote(lote);
    }
}