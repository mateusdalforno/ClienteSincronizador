package core;

/**
 *
 * @author Mateus
 */
public class Main {

    public static void main(String[] args) {
        Relogio relogio = new Relogio();
        Principal p = new Principal(relogio);
        relogio.setTime(relogio.getTime() + 10000000);
        p.inicializaConfiguração();
        p.inserirNumeroPedido();
        p.descobrirTMin();
        p.solicitarHorario();
        relogio.shutdown();
    }
}
