package edu.escuelaing.arep.app.model;
import java.util.Date;
/**
 * Clase encargada de tener control sobre todos los mensajes ignresados por el usuario.
 * @author  Alejandro Toro Daza
 * @version 1.0.  (25 de Febrero del 2021) 
 */
public class Message {
    private String info;
    private Date date;
    /**
     * Metodo contructor de la clase Message.
     * @param info Cadena que contiene la informacion que se desea almacenar.
     */
    public Message(String info){
        this.info=info;
        this.date = new Date();
    }
    /**
     * Metodo contructor de la clase Message.
     * @param info Cadena que contiene la informacion que se desea almacenar.
     * @param date Fecha en la que el mensaje fue realizado.
     */
    public Message(String info,Date date){
        this.info=info;
        this.date = date;
    }
    /**
     * Metodo encargado de obtener toda la informacion del mensaje.
     * @return Cadena que contiene toda la informacion del mensaje.
     */
    public String getInfo() {
        return info;
    }
    /**
     * Metodo encargado de realizar la respectiva actualizacion del mensaje.
     * @param info Cadena que contiene la informacion del mensaje.
     */
    public void setInfo(String info) {
        this.info = info;
    }
    /**
     * Metodo encargado de obtener la fecha del mensaje.
     * @return Cadena que contiene la informacion del mensaje.
     */
    public Date getDate() {
        return date;
    }
    /**
     * Metodo encargado de realizar la respectiva actualizacion de la fecha del mensaje.
     * @param date Cadena que contiene la fecha que tiene el mensaje.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}