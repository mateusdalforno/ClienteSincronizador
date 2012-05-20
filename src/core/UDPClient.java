package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Classe UDPClient que estabele o protocolo de comunicação com o servidor. No
 * caso a comunicação funciona sobre o protocolo UDP (User Datagram Protocol, ou
 * protocolo de datagrama de usuários)
 *
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 * @version 1.0
 */
public class UDPClient {

    public static void main(String args[]) throws Exception {
        String arg = "5";
        //for (int i = 0; i < Integer.valueOf(args[0]); i++) {
        for (int i = 0; i < Integer.valueOf(arg); i++) {
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("10.1.1.15");
            byte[] sendData = new byte[0];
            byte[] receiveData = new byte[0];
            //String sentence = inFromUser.readLine();
            //sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
            clientSocket.close();
        }
    }
}
