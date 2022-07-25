package Entities;

public class ContaCorrente extends Conta {
    private double TAXA = 0.10;

    public ContaCorrente(Cliente titular) {
        super(titular);
    }

    @Override
    public double calculaTributos() {
        return this.saldo * TAXA;
    }
}
