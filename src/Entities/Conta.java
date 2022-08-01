package Entities;

import Exceptions.SaldoInsuficienteException;
import Interfaces.Tributavel;

public abstract class Conta implements Tributavel {
    private static final String SALDO_INSUFICIENTE_PARA_SAQUE = "Saldo insuficiente para saque";
    private static final String VALOR_INVALIDO_PARA_O_DEPOSITO = "Valor invalido para o deposito";

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
        try {
            double saldoConta = this.saldo;
            saldoConta -= valor;

            if (valor < 0) {
                throw new IllegalArgumentException(VALOR_INVALIDO_PARA_O_DEPOSITO);
            }

            if (saldoConta < 0) {
                throw new SaldoInsuficienteException(SALDO_INSUFICIENTE_PARA_SAQUE);
            }

            this.saldo = saldoConta;
        } catch (Exception error) {
            System.out.println(error.getMessage());

            return;
        }
    }

    public void deposita(double valor) {
        try {
            if (valor < 0) {
                throw new IllegalArgumentException(VALOR_INVALIDO_PARA_O_DEPOSITO);
            }

            this.saldo += valor;
        } catch (Exception error) {
            System.out.println(error.getMessage());

            return;
        }
    }

    public void transfere(Conta destino, double valor) {
        try {
            this.saca(valor);
            destino.deposita(valor);
        } catch (Exception error) {
            System.out.println(error.getMessage());

            return;
        }
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

    public void atualiza(double taxa) {
    }

    public double getSaldo() {
        return saldo;
    }
}
