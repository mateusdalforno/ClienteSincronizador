/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiago
 */
public class Conexao {

    public void faz() {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] m = "teste".getBytes();
            InetAddress host = InetAddress.getByName("localhost");
            int port = 5588;
            DatagramPacket request = new DatagramPacket(m, port);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
