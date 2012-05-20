package core;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author thiago
 */
public class Conexao {

    private InetAddress inetIp;
    private String ip;
    public static String IP_SERVIDOR = "";

    public Conexao(InetAddress inetIp) {
        this.inetIp = inetIp;
        this.ip = inetIp.getHostAddress();
    }

    public Conexao(String ip) throws UnknownHostException {
        this.ip = ip;
        this.inetIp = InetAddress.getByName(ip);
    }

    public static void setIP_SERVIDOR(String ip) {
        IP_SERVIDOR = ip;
    }

    public InetAddress getConexaoServidor() {
        return this.inetIp;
    }
}
