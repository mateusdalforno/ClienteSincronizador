
import core.Conexao;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Mateus
 */
public class Main {

    public static void main(String[] args) {
        long rtt = System.currentTimeMillis();
        Relogio relogio = new Relogio();
        
        try {
            Conexao.setIP_SERVIDOR("10.1.1.15");
            DatagramSocket clientSocket = new DatagramSocket();
            Emissor emissor = new Emissor(clientSocket);
            Receptor receptor = new Receptor(clientSocket);
            
            emissor.enviar("");
            Date envio = relogio.getDate();
            System.out.println(envio.getTime());
            receptor.receber();
            Date recebimento = relogio.getDate();
            System.out.println(recebimento.getTime());
            long rttEstimando = recebimento.getTime() - envio.getTime();
            
            if (rttEstimando < rtt) {
                rtt = rttEstimando;
            }
            System.out.println(rtt);
            
            clientSocket.close();
        } catch (SocketException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        relogio.shutdown();
    }
}
