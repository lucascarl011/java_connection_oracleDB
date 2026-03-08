package projeto.conexao;

public class Cliente {

    private int id;
    private String nome;
    private String email;
    private String telefone;

    // Construtor para inserção (ID gerado pelo banco)
    public Cliente (String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    // Construtor completo (para leituras futuras)
    public Cliente (int id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String tel) {
        this.telefone = tel;
    }

    @Override
    public String toString() {
        return String.format("Cliente{id=%d, nome='%s', email='%s', telefone='%s'}",
                id, nome, email, telefone);
    }
}
