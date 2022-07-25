package Entities;

public class Conta {
    private int NUMERO_DE_CONTAS;
    private int numeroConta;
    private Cliente titular;
    protected double saldo;

    public Conta(Cliente titular) {
        this.titular = titular;

        this.NUMERO_DE_CONTAS++;
        this.numeroConta = this.NUMERO_DE_CONTAS;
    }

    public void saca(double valor) {}

    public void deposita(double valor) {}

    public void transfere(Conta destino, double valor) {}

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void atualiza(double taxa) {}
}
