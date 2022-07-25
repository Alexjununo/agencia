package Entities;

import java.util.HashMap;
import java.util.Map;

public class Agencia {
    private Map<Integer, Conta> contas = new HashMap<>();
    private Map<String, Cliente> clientes = new HashMap<>();
    private int numeroAG;

    public Map<Integer, Conta> getContas() {
        return contas;
    }

    public void setContas(Map<Integer, Conta> contas) {
        this.contas = contas;
    }

    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Map<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    public int getNumeroAG() {
        return numeroAG;
    }

    public void setNumeroAG(int numeroAG) {
        this.numeroAG = numeroAG;
    }
}
