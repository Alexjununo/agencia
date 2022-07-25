package Handlers;

import Entities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menus {
    public static void exibirMenu(Agencia agencia) {
        int controle = 0;

        Scanner read = new Scanner(System.in);

        do {
            System.out.println("Seleciona uma opcao: ");
            System.out.println("1 - Gerir contas");
            System.out.println("2 - Gerir clientes");
            System.out.println("3 - Gerir transacoes");
            System.out.println("4 - Gerir IRPF");
            System.out.println("5 - Sair");

            controle = read.nextInt();

            switch (controle) {
                case 1:
                    exibirMenuGerirContas(agencia);
                    break;
                case 2:
                    // TODO: desenvolver menu
                    System.out.println("Aguardando");
                    break;
                case 3:
                    // TODO: desenvolver menu
                    System.out.println("Aguardando");
                    break;
                case 4:
                    // TODO: desenvolver menu
                    System.out.println("Aguardando");
                    break;
                default:
                    if (controle != 5) {
                        System.out.println("Opcao invalida");
                    }
                    break;
            }
        } while (controle != 5);
    }

    public static void exibirMenuGerirContas(Agencia agencia) {
        int controleGerirContas = 0;

        Scanner read = new Scanner(System.in);

        do {
            System.out.println("# Gerir Contas #");
            System.out.println("Seleciona uma opcao: ");
            System.out.println("1 - Cadastrar conta");
            System.out.println("2 - Atualizar conta");
            System.out.println("3 - Listar contas");
            System.out.println("4 - Listar uma conta");
            System.out.println("5 - Excluir conta");
            System.out.println("6 - Voltar");

            controleGerirContas = read.nextInt();

            switch (controleGerirContas) {
                case 1:
                    cadastrarConta(agencia);
                    break;
                case 2:
                    // TODO: desenvolver regra
                    System.out.println("Aguardando");
                    break;
                case 3:
                    // TODO: desenvolver regra
                    System.out.println("Aguardando");
                    break;
                case 4:
                    // TODO: desenvolver regra
                    System.out.println("Aguardando");
                    break;
                case 5:
                    // TODO: desenvolver regra
                    System.out.println("Aguardando");
                    break;
                default:
                    if (controleGerirContas != 6) {
                        System.out.println("Opcao invalida");
                    }
                    break;
            }
        } while (controleGerirContas != 6);
    }

    public static void cadastrarConta(Agencia agencia) {
        Scanner read = new Scanner(System.in);

        String cpf;
        int controleConta = 0;
        double saldoInicial;
        int numeroConta;

        System.out.println("Informe o CPF de um cliente: ");
        cpf = read.next();

        Map<String, Cliente> clientes = agencia.getClientes();

        if (clientes == null) {
            System.out.println("Nao ha clientes cadastrados");
            return;
        }

        Cliente cliente = clientes.get(cpf);

        if (cliente == null) {
            System.out.println("Cliente nao encontrado! Deve cadastrar o cliente antes de criar uma conta");
           return;
        }

        do {
            System.out.println("Informe o tipo de Conta: ");
            System.out.println("1 - Conta corrente ");
            System.out.println("2 - Conta Poupanca ");
            System.out.println("3 - Voltar ");

            controleConta = read.nextInt();

            Map<Integer, Conta> mapaDeContasCliente = cliente.getContas();
            Map<Integer, Conta> mapaDeContasAgencia = agencia.getContas();

            switch (controleConta) {
                case 1:
                    ContaCorrente contaCorrente = new ContaCorrente(cliente);
                    System.out.println("Informe o saldo inicial: ");
                    saldoInicial = read.nextDouble();

                    contaCorrente.deposita(saldoInicial);
                    numeroConta = contaCorrente.getNumeroConta();
                    mapaDeContasCliente.put(numeroConta, contaCorrente);
                    mapaDeContasAgencia.put(numeroConta, contaCorrente);
                    cliente.setContas(mapaDeContasCliente);
                    agencia.setContas(mapaDeContasAgencia);

                    System.out.println("Conta criada com sucesso!!");
                    break;
                case 2:
                    ContaPoupanca contaPoupanca = new ContaPoupanca(cliente);
                    System.out.println("Informe o saldo inicial: ");
                    saldoInicial = read.nextDouble();

                    contaPoupanca.deposita(saldoInicial);
                    numeroConta = contaPoupanca.getNumeroConta();
                    mapaDeContasCliente.put(numeroConta, contaPoupanca);
                    mapaDeContasAgencia.put(numeroConta, contaPoupanca);
                    cliente.setContas(mapaDeContasCliente);
                    agencia.setContas(mapaDeContasAgencia);

                    System.out.println("Conta criada com sucesso!!");
                    break;
                default:
                    if (controleConta != 3) {
                        System.out.println("Opcao invalida");
                    }
                    break;
            }
        } while (controleConta != 3);
    }
}
