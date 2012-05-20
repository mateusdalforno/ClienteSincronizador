package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Principal que contém os métodos principais para o funcionamento do
 * programa de sincronização de relógios
 *
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 * @version 1.0
 */
public class Principal {

    private BufferedReader br;
    private InputStreamReader isr;
    private Relogio relogio;
    private int numPedidos;
    private Calculo calculo;

    public Principal(Relogio relogio) {
        isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);
        this.relogio = relogio;
        this.numPedidos = 0;
        calculo = new Calculo();
    }

    /**
     * Método responsável por inicializar a configuração para a comunicação com
     * o servidor
     */
    public void inicializaConfiguração() {
        System.out.println("Insira o número do ip do servidor:");
        try {
            String ip = br.readLine();
            Conexao.setIP_SERVIDOR(ip);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método utilizado para o usuário informar o número de requisições que
     * devem ser feias ao servidor para co cáculo do tempo mínimo
     */
    public void inserirNumeroPedido() {
        boolean numInteiro = false;
        System.out.println("Insira a quantidade de pedidos que você deseja enviar ao servidor:");
        try {
            String linha = br.readLine();
            while (!numInteiro) {
                try {
                    numPedidos = Integer.valueOf(linha);
                    if (numPedidos <= 0) {
                        System.out.println("Digite um número maior que 0!");
                    } else {
                        numInteiro = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Caractere inválido! Digite um número.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por pegar o menor tempo dentre as requisições enviadas
     * ao servidor
     */
    public void descobrirTMin() {
        try {
            for (int i = 0; i < numPedidos; i++) {
                DatagramSocket clientSocket = new DatagramSocket();
                Emissor emissor = new Emissor(clientSocket);
                Receptor receptor = new Receptor(clientSocket);

                emissor.enviar("");
                long envio = relogio.getTime();
                System.out.println("Data de envio: " + envio);

                receptor.receber();
                long recebimento = relogio.getTime();
                System.out.println("Data de recebimento: " + recebimento);

                long rttEstimando = recebimento - envio;
                System.out.println("Tempo de resposta do servidor: " + rttEstimando);

                calculo.RTTMenor(rttEstimando);

                clientSocket.close();
            }
        } catch (SocketException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void solicitaHorario() {
    }
}
