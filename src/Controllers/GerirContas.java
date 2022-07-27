package Controllers;

import java.util.Map;
import java.util.Scanner;

import Entities.Agencia;
import Entities.Cliente;
import Entities.Conta;
import Entities.ContaCorrente;
import Entities.ContaPoupanca;

public class GerirContas {
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
            System.out.println("Nao ha clientes cadastrados! Deve cadastrar clientes antes de criar uma conta");

            return;
        }

        System.out.println("Informe o CPF de um cliente: ");
        String cpf = read.next();


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
                        System.out.println("Opcao invalida");
                    }
                    break;
            }
        } while (controle != 3);
    }

    public void atualizarConta() {
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

    public void listarContas() {
        Map<Integer, Conta> contas = agencia.getContas();
        
        if (contas == null) {
            return;
        }

        for (Integer numeroConta : contas.keySet()) {
            Conta conta = contas.get(numeroConta);

            System.out.println("##########################################");
            System.out.println("Numero da conta: " + conta.getNumeroConta());
            System.out.println("Tipo de conta: " + conta.getClass().getSimpleName());
            System.out.println("Saldo: " + conta.getSaldo());
            System.out.println("##########################################");
        }

        System.out.println("Pressiona qualquer tecla para voltar! ");
        read.next();
    }

    public void listarUmaConta() {
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

        System.out.println("##########################################");
        System.out.println("Numero da conta: " + conta.getNumeroConta());
        System.out.println("Tipo da conta: " + conta.getClass());
        System.out.println("Saldo: " + conta.getSaldo());
        System.out.println("##########################################");

        System.out.println("Pressiona qualquer tecla para voltar! ");
        read.next();
    }

    public void excluirConta() {
        Map<Integer, Conta> contas = agencia.getContas();

        System.out.println("Informe o numero da conta: ");
        int numeroConta = read.nextInt();

        Conta conta = contas.get(numeroConta);

        if (conta == null) {
            System.out.println("Conta nao encontrada!!");

            return;
        }

        System.out.println("Deseja realmente exluir conta? (1 - Sim / 2 - Nao)");
        controle = read.nextInt();

        if (controle == 1) {
            Cliente titular = conta.getTitular();
            Map<Integer, Conta> mapaDeContasCliente = titular.getContas();

            if(mapaDeContasCliente.size() == 1) {
                Map<String, Cliente> clientes = agencia.getClientes();

                clientes.remove(titular.getCpf());
            } else {
                mapaDeContasCliente.remove(numeroConta);
            }

            contas.remove(numeroConta);
            System.out.println("Conta excluida com sucesso!!");
        } else {
            System.out.println("Conta nao excluida!!");
        }
    }
}
