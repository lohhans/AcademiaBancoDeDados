package negocios.entidades;

public class Modalidade {

    private String nome;
    private static int id = 1;
    private final int codigoModalidade;
    private double preco;

    public Modalidade(String nome, double preco) {
        this.nome = nome;
        codigoModalidade = id;
        ++id;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static int getId() {
        return id;
    }

    public int getCodigoModalidade() {
        return codigoModalidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object objetoModalidade) {
        if(objetoModalidade instanceof Modalidade){
            Modalidade modalidade = (Modalidade) objetoModalidade;
            if (modalidade.getCodigoModalidade() == this.codigoModalidade){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Modalidade:\n" +
                "Nome = " + nome + "\n" +
                "Código da modalidade = " + codigoModalidade + "\n" +
                "Preço = " + preco + "\n";
    }
}
