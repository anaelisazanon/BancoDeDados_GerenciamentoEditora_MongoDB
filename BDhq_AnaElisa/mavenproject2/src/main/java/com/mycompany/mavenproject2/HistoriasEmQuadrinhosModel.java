package com.mycompany.mavenproject2;
import com.mycompany.mavenproject2.HistoriasEmQuadrinhosBean;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoCursor;

import java.util.HashSet;

public class HistoriasEmQuadrinhosModel {

    // Método para criar uma História em Quadrinhos no MongoDB
    public static void createHistoria(HistoriasEmQuadrinhosBean hq, MongoDatabase db) {
        try {
            // Obter a coleção "hqs" do MongoDB
            MongoCollection<Document> hqsCollection = db.getCollection("hqs");

            // Converter HistoriasEmQuadrinhosBean para Document e inserir
            Document hqDoc = hq.toDocument();
            hqsCollection.insertOne(hqDoc);
            System.out.println("História em Quadrinhos inserida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir História em Quadrinhos: " + e.getMessage());
        }
    }

    // Método para listar todas as Histórias em Quadrinhos no MongoDB
    public static HashSet<HistoriasEmQuadrinhosBean> listAll(MongoDatabase db) {
        HashSet<HistoriasEmQuadrinhosBean> list = new HashSet<>();
        try {
            // Obter a coleção "hqs" do MongoDB
            MongoCollection<Document> hqsCollection = db.getCollection("hqs");

            // Iterar pelos documentos e converter para HistoriasEmQuadrinhosBean
            try (MongoCursor<Document> cursor = hqsCollection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    HistoriasEmQuadrinhosBean hq = HistoriasEmQuadrinhosBean.fromDocument(doc);
                    list.add(hq);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar Histórias em Quadrinhos: " + e.getMessage());
        }
        return list;
    }

    // Método para remover uma História em Quadrinhos do MongoDB
    public static void remove(int id, MongoDatabase db) {
        try {
            // Obter a coleção "hqs" do MongoDB
            MongoCollection<Document> hqsCollection = db.getCollection("hqs");

            // Criar o filtro para localizar a HQ com o ID fornecido
            Document filter = new Document("id", id);
            hqsCollection.deleteOne(filter);
            System.out.println("História em Quadrinhos removida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover História em Quadrinhos: " + e.getMessage());
        }
    }

    // Método para alterar uma História em Quadrinhos no MongoDB
    public static void alterar(HistoriasEmQuadrinhosBean hq, MongoDatabase db) {
        try {
            // Obter a coleção "hqs" do MongoDB
            MongoCollection<Document> hqsCollection = db.getCollection("hqs");

            // Criar o filtro para localizar a HQ com o ID fornecido
            Document filter = new Document("id", hq.getIdHistoria());

            // Converter o bean para um documento e definir o filtro para atualizar
            Document updatedDoc = hq.toDocument();
            hqsCollection.replaceOne(filter, updatedDoc);
            System.out.println("História em Quadrinhos alterada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao alterar História em Quadrinhos: " + e.getMessage());
        }
    }
}
