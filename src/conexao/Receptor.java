package conexao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Classe receptor para recebimento das mensagens do servidor
 *
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 * @version 1.0
 */
public class Receptor {

    private DatagramSocket clientSocket;

    /**
     * Método construtor da classe Receptor que recebe como parâmetro um socket
     * cliente que estabelece uma comunicação com o servidor
     *
     * @param clientSocket
     */
    public Receptor(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Método responsável por reber as respostas do servidor em forma de um
     * pacote de dados, datagramas
     *
     * @return a string de resposta do servidor
     * @throws IOException
     */
    public String receber() throws IOException {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        return new String(receivePacket.getData());
    }

}
