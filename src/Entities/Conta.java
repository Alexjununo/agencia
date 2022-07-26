package Entities;

import Helpers.SaldoInsuficienteException;
import Interfaces.Tributavel;

public abstract class Conta implements Tributavel {
    private static int NUMERO_DE_CONTAS = 0;

    private int numeroConta;
    private Cliente titular;
    protected double saldo;

    public Conta(Cliente titular) {
        this.titular = titular;

        this.NUMERO_DE_CONTAS++;
        this.numeroConta = this.NUMERO_DE_CONTAS;
    }

    public void saca(double valor) {
        if (this.saldo <= 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente!");
        }

        this.saldo -= valor;
    }

    public void deposita(double valor) {
        this.saldo = valor;
    }

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

    public double getSaldo() {
        return saldo;
    }
}
