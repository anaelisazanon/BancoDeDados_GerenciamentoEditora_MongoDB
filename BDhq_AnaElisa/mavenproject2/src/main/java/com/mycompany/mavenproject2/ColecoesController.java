package com.mycompany.mavenproject2;
import com.mycompany.mavenproject2.ColecoesBean;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class ColecoesController {

    // Método para criar uma coleção no MongoDB
    public void createColecao(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para criar uma nova Colecao: ");
        System.out.print("Nome da Colecao: ");
        String nomeColecao = input.nextLine();
        System.out.print("Data (YYYY-MM-DD): ");
        String data = input.nextLine();


        // Criar a coleção e converter para Document
        ColecoesBean colecao = new ColecoesBean(0, nomeColecao, data); // ID será gerado pelo MongoDB
        MongoCollection<Document> colecoesCollection = db.getCollection("colecoes");
        Document colecaoDoc = colecao.toDocument();

        // Inserir no MongoDB
        colecoesCollection.insertOne(colecaoDoc);
        System.out.println("Colecao cadastrada com sucesso!");
    }

    // Método para listar todas as coleções no MongoDB
    public void listarColecoes(MongoDatabase db) {
        MongoCollection<Document> colecoesCollection = db.getCollection("colecoes");

        if (colecoesCollection.countDocuments() == 0) {
            System.out.println("Nenhuma colecao encontrada.");
        } else {
            System.out.println("Lista de colecoes:");
            for (Document doc : colecoesCollection.find()) {
                ColecoesBean colecao = ColecoesBean.fromDocument(doc);
                System.out.println(colecao.toString());
            }
        }
    }

    // Método para remover uma coleção do MongoDB
    public void removerColecao(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarColecoes(db);
        System.out.println("Informe o ID da colecao a remover: ");

        int id = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha pendente

        MongoCollection<Document> colecoesCollection = db.getCollection("colecoes");
        Document filtro = new Document("idcolecao", id);

        // Remover a coleção
        colecoesCollection.deleteOne(filtro);
        System.out.println("Colecao removida com sucesso!");
    }

    // Método para alterar uma coleção no MongoDB
    public void alterarColecao(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarColecoes(db);

        System.out.println("Informe o ID da coleção a alterar: ");
        int id = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha pendente

        System.out.print("Nome da Coleção: ");
        String nomeColecao = input.nextLine();

        System.out.print("Data (YYYY-MM-DD): ");
        String data = input.nextLine();
   

        // Criar a coleção atualizada
        ColecoesBean colecao = new ColecoesBean(id, nomeColecao, data);

        MongoCollection<Document> colecoesCollection = db.getCollection("colecoes");
        Document filtro = new Document("idcolecao", id);
        Document novoValor = new Document("$set", colecao.toDocument());

        // Atualizar a coleção no MongoDB
        colecoesCollection.updateOne(filtro, novoValor);
        System.out.println("Coleção alterada com sucesso!");
    }
}
