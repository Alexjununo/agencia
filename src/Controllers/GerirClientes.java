package Controllers;

import java.util.Map;
import java.util.Scanner;

import Entities.Agencia;
import Entities.Cliente;
import Entities.Conta;

public class GerirClientes {
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
            System.out.println("Cliente ja cadastrado!");
            
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
            System.out.println("Cliente nao encontrado!");
            
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
            System.out.println("Nao ha clientes cadastrados!");
            
            return;
        }

        for (String cpf : clientes.keySet()) {
            Cliente cliente = clientes.get(cpf);
            
            System.out.println("##########################################");
            System.out.println("CPF: " + cpf);
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Sobrenome: " + cliente.getSobrenome());
            System.out.println("Total de contas: " + cliente.getContas().size());
            System.out.println("##########################################");
        }

        
        System.out.println("Pressiona qualquer tecla para voltar! ");
        read.next();
    }

    public void listarUmCliente() {
        Map<String, Cliente> clientes = agencia.getClientes();

        System.out.println("Informe o CPF: ");
        String cpf = read.next();
        
        Cliente cliente = clientes.get(cpf);
        
        if (cliente == null) {
            System.out.println("Cliente nao encontrado!");
            
            return;
        }
        
        System.out.println("##########################################");
        System.out.println("CPF: " + cpf);
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Sobrenome: " + cliente.getSobrenome());
        System.out.println("Total de contas: " + cliente.getContas().size());
        System.out.println("##########################################");
        
        System.out.println("Pressiona qualquer tecla para voltar! ");
        read.next();
    }

    public void excluirCliente() {
        Map<String, Cliente> clientes = agencia.getClientes();
        
        System.out.println("Informe o CPF: ");
        String cpf = read.next();
        
        Cliente cliente = clientes.get(cpf);
        
        if (cliente == null) {
            System.out.println("Cliente nao encontrado!");
            
            return;
        }

        System.out.println("Deseja realmente excluir o cliente? (1 - Sim / 2 - Nao)");
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
            System.out.println("Cliente nao excluido!");
        }        
    }
}
