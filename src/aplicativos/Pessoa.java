package aplicativos;

public class Pessoa {
    private String nome;
    private String cpf;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        // if (cpf.length() != 11) {
        //     throw new IllegalArgumentException("O CPF deve possuir 11 digitos");
        // }


        this.cpf = cpf;
    }
}
