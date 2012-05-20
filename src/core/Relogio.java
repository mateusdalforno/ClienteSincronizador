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
        hora = new Date(System.currentTimeMillis());
        this.start();
    }

    @Override
    public void run() {
        while (execucao) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Relogio.class.getName()).log(Level.SEVERE, null, ex);
            }
            hora.setTime(hora.getTime()+1);
        }
    }

    public Date getDate() {
        return this.hora;
    }

    public long getTime() {
        return this.hora.getTime();
    }
    
    public void setTime(long time) {
        this.hora.setTime(time);
    }

    public void shutdown() {
        execucao = false;
    }
}
