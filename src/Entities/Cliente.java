package Entities;

import java.util.HashMap;
import java.util.Map;

public class Cliente {
    private String nome;
    private String sobrenome;
    private String cpf;
    private Map<Integer, Conta> contas = new HashMap<>();

    public Cliente(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public Map<Integer, Conta> getContas() {
        return contas;
    }

    public void setContas(Map<Integer, Conta> contas) {
        this.contas = contas;
    }
}
