package conexao;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Classe Conexao que inicia estabelece os atributas para a conexão com o
 * servidor
 *
 * @author Thiago Krug
 * @author Bruno Vicelli
 * @author Mateus Dal Forno
 * @version 1.0
 */
public class Conexao {

    private InetAddress inetIp;
    private String ip;
    public static String IP_SERVIDOR = "";

    /**
     * Método construtor da classe Conexao que estabelece as configurações do
     * servidor o qual se esta estabelecendo uma conexão
     *
     * @param inetIp
     */
    public Conexao(InetAddress inetIp) {
        this.inetIp = inetIp;
        this.ip = inetIp.getHostAddress();
    }

    /**
     * Método construtor da classe Conexao que recebe como parâmetro o ip e
     * lança uma execessão caso não ache o ip
     *
     * @param ip
     * @throws UnknownHostException
     */
    public Conexao(String ip) throws UnknownHostException {
        this.ip = ip;
        this.inetIp = InetAddress.getByName(ip);
    }

    /**
     * Método set para inserir o valor do ip
     *
     * @param ip
     */
    public static void setIP_SERVIDOR(String ip) {
        IP_SERVIDOR = ip;
    }

    /**
     * Método para retorno das configurações do servidor
     *
     * @return inetIp
     */
    public InetAddress getConexaoServidor() {
        return this.inetIp;
    }
}
