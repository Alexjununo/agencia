package Controllers;

import Interfaces.Tributavel;

public class GerenciadorIRPF {
    private double total;

    public void adicionaTributavel(Tributavel t) {
        this.total += t.calculaTributos();
    }

    public double getTotal() {
        return this.total;
    }
}
