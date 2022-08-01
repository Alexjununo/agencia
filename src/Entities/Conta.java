package Entities;

import Exceptions.SaldoInsuficienteException;
import Interfaces.Tributavel;

public abstract class Conta implements Tributavel {
    private static int NUMERO_DE_CONTAS = 0;

    private int numeroConta;
    private Cliente titular;
    protected double saldo;

    public Conta(Cliente titular) {
        this.titular = titular;

        Conta.NUMERO_DE_CONTAS++;
        this.numeroConta = Conta.NUMERO_DE_CONTAS;
    }

    public void saca(double valor) {
        double saldoConta = this.saldo;
        saldoConta -= valor;
        
        if (valor < 0) {
            throw new IllegalArgumentException("Valor invalido para o saque");
        }

        if (saldoConta < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque");
        }

        this.saldo = saldoConta;
    }

    public void deposita(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Valor invalido para o deposito");
        }

        this.saldo += valor;
    }

    public void transfere(Conta destino, double valor) {
        this.saca(valor);
        destino.deposita(valor);
    }

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
