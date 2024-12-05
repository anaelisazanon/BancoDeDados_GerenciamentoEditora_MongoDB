package com.mycompany.mavenproject2;
import com.mycompany.mavenproject2.EditorasBean;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.HashSet;

public class EditorasModel {

    // Método para criar uma editora no MongoDB
    public static void createEditora(EditorasBean editora, MongoDatabase db) {
        try {
            // Obter a coleção "editoras" do MongoDB
            MongoCollection<Document> editorasCollection = db.getCollection("editoras");

            // Converter EditorasBean para Document e inserir
            Document editoraDoc = editora.toDocument();
            editorasCollection.insertOne(editoraDoc);
            System.out.println("Editora inserida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir editora: " + e.getMessage());
        }
    }

    // Método para listar todas as editoras no MongoDB
    public static HashSet<EditorasBean> listAll(MongoDatabase db) {
        HashSet<EditorasBean> list = new HashSet<>();
        try {
            // Obter a coleção "editoras" do MongoDB
            MongoCollection<Document> editorasCollection = db.getCollection("editoras");

            // Iterar pelos documentos e converter para EditorasBean
            for (Document doc : editorasCollection.find()) {
                EditorasBean editora = EditorasBean.fromDocument(doc);
                list.add(editora);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar editoras: " + e.getMessage());
        }
        return list;
    }

    // Método para remover uma editora no MongoDB
    public static void remove(int id, MongoDatabase db) {
        try {
            // Obter a coleção "editoras" do MongoDB
            MongoCollection<Document> editorasCollection = db.getCollection("editoras");

            // Remover editora com base no ID
            long deletedCount = editorasCollection.deleteOne(Filters.eq("ideditora", id)).getDeletedCount();
            if (deletedCount > 0) {
                System.out.println("Editora removida com sucesso!");
            } else {
                System.out.println("Nenhuma editora encontrada com esse ID.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover editora: " + e.getMessage());
        }
    }

    // Método para alterar uma editora no MongoDB
    public static void alterar(EditorasBean editora, MongoDatabase db) {
        try {
            // Obter a coleção "editoras" do MongoDB
            MongoCollection<Document> editorasCollection = db.getCollection("editoras");

            // Atualizar editora com base no ID
            long modifiedCount = editorasCollection.updateOne(
                Filters.eq("ideditora", editora.getIdEditora()),
                Updates.combine(
                    Updates.set("cnpj_editora", editora.getCnpjEditora()),
                    Updates.set("nome_editora", editora.getNomeEditora()),
                    Updates.set("telefone_editora", editora.getTelefoneEditora()),
                    Updates.set("cep", editora.getCep()),
                    Updates.set("rua", editora.getRua()),
                    Updates.set("numero", editora.getNumero())
                )
            ).getModifiedCount();

            if (modifiedCount > 0) {
                System.out.println("Editora alterada com sucesso!");
            } else {
                System.out.println("Nenhuma editora encontrada com esse ID.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar editora: " + e.getMessage());
        }
    }
}
