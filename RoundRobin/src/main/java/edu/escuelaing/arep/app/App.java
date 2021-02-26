package edu.escuelaing.arep.app;
import static spark.Spark.*;
/**
 * Clase principal encargada de llevar a cabo la ejecucion de todo el programa.
 * @author  Alejandro Toro Daza
 * @version 1.0.  (25 de Febrero del 2021) 
 */
public class App {
    /**
     * Metodo principal encargado de recibir peticiones GET y POST.
     * @param args Argumentos que entran a la clase principal.
     */
    public static void main( String[] args ) {
        port(getPort());
        staticFileLocation("/static");
        HttpClient client = new HttpClient();
        get("/", (req, res) -> {
            res.redirect( "index.html");
            return null;
        });
        get("/results", (req, res) -> {
            res.status(200);
            res.type("application/json");
            String resp = client.getMessage();
            client.changeNumberServer();
            return resp;
        });
        post("/results", (req, res) -> {
            client.postMessage(req.body());
            client.changeNumberServer();
            return "";
        });
    }
    /**
     * Metodo encargado de ejecutar el programa de manera local con un puerto predeterminado.
     * @return Puerto asignado por defecto para ejecutar el programa de forma local, que es 4567.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}