package Handlers;

import Entities.*;

import java.util.Map;
import java.util.Scanner;

import Controllers.GerenciadorIRPF;
import Controllers.GerirClientes;
import Controllers.GerirContas;
import Controllers.GerirTransacoes;

public class Menus {
    private static final String OPÇÃO_INVALIDA = "Opção invalida!";

    static int controle;
    static Scanner read = new Scanner(System.in);
    static Agencia agencia;
    static GerirContas gerirContas;
    static GerirClientes gerirClientes;
    static GerirTransacoes gerirTransacoes;
    static GerenciadorIRPF gerenciadorIrpf;

    public Menus() {
        Menus.agencia = new Agencia();
        Menus.gerirContas = new GerirContas(agencia, read);
        Menus.gerirClientes = new GerirClientes(agencia, read);
        Menus.gerirTransacoes = new GerirTransacoes(agencia, read);
        Menus.gerenciadorIrpf = new GerenciadorIRPF();
    }

    public void exibirMenu() {
        do {
            System.out.println("##########################################");
            System.out.println("############### MENU PRINCIPAL ##############");
            System.out.println("##########################################");
            System.out.println("1 - Gerir Contas");
            System.out.println("2 - Gerir Clientes");
            System.out.println("3 - Gerir Transações");
            System.out.println("4 - Gerir IRPF");
            System.out.println("5 - Sair");
            System.out.println("##########################################");
            System.out.println("Informe a opção desejada: ");
            controle = read.nextInt();

            switch (controle) {
                case 1:
                    exibirMenuGerirContas();
                    break;
                case 2:
                    exibirMenuGerirClientes();
                    break;
                case 3:
                    exibirMenuGerirTransacoes();
                    break;
                case 4:
                    gerirIRPF();
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println(OPÇÃO_INVALIDA);
                    break;
            }
        } while (controle != 5);

        read.close();
    }

    public static void exibirMenuGerirContas() {
        do {
            System.out.println("##########################################");
            System.out.println("############### GERIR CONTAS ##############");
            System.out.println("##########################################");
            System.out.println("1 - Cadastrar conta");
            System.out.println("2 - Atualizar conta");
            System.out.println("3 - Listar contas");
            System.out.println("4 - Listar uma conta");
            System.out.println("5 - Excluir conta");
            System.out.println("6 - Voltar");
            System.out.println("##########################################");
            System.out.println("Informe a opção desejada: ");
            controle = read.nextInt();

            switch (controle) {
                case 1:
                    gerirContas.cadastrarConta();
                    break;
                case 2:
                    gerirContas.atualizarConta();
                    break;
                case 3:
                    gerirContas.listarContas();
                    break;
                case 4:
                    gerirContas.listarUmaConta();
                    break;
                case 5:
                    gerirContas.excluirConta();
                    break;
                default:
                    if (controle != 6) {
                        System.out.println(OPÇÃO_INVALIDA);
                    }
                    break;
            }
        } while (controle != 6);
    }

    public static void exibirMenuGerirClientes() {
        do {
            System.out.println("##########################################");
            System.out.println("############### GERIR CLIENTES ##############");
            System.out.println("##########################################");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Atualizar cliente");
            System.out.println("3 - Listar clientes");
            System.out.println("4 - Listar um cliente");
            System.out.println("5 - Excluir cliente");
            System.out.println("6 - Voltar");
            System.out.println("##########################################");
            System.out.println("Informe a opção desejada: ");
            controle = read.nextInt();

            switch (controle) {
                case 1:
                    gerirClientes.cadastrarCliente();
                    break;
                case 2:
                    gerirClientes.atualizarCliente();
                    break;
                case 3:
                    gerirClientes.listarClientes();
                    break;
                case 4:
                    gerirClientes.listarUmCliente();
                    break;
                case 5:
                    gerirClientes.excluirCliente();
                    break;
                default:
                    if (controle != 6) {
                        System.out.println(OPÇÃO_INVALIDA);
                    }
                    break;
            }
        } while (controle != 6);
    }

    public static void exibirMenuGerirTransacoes() {
        do {
            System.out.println("##########################################");
            System.out.println("############### GERIR TRANSAÇÕES ##############");
            System.out.println("##########################################");
            System.out.println("1 - Saque");
            System.out.println("2 - Depósito");
            System.out.println("3 - Transferência");
            System.out.println("4 - Voltar");
            System.out.println("##########################################");
            System.out.println("Informe a opção desejada: ");
            controle = read.nextInt();

            switch (controle) {
                case 1:
                    gerirTransacoes.sacar();
                    break;
                case 2:
                    gerirTransacoes.depositar();
                    break;
                case 3:
                    gerirTransacoes.transferir();
                    break;
                default:
                    if (controle != 4) {
                        System.out.println(OPÇÃO_INVALIDA);
                    }
                    break;
            }
        } while (controle != 4);
    }

    public static void gerirIRPF() {
        System.out.println("Informe o CPF do cliente: ");
        String cpf = read.next();

        Map<String, Cliente> clientes = agencia.getClientes();

        if (clientes == null) {
            System.out.println("Não existem clientes cadastrados");
        }

        Cliente cliente = clientes.get(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado");
        }

        Map<Integer, Conta> contas = cliente.getContas();

        if (contas == null) {
            System.out.println("Não existem contas cadastradas");
        }

        for (Conta conta : contas.values()) {
            gerenciadorIrpf.adicionaTributavel(conta);
        }

        System.out.println("O valor do IRPF e de: " + gerenciadorIrpf.getTotal());

        System.out.println("Pressione qualquer tecla para voltar!");
        read.next();
    }
}