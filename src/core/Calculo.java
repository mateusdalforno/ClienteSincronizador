package core;

/**
 *
 * @author Bruno
 */
public class Calculo {

    private long TMin;

    public Calculo() {
        TMin = Long.MAX_VALUE;
    }

    public void RTTMenor(long num) {
        if (num < TMin) {
            TMin = num;
        }
    }
}