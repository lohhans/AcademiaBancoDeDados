package negocios.entidades;

import java.util.Date;

public class Avaliacao {

    private int numeroDaAvaliacao;
    private double circunferenciaAbdominal,torax, cintura, quadril, antebracoDireito, antebracoEsquerdo, bracoDireito, bracoEsquerdo, coxaDireita, coxaEsquerda, panturrilhaDireita, panturrilhaEsquerda;
    private Date data;

    public Avaliacao(double circunferenciaAbdominal, double torax, double cintura, double quadril, double antebracoDireito, double antebracoEsquerdo, double bracoDireito, double bracoEsquerdo, double coxaDireita, double coxaEsquerda, double panturrilhaDireita, double panturrilhaEsquerda) {
        this.circunferenciaAbdominal = circunferenciaAbdominal;
        this.torax = torax;
        this.cintura = cintura;
        this.quadril = quadril;
        this.antebracoDireito = antebracoDireito;
        this.antebracoEsquerdo = antebracoEsquerdo;
        this.bracoDireito = bracoDireito;
        this.bracoEsquerdo = bracoEsquerdo;
        this.coxaDireita = coxaDireita;
        this.coxaEsquerda = coxaEsquerda;
        this.panturrilhaDireita = panturrilhaDireita;
        this.panturrilhaEsquerda = panturrilhaEsquerda;
        this.data = new Date();
    }

    public int getNumeroDaAvaliacao() {
        return numeroDaAvaliacao;
    }

    public void setNumeroDaAvaliacao(int numeroDaAvaliacao) {
        this.numeroDaAvaliacao = numeroDaAvaliacao;
    }

    public double getCircunferenciaAbdominal() {
        return circunferenciaAbdominal;
    }

    public void setCircunferenciaAbdominal(double circunferenciaAbdominal) {
        this.circunferenciaAbdominal = circunferenciaAbdominal;
    }

    public double getTorax() {
        return torax;
    }

    public void setTorax(double torax) {
        this.torax = torax;
    }

    public double getCintura() {
        return cintura;
    }

    public void setCintura(double cintura) {
        this.cintura = cintura;
    }

    public double getQuadril() {
        return quadril;
    }

    public void setQuadril(double quadril) {
        this.quadril = quadril;
    }

    public double getAntebracoDireito() {
        return antebracoDireito;
    }

    public void setAntebracoDireito(double antebracoDireito) {
        this.antebracoDireito = antebracoDireito;
    }

    public double getAntebracoEsquerdo() {
        return antebracoEsquerdo;
    }

    public void setAntebracoEsquerdo(double antebracoEsquerdo) {
        this.antebracoEsquerdo = antebracoEsquerdo;
    }

    public double getBracoDireito() {
        return bracoDireito;
    }

    public void setBracoDireito(double bracoDireito) {
        this.bracoDireito = bracoDireito;
    }

    public double getBracoEsquerdo() {
        return bracoEsquerdo;
    }

    public void setBracoEsquerdo(double bracoEsquerdo) {
        this.bracoEsquerdo = bracoEsquerdo;
    }

    public double getCoxaDireita() {
        return coxaDireita;
    }

    public void setCoxaDireita(double coxaDireita) {
        this.coxaDireita = coxaDireita;
    }

    public double getCoxaEsquerda() {
        return coxaEsquerda;
    }

    public void setCoxaEsquerda(double coxaEsquerda) {
        this.coxaEsquerda = coxaEsquerda;
    }

    public double getPanturrilhaDireita() {
        return panturrilhaDireita;
    }

    public void setPanturrilhaDireita(double panturrilhaDireita) {
        this.panturrilhaDireita = panturrilhaDireita;
    }

    public double getPanturrilhaEsquerda() {
        return panturrilhaEsquerda;
    }

    public void setPanturrilhaEsquerda(double PanturrilhaEsquerda) {

        this.panturrilhaEsquerda = PanturrilhaEsquerda;
    }

    public Date getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Avaliação:\n" +
                "Número da Avaliação = " + numeroDaAvaliacao + "\n" +
                "Circunferência abdominal = " + circunferenciaAbdominal + "\n" +
                "Tórax = " + torax + "\n" +
                "Cintura = " + cintura + "\n" +
                "Quadril = " + quadril + "\n" +
                "Antebraço Direito = " + antebracoDireito + "\n" +
                "Antebraço Esquerdo = " + antebracoEsquerdo + "\n" +
                "Braço Direito = " + bracoDireito + "\n" +
                "Braço Esquerdo = " + bracoEsquerdo + "\n" +
                "Coxa Direita = " + coxaDireita + "\n" +
                "Coxa Esquerda = " + coxaEsquerda + "\n" +
                "Panturrilha Direita = " + panturrilhaDireita + "\n" +
                "Panturrilha Esquerda = " + panturrilhaEsquerda + "\n" +
                "Data da avaliação = " + data + "\n";
    }
}