package core;

/**
 * Classe Calculo que faz o calculo para setar o relogio do cliente de acordo
 * com a hora certa do servidor
 *
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 * @version 1.0
 */
public class Calculo {

    private long TMin;

    /**
     * Método construtor que inicializa o Tmin como um long
     */
    public Calculo() {
        TMin = Long.MAX_VALUE;
    }

    /**
     * Método que pega o menor valor do RTT para fazer o cálculo
     *
     * @param num
     */
    public void RTTMenor(long num) {
        if (num < TMin) {
            TMin = num;
        }
    }

    /**
     * Método que implementa o algorítmo de Christian para setar o relógio do
     * cliente com a hora certa
     *
     * @param dataServidor
     * @param rtt
     * @return o valor do tempo certo
     */
    public long calcularHorario(long dataServidor, long rtt) {
        return dataServidor + rtt / 2 - TMin;
    }
}
