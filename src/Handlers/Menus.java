package Handlers;

import Entities.*;

import java.util.Scanner;

import Controllers.GerirClientes;
import Controllers.GerirContas;

public class Menus {
    static int controle;
    static Scanner read = new Scanner(System.in);
    static Agencia agencia;
    static GerirContas gerirContas;
    static GerirClientes gerirClientes;

    public Menus(Agencia agencia) {
        Menus.agencia = agencia;
        Menus.gerirContas = new GerirContas(agencia, read);
        Menus.gerirClientes = new GerirClientes(agencia, read);
    }

    public void exibirMenu() {
        do {
            System.out.println("##########################################");
            System.out.println("############### MENU PRINCIPAL ##############");
            System.out.println("##########################################");
            System.out.println("1 - Gerir Contas");
            System.out.println("2 - Gerir Clientes");
            System.out.println("3 - Gerir Transacoes");
            System.out.println("4 - Gerir IRPF");
            System.out.println("5 - Sair");
            System.out.println("##########################################");
            System.out.println("Informe a opcao desejada: ");
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
                    exibirMenuGerirIRPF();
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
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
            System.out.println("Informe a opcao desejada: ");
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
                        System.out.println("Opcao invalida");
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
            System.out.println("Informe a opcao desejada: ");
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
                        System.out.println("Opcao invalida");
                    }
                    break;
            }
        } while (controle != 6);
    }

    public static void exibirMenuGerirTransacoes() {}

    public static void exibirMenuGerirIRPF() {}
}