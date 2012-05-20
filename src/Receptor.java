
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Bruno
 */
public class Receptor {

    private DatagramSocket clientSocket;

    public Receptor(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void receber() throws IOException {
        byte[] receiveData = new byte[0];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + modifiedSentence);
    }

}
