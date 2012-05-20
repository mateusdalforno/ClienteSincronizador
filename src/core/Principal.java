package core;

import conexao.Conexao;
import conexao.Emissor;
import conexao.Receptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DateParser;

/**
 * Classe Principal que contém os métodos principais para o funcionamento do
 * programa de sincronização de relógios e que faz a sincronização com a hora
 * certa do servidor
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

    /**
     * Método construtor que inicia as entradas do programa cliente e inicializa
     * o relogio para o registro do tempo
     *
     * @param relogio
     */
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
     * devem ser feitas ao servidor
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
     * Método que calcula o intervalo de tempo entre o horario de envio da
     * mensagem e de retorno da mesma baseado no relógio local do cliente qe não
     * representa a hora oficial
     */
    public synchronized void descobrirTMin() {
        long horaClinte = relogio.getTime();
        System.out.println("\nA hora do cliente é: " + DateParser.simpleDateFormat("dd/MM/yyyy  HH:mm:ss:SSSS", horaClinte) + "\n");

        try {
            for (int i = 0; i < numPedidos; i++) {
                DatagramSocket clientSocket = new DatagramSocket();
                Emissor emissor = new Emissor(clientSocket);
                Receptor receptor = new Receptor(clientSocket);

                emissor.enviar("");
                long envio = relogio.getTime();
                System.out.println("Mensagem enviada em: " + DateParser.simpleDateFormat("dd/MM/yyyy  HH:mm:ss:SSSS", envio));

                receptor.receber();
                long recebimento = relogio.getTime();
                System.out.println("Mensagem recebida em : " + DateParser.simpleDateFormat("dd/MM/yyyy HH:mm:ss:SSSS", recebimento));

                long rttEstimado = recebimento - envio;
                System.out.println("Tempo de resposta do servidor: " + rttEstimado + "ms\n");
                System.out.println("-------------------------------------------------------");

                calculo.RTTMenor(rttEstimado);

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

    /**
     * Método que solicita a hora oficial do servidor e retorna com a hora
     * sincronizada e chama o calculo do rtt
     */
    public void solicitarHorario() {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            Emissor emissor = new Emissor(clientSocket);
            Receptor receptor = new Receptor(clientSocket);

            emissor.enviar("");
            long envio = relogio.getTime();
            System.out.println("------- Cálculo do RTT -------");
            System.out.println("Mensagem enviada em: " + DateParser.simpleDateFormat("dd/MM/yyyy HH:mm:ss:SSSS", envio));

            String horaServidor = receptor.receber();
            long recebimento = relogio.getTime();
            System.out.println("Mensagem recebida em: " + DateParser.simpleDateFormat("dd/MM/yyyy HH:mm:ss:SSSS", recebimento));

            long rttEstimando = recebimento - envio;
            System.out.println("Tempo de resposta do servidor: " + rttEstimando + "ms\n");

            horaServidor = horaServidor.trim();

            long horaS = Long.valueOf(horaServidor);
            long horaCerta = calculo.calcularHorario(horaS, rttEstimando);
            relogio.setTime(horaCerta);
            String hServidor = DateParser.simpleDateFormat("dd/MM/yyyy HH:mm:ss:SSSS", horaS);
            System.out.println("Cálculo = " + hServidor + " + " + rttEstimando + " / 2 - " + calculo.getTMin() + "\n");
            System.out.println("Hora sincronizada: ");
            System.out.println(DateParser.simpleDateFormat("dd/MM/yyyy HH:mm:ss:SSSS", relogio.getDate()));

            clientSocket.close();
        } catch (SocketException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
