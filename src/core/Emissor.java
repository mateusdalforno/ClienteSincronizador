package core;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Bruno
 */
public class Emissor {

    private DatagramSocket clientSocket;

    public Emissor(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void enviar(String msg) throws SocketException, UnknownHostException, IOException {
        Conexao con = new Conexao(Conexao.IP_SERVIDOR);
        byte[] sendData = new byte[1024];
        sendData = msg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, con.getConexaoServidor(), 9876);
        clientSocket.send(sendPacket);
    }

}
