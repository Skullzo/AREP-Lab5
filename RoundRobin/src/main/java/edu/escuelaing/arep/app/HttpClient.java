package edu.escuelaing.arep.app;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
/**
 * Clase encargada de realizar la conexion HTTP con el cliente del programa.
 * @author  Alejandro Toro Daza
 * @version 1.0.  (25 de Febrero del 2021) 
 */
public class HttpClient {
    private String[] ports = {":8001",":8002",":8003"};
    private int nServer = 0;
    private String url = "http://192.168.99.100";
    /**
     * Metodo encargado de realizar una peticion GET al LogService.
     * @return String Cadena que contiene la peticion GET realizado.
     * @throws UnirestException Excepcion que se arroja si no se encuentra ninguna peticion.
     */
    public String getMessage() throws UnirestException {
        HttpResponse<String> apiResponse = Unirest.get(url+ports[nServer]+"/messages").asString();
        System.out.println("Petición GET de "+url+ports[nServer]);
        return apiResponse.getBody();
    }
    /**
     * Metodo encargado de realizar una peticion POST al LogService.
     * @param message Mensaje ingresado por el usuario que es asimismo ingresado a la base de datos.
     * @return String Cadena que contiene la peticion POST que se realizo.
     * @throws UnirestException Excepcion que se arroja si no se encuentra ningun mensaje.
     */
    public String postMessage(String message) throws UnirestException {
        HttpResponse<String> apiResponse = Unirest.post(url+ports[nServer]+"/messages")
                .body(message)
                .asString();
        System.out.println("Petición POST de "+url+ports[nServer]);
        return apiResponse.getBody();
    }
    /**
     * Metodo encargado de cambiar el valor de la variable nServer para cambiar entre los tres puertos disponibles del LogService.
     */
    public void changeNumberServer(){
        nServer=(nServer + 1) % ports.length;
    }
}