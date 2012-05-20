package core;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Bruno
 */
public class Receptor {

    private DatagramSocket clientSocket;

    public Receptor(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public String receber() throws IOException {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        return new String(receivePacket.getData());
    }

}
