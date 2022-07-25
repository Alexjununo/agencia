import Entities.Agencia;
import Entities.Cliente;
import Handlers.Menus;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Agencia agencia = new Agencia();

        // TODO: retirar metodo criarCliente abaixo no final
        criarCliente(agencia);

        Menus.exibirMenu(agencia);
    }

    public static void criarCliente (Agencia agenciaTeste) {
        Cliente clienteTeste = new Cliente("Alex", "Pereira", "12345");

        agenciaTeste.setNumeroAG(12345);
        Map<String, Cliente> mapaDeClientes = agenciaTeste.getClientes();

        String CPF = clienteTeste.getCpf();
        mapaDeClientes.put(CPF, clienteTeste);

        agenciaTeste.setClientes(mapaDeClientes);
    }
}