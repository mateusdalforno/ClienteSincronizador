package conexao;

import conexao.Conexao;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Classe emissor para envio das mensagens ao servidor
 *
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 * @version 1.0
 */
public class Emissor {

    private DatagramSocket clientSocket;

    /**
     * Método construtor da classe Emissor que recebe como parametro um
     * clientSocket
     *
     * @param clientSocket
     */
    public Emissor(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Método de envio das requisições do cliente para o servidor
     *
     * @param msg
     * @throws SocketException
     * @throws UnknownHostException
     * @throws IOException
     */
    public void enviar(String msg) throws SocketException, UnknownHostException, IOException {
        Conexao con = new Conexao(Conexao.IP_SERVIDOR);
        byte[] sendData = new byte[1024];
        sendData = msg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, con.getConexaoServidor(), 9876);
        clientSocket.send(sendPacket);
    }

}
