package Controllers;

import java.util.Map;
import java.util.Scanner;

import Entities.Agencia;
import Entities.Cliente;
import Entities.Conta;

public class GerirClientes {
    private static final String NAO_HA_CLIENTES_CADASTRADOS = "Não há clientes cadastrados!";
    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado!";
    private static final String CLIENTE_JA_CADASTRADO = "Cliente já cadastrado!";

    static int controle;
    static Scanner read;
    static Agencia agencia;

    public GerirClientes(Agencia agencia, Scanner read) {
        GerirClientes.agencia = agencia;
        GerirClientes.read = read;
    }

    public void cadastrarCliente() {
        Map<String, Cliente> clientes = agencia.getClientes();

        System.out.println("Informe o CPF: ");
        String cpf = read.next();

        if (clientes.containsKey(cpf)) {
            System.out.println(CLIENTE_JA_CADASTRADO);

            return;
        }

        System.out.println("Informe o nome: ");
        String nome = read.next();

        System.out.println("Informe o sobrenome: ");
        String sobrenome = read.next();

        Cliente cliente = new Cliente(nome, sobrenome, cpf);

        clientes.put(cpf, cliente);

        GerirContas gerirContas = new GerirContas(agencia, read);

        gerirContas.cadastrarConta();
    }

    public void atualizarCliente() {
        Map<String, Cliente> clientes = agencia.getClientes();

        System.out.println("Informe o CPF: ");
        String cpf = read.next();

        Cliente cliente = clientes.get(cpf);

        if (cliente == null) {
            System.out.println(CLIENTE_NAO_ENCONTRADO);

            return;
        }

        System.out.println("Informe o novo nome: ");
        String nome = read.next();

        System.out.println("Informe o novo sobrenome: ");
        String sobrenome = read.next();

        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
    }

    public void listarClientes() {
        Map<String, Cliente> clientes = agencia.getClientes();

        if (clientes == null) {
            System.out.println(NAO_HA_CLIENTES_CADASTRADOS);

            return;
        }

        for (Cliente cliente : clientes.values()) {
            System.out.println("##########################################");
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Sobrenome: " + cliente.getSobrenome());
            System.out.println("Total de contas: " + cliente.getContas().size());
            System.out.println("##########################################");
        }

        System.out.println("Pressione qualquer tecla para voltar! ");
        read.next();
    }

    public void listarUmCliente() {
        Map<String, Cliente> clientes = agencia.getClientes();

        System.out.println("Informe o CPF: ");
        String cpf = read.next();

        Cliente cliente = clientes.get(cpf);

        if (cliente == null) {
            System.out.println(CLIENTE_NAO_ENCONTRADO);

            return;
        }

        System.out.println("##########################################");
        System.out.println("CPF: " + cpf);
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Sobrenome: " + cliente.getSobrenome());
        System.out.println("Total de contas: " + cliente.getContas().size());
        System.out.println("##########################################");

        System.out.println("Pressione qualquer tecla para voltar! ");
        read.next();
    }

    public void excluirCliente() {
        Map<String, Cliente> clientes = agencia.getClientes();

        System.out.println("Informe o CPF: ");
        String cpf = read.next();

        Cliente cliente = clientes.get(cpf);

        if (cliente == null) {
            System.out.println(CLIENTE_NAO_ENCONTRADO);

            return;
        }

        System.out.println("Deseja realmente excluir o cliente? (1 - Sim / 2 - Não)");
        controle = read.nextInt();

        if (controle == 1) {
            Map<Integer, Conta> contas = agencia.getContas();
            Map<Integer, Conta> mapaDeContasCliente = cliente.getContas();

            if (mapaDeContasCliente != null) {
                for (Integer numeroConta : mapaDeContasCliente.keySet()) {
                    contas.remove(numeroConta);
                }
            }

            clientes.remove(cpf);
        } else {
            System.out.println("Cliente não excluido!");
        }
    }
}
