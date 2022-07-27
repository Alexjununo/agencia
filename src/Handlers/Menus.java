package Handlers;

import Entities.*;

import java.util.Scanner;

import Controllers.GerirContas;

public class Menus {
    static int controle;
    static Scanner read = new Scanner(System.in);
    static Agencia agencia;

    public Menus(Agencia agencia) {
        Menus.agencia = agencia;
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
        GerirContas gerirContas = new GerirContas(agencia, read);

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
                    // TODO: try/catch
                    gerirContas.cadastrarConta();
                    break;
                case 2:
                    // TODO: try/catch
                    gerirContas.atualizarConta();
                    break;
                case 3:
                    // TODO: try/catch
                    gerirContas.listarContas();
                    break;
                case 4:
                    // TODO: try/catch
                    gerirContas.listarUmaConta();
                    break;
                case 5:
                    // TODO: try/catch
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

    public static void exibirMenuGerirClientes() {}

    public static void exibirMenuGerirTransacoes() {}

    public static void exibirMenuGerirIRPF() {}
}