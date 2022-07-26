package Entities;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente titular) {
        super(titular);
    }

    @Override
    public double calculaTributos() {
        return 0;
    }

    @Override
    public void atualiza(double taxa) {
        double valorPoupanca = this.saldo * taxa;

        this.saldo += valorPoupanca;
    }
}
