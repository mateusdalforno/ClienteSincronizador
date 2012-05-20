package core;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiago
 */
public class Relogio extends Thread {

    private Date hora;
    private boolean execucao;

    public Relogio() {
        execucao = true;
        this.start();
    }

    @Override
    public void run() {
        this.iniciaContagem();
    }

    public void iniciaContagem() {
        hora = new Date(System.currentTimeMillis());
        while (execucao) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Relogio.class.getName()).log(Level.SEVERE, null, ex);
            }
            hora.setTime(System.currentTimeMillis());
        }
    }

    public Date getDate() {
        return this.hora;
    }

    public long getTime() {
        return this.hora.getTime();
    }

    public void shutdown() {
        execucao = false;
    }
}
