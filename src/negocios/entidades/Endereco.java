package negocios.entidades;

public class Endereco {

    private String cep, numero, rua, bairro, cidade;

    public Endereco(String cep, String numero, String rua, String bairro, String cidade){
        this.cep = cep;
        this.numero = numero;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object objetoEndereco) {
        if(objetoEndereco instanceof Endereco){
            Endereco endereco = (Endereco) objetoEndereco;
            if (endereco.getCep().equalsIgnoreCase(this.cep) && endereco.getNumero().equalsIgnoreCase(this.numero) && endereco.getRua().equalsIgnoreCase(this.rua) && endereco.getBairro().equalsIgnoreCase(this.bairro) && endereco.getCidade().equalsIgnoreCase(this.cidade)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Endereço:\n" +
                "CEP = " + cep + "\n" +
                "Número = " + numero + "\n" +
                "Rua = " + rua + "\n" +
                "Bairro = " + bairro + "\n" +
                "Cidade = " + cidade;
    }
}
