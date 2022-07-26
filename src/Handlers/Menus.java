package Handlers;

import Entities.*;

import java.util.Map;
import java.util.Scanner;

public class Menus {
    public static void exibirMenu(Agencia agencia) {
        int controle;

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
        int controleGerirContas;

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
                    // TODO: try/catch
                    cadastrarConta(agencia);
                    break;
                case 2:
                    // TODO: try/catch
                    atualizarConta(agencia);
                    break;
                case 3:
                    // TODO: try/catch
                    listarContas(agencia);
                    break;
                case 4:
                    listarUmaConta(agencia);
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
        int controleConta;
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

    public static void atualizarConta(Agencia agencia) {
        Scanner read = new Scanner(System.in);

        System.out.println("Informe o numero da conta: ");
        int numeroConta = read.nextInt();

        Map<Integer, Conta> contas = agencia.getContas();

        Conta conta = contas.get(numeroConta);

        if (conta == null) {
            System.out.println("Conta nao encontrada");
            return;
        }

        System.out.println("Informe o novo saldo da conta: ");
        double novoSaldo = read.nextDouble();

        conta.deposita(novoSaldo);
    }

    public static void listarContas(Agencia agencia) {
        Scanner read = new Scanner(System.in);

        Map<Integer, Conta> contas = agencia.getContas();

        if (contas == null) {
            return;
        }

        for (Integer key : contas.keySet()) {
            Conta value = contas.get(key);

            System.out.println("##########################################");
            System.out.println("Numero da conta: " + value.getNumeroConta());
            System.out.println("Tipo de conta: " + value.getClass());
            System.out.println("Saldo: " + value.getSaldo());
            System.out.println("##########################################");
        }

        System.out.println("Pressiona qualquer tecla para voltar! ");
        String sair = read.next();
    }

    public static void listarUmaConta(Agencia agencia) {
        Scanner read = new Scanner(System.in);

        Map<Integer, Conta> contas = agencia.getContas();

        if (contas == null) {
            return;
        }

        System.out.println("Informe o numero da conta: ");
        int numeroConta = read.nextInt();

        Conta conta = contas.get(numeroConta);

        if (conta ==  null) {
            System.out.println("Conta nao encontrada!!");
            return;
        }

        System.out.println("Numero da conta: " + conta.getNumeroConta());
        System.out.println("Tipo da conta: " + conta.getClass());
        System.out.println("Saldo: " + conta.getSaldo());

        System.out.println("Pressiona qualquer tecla para voltar! ");
        String sair = read.next();
    }
}