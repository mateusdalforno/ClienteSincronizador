package core;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Relogio que pega o horário do envio de uma requisição do cliente
 *
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 * @version 1.0
 */
public class Relogio extends Thread {

    private Date hora;
    private boolean execucao;

    /**
     * Método construtor da classe Relogio que incia a execução do relógio
     */
    public Relogio() {
        execucao = true;
        this.start();
    }

    /**
     * Método responsável executar o relógio
     */
    @Override
    public void run() {
        this.iniciaContagem();
    }

    /**
     * Método responsável por iniciar a contagem do relógio
     */
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

    /**
     * Método que retorna a hora do cliente
     *
     * @return a hora corrente
     */
    public Date getDate() {
        return this.hora;
    }

    /**
     * Método que retorna a hora e todo os atributos do tempo referenciado como
     * minuts, segundos, milisegundos
     *
     * @return
     */
    public long getTime() {
        return this.hora.getTime();
    }

    /**
     * Método que finaliza a execução do relógio
     */
    public void shutdown() {
        execucao = false;
    }
}
