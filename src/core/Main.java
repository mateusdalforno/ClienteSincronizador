package core;

/**
 *
 * @author Mateus
 */
public class Main {

    public static void main(String[] args) {
        Relogio relogio = new Relogio();
        Principal p = new Principal(relogio);
        p.inicializaConfiguração();
        p.inserirNumeroPedido();
        p.descobrirTMin();
        relogio.shutdown();
    }
}