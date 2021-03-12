package edu.escuelaing.arep.app.persistence;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.escuelaing.arep.app.model.Message;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Date;
/**
 * Clase encargada de realziar la respectiva conexion con la base de datos en MongoDB.
 * @author  Alejandro Toro Daza
 * @version 1.0.  (25 de Febrero del 2021) 
 */
public class DBConnection {
    MongoClientURI uri;
    MongoClient mongoClient;
    /**
     * Metodo constructor de la clase DBConnection.
     */
    public DBConnection() {
        uri = new MongoClientURI("mongodb://admin:admin@0.0.0.0:27017/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=Database&authMechanism=SCRAM-SHA-1&3t.uriVersion=3");
        mongoClient = new MongoClient(uri);
    }
    /**
     * Metodo encargado de insertar el mensaje en la base de datos.
     * @param message Parametro que indica los datos a insertar en la base de datos.
     */
    public void insertMessage(Message message){
        mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("Database");
        MongoCollection<Document> collection =database.getCollection("Message");
        Document document=new Document();
        document.put("info",message.getInfo());
        document.put("date",message.getDate());
        collection.insertOne(document);
    }
    /**
     * Metodo encargado de realizar una consulta a la base de datos y obtener los datos ingresados.
     * @return Retorna una lista que contiene los mensajes almacenados en la base de datos.
     */
    public ArrayList<Message> getMessages() {
        MongoDatabase database = mongoClient.getDatabase("Database");
        MongoCollection<Document> collection = database.getCollection("Message");
        FindIterable findIterable = collection.find();
        ArrayList<Document> documents = new ArrayList<Document>();
        ArrayList<Message> messages = new ArrayList<Message>();
        findIterable.into(documents);
        for (Document doc : documents) {
            if (doc.get("info") != null && doc.get("date") != null) {
                messages.add(new Message((String) doc.get("info"), (Date) doc.get("date")));
            }
        }
        return messages;
    }
}
