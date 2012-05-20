
import core.Conexao;
import java.io.IOException;
import java.net.*;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
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
        byte[] sendData = new byte[0];
        sendData = msg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, con.getConexaoServidor(), 9876);
        clientSocket.send(sendPacket);
    }

}
