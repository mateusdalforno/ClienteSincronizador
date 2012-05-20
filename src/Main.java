
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
        long tmin = 97823453623l;
        Relogio relogio = new Relogio();
        
        try {
            Conexao.setIP_SERVIDOR("10.1.1.15");
            DatagramSocket clientSocket = new DatagramSocket();
            Emissor emissor = new Emissor(clientSocket);
            Receptor receptor = new Receptor(clientSocket);
            
            emissor.enviar("");
            long envio = relogio.getTime();
            System.out.println("Data de envio: " + envio);
            receptor.receber();
            long recebimento = relogio.getTime();
            System.out.println("Data de recebimento: " + recebimento);
            long rttEstimando = recebimento - envio;
            
            if (rttEstimando < tmin) {
                tmin = rttEstimando;
            }
            System.out.println("Tempo de resposta do servidor: " + tmin);
            
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
