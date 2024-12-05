import com.mycompany.mavenproject2.ColecoesBean;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoCursor;

import java.util.HashSet;

public class ColecoesModel {

    // Método para criar uma coleção no MongoDB
    public static void createColecao(ColecoesBean colecao, MongoDatabase db) {
        try {
            // Obter a coleção "colecoes" do MongoDB
            MongoCollection<Document> colecoesCollection = db.getCollection("colecoes");

            // Converter ColecoesBean para Document e inserir
            Document colecaoDoc = colecao.toDocument();
            colecoesCollection.insertOne(colecaoDoc);
            System.out.println("Coleção inserida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir coleção: " + e.getMessage());
        }
    }

    // Método para listar todas as coleções no MongoDB
    public static HashSet<ColecoesBean> listAll(MongoDatabase db) {
        HashSet<ColecoesBean> list = new HashSet<>();
        try {
            // Obter a coleção "colecoes" do MongoDB
            MongoCollection<Document> colecoesCollection = db.getCollection("colecoes");

            // Iterar pelos documentos e converter para ColecoesBean
            try (MongoCursor<Document> cursor = colecoesCollection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    ColecoesBean colecao = ColecoesBean.fromDocument(doc);
                    list.add(colecao);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar coleções: " + e.getMessage());
        }
        return list;
    }
}
