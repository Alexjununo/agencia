package Controllers;

import java.util.Map;
import java.util.Scanner;

import Entities.Agencia;
import Entities.Conta;

public class GerirTransacoes {
    /**
     *
     */
    private static final String CONTA_NAO_ENCONTRADA = "Conta nao encontrada!!";
    static Scanner read;
    static Agencia agencia;

    public GerirTransacoes(Agencia agencia, Scanner read) {
        GerirTransacoes.agencia = agencia;
        GerirTransacoes.read = read;
    }

    public void sacar() {
        System.out.println("Informe o número da conta: ");
        int numeroConta = read.nextInt();

        Map<Integer, Conta> contas = agencia.getContas();

        if (contas == null) {
            return;
        }

        Conta conta = contas.get(numeroConta);

        if (conta == null) {
            System.out.println(CONTA_NAO_ENCONTRADA);

            return;
        }

        System.out.println("Informe o valor a ser sacado: ");
        double valor = read.nextDouble();

        conta.saca(valor);

        System.out.println("Pressione qualquer tecla para voltar! ");
        read.next();
    }

    public void depositar() {
        System.out.println("Informe o número da conta: ");
        int numeroConta = read.nextInt();

        Map<Integer, Conta> contas = agencia.getContas();

        if (contas == null) {
            return;
        }

        Conta conta = contas.get(numeroConta);

        if (conta == null) {
            System.out.println(CONTA_NAO_ENCONTRADA);

            return;
        }

        System.out.println("Informe o valor a ser depositado: ");
        double valor = read.nextDouble();

        conta.deposita(valor);

        System.out.println("Pressione qualquer tecla para voltar! ");
        read.next();
    }

    public void transferir() {
        System.out.println("Informe o número da conta de origem: ");
        int numeroConta = read.nextInt();

        Map<Integer, Conta> contas = agencia.getContas();

        if (contas == null) {
            return;
        }

        Conta contaOrigem = contas.get(numeroConta);

        if (contaOrigem == null) {
            System.out.println(CONTA_NAO_ENCONTRADA);

            return;
        }

        System.out.println("Informe o número da conta de destino: ");
        numeroConta = read.nextInt();

        Conta contaDestino = contas.get(numeroConta);

        if (contaDestino == null) {
            System.out.println(CONTA_NAO_ENCONTRADA);

            return;
        }

        System.out.println("Informe o valor a ser transferido: ");
        double valor = read.nextDouble();

        contaOrigem.transfere(contaDestino, valor);

        System.out.println("Pressione qualquer tecla para voltar! ");
        read.next();
    }
}
