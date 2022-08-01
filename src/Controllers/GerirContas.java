package Controllers;

import java.util.Map;
import java.util.Scanner;

import Entities.Agencia;
import Entities.Cliente;
import Entities.Conta;
import Entities.ContaCorrente;
import Entities.ContaPoupanca;

public class GerirContas {
    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado! Deve cadastrar o cliente antes de criar uma conta";
    private static final String CONTA_NAO_ENCONTRADA = "Conta não encontrada";
    private static final String OPCAO_INVALIDA = "Opção invalida!";

    static int controle;
    static Scanner read;
    static Agencia agencia;

    public GerirContas(Agencia agencia, Scanner read) {
        GerirContas.agencia = agencia;
        GerirContas.read = read;
    }

    public void cadastrarConta() {
        Map<String, Cliente> clientes = agencia.getClientes();

        if (clientes == null) {
            System.out.println("Não há clientes cadastrados! Deve cadastrar clientes antes de criar uma conta");

            return;
        }

        System.out.println("Informe o CPF de um cliente: ");
        String cpf = read.next();

        Cliente cliente = clientes.get(cpf);

        if (cliente == null) {
            System.out.println(CLIENTE_NAO_ENCONTRADO);

            return;
        }

        do {
            System.out.println("Informe o tipo de Conta: ");
            System.out.println("1 - Conta Corrente ");
            System.out.println("2 - Conta Poupança ");
            System.out.println("3 - Voltar ");

            controle = read.nextInt();

            int numeroConta;
            double saldoInicial;
            Map<Integer, Conta> mapaDeContasCliente = cliente.getContas();
            Map<Integer, Conta> mapaDeContasAgencia = agencia.getContas();

            switch (controle) {
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
                    if (controle != 3) {
                        System.out.println(OPCAO_INVALIDA);
                    }
                    break;
            }
        } while (controle != 3);
    }

    public void atualizarConta() {
        System.out.println("Informe o número da conta: ");
        int numeroConta = read.nextInt();

        Map<Integer, Conta> contas = agencia.getContas();

        Conta conta = contas.get(numeroConta);

        if (conta == null) {
            System.out.println(CONTA_NAO_ENCONTRADA);

            return;
        }

        System.out.println("Informe o novo saldo da conta: ");
        double novoSaldo = read.nextDouble();

        conta.deposita(novoSaldo);
    }

    public void listarContas() {
        Map<Integer, Conta> contas = agencia.getContas();

        if (contas == null) {
            System.out.println(CONTA_NAO_ENCONTRADA);

            return;
        }

        for (Conta conta : contas.values()) {
            System.out.println("##########################################");
            System.out.println("Número da conta: " + conta.getNumeroConta());
            System.out.println("Tipo de conta: " + conta.getClass().getSimpleName());
            System.out.println("Saldo: " + conta.getSaldo());
            System.out.println("##########################################");
        }

        System.out.println("Pressione qualquer tecla para voltar!");
        read.next();
    }

    public void listarUmaConta() {
        Map<Integer, Conta> contas = agencia.getContas();

        if (contas == null) {
            System.out.println(CONTA_NAO_ENCONTRADA);

            return;
        }

        System.out.println("Informe o número da conta: ");
        int numeroConta = read.nextInt();

        Conta conta = contas.get(numeroConta);

        if (conta == null) {
            System.out.println(CONTA_NAO_ENCONTRADA);

            return;
        }

        System.out.println("##########################################");
        System.out.println("Número da conta: " + conta.getNumeroConta());
        System.out.println("Tipo da conta: " + conta.getClass());
        System.out.println("Saldo: " + conta.getSaldo());
        System.out.println("##########################################");

        System.out.println("Pressione qualquer tecla para voltar!");
        read.next();
    }

    public void excluirConta() {
        Map<Integer, Conta> contas = agencia.getContas();

        System.out.println("Informe o número da conta: ");
        int numeroConta = read.nextInt();

        Conta conta = contas.get(numeroConta);

        if (conta == null) {
            System.out.println(CONTA_NAO_ENCONTRADA);

            return;
        }

        System.out.println("Deseja realmente excluir conta? (1 - Sim / 2 - Não)");
        controle = read.nextInt();

        if (controle == 1) {
            Cliente titular = conta.getTitular();
            Map<Integer, Conta> mapaDeContasCliente = titular.getContas();

            if (mapaDeContasCliente.size() == 1) {
                Map<String, Cliente> clientes = agencia.getClientes();

                clientes.remove(titular.getCpf());
            } else {
                mapaDeContasCliente.remove(numeroConta);
            }

            contas.remove(numeroConta);
            System.out.println("Conta excluída com sucesso!!");
        } else {
            System.out.println("Conta no excluida!!");
        }
    }
}
