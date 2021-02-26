package edu.escuelaing.arep.app;
import com.google.gson.Gson;
import edu.escuelaing.arep.app.model.Message;
import edu.escuelaing.arep.app.persistence.DBConnection;
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
        DBConnection connection = new DBConnection();
        get("/messages", (req, res) -> {
            res.status(200);
            res.type("application/json");
            return new Gson().toJson(connection.getMessages());
        });
        post("/messages", (req, res) -> {
            Message a = new Message(req.body());
            connection.insertMessage(a);
            return null;
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
        return 4561;
    }
}