import com.mycompany.mavenproject2.AutoresBean;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.HashSet;

public class AutoresModel {
    public static void createAutor(AutoresBean autor, MongoDatabase db) {
        try {
            // Obter a coleção "autores" do MongoDB
            MongoCollection<Document> autoresCollection = db.getCollection("autores");

            // Converter AutoresBean para Document e inserir
            Document autorDoc = autor.toDocument();
            autoresCollection.insertOne(autorDoc);
            System.out.println("Autor inserido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir autor: " + e.getMessage());
        }
    }

 
        // Método para listar todas as editoras no MongoDB
    public static HashSet<AutoresBean> listAllautores(MongoDatabase db) {
        HashSet<AutoresBean> list = new HashSet<>();
        try {
            // Obter a coleção "editoras" do MongoDB
            MongoCollection<Document> autoresCollection = db.getCollection("autores");

            // Iterar pelos documentos e converter para EditorasBean
            for (Document doc : autoresCollection.find()) {
                AutoresBean editora = AutoresBean.fromDocument(doc);
                list.add(editora);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar autores: " + e.getMessage());
        }
        return list;
    }


    // Método para remover um autor no MongoDB
    public static void remove(int id, MongoDatabase db) {
        try {
            // Obter a coleção "autores" do MongoDB
            MongoCollection<Document> autoresCollection = db.getCollection("autores");

            // Remover autor com base no ID
            long deletedCount = autoresCollection.deleteOne(Filters.eq("idautor", id)).getDeletedCount();
            if (deletedCount > 0) {
                System.out.println("Autor removido com sucesso!");
            } else {
                System.out.println("Nenhum autor encontrado com esse ID.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover autor: " + e.getMessage());
        }
    }

    // Método para alterar um autor no MongoDB
    public static void alterar(AutoresBean autor, MongoDatabase db) {
        try {
            // Obter a coleção "autores" do MongoDB
            MongoCollection<Document> autoresCollection = db.getCollection("autores");

            // Atualizar autor com base no ID
            long modifiedCount = autoresCollection.updateOne(
                Filters.eq("idautor", autor.getIdAutor()),
                Updates.combine(
                    Updates.set("nome", autor.getNome()),
                    Updates.set("telefone", autor.getTelefone()),
                    Updates.set("email", autor.getEmail())
                )
            ).getModifiedCount();

            if (modifiedCount > 0) {
                System.out.println("Autor alterado com sucesso!");
            } else {
                System.out.println("Nenhum autor encontrado com esse ID.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar autor: " + e.getMessage());
        }
    }
}
