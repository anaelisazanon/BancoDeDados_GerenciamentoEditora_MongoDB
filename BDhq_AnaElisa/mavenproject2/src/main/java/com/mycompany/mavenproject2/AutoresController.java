package com.mycompany.mavenproject2;
import com.mycompany.mavenproject2.AutoresBean;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Scanner;


public class AutoresController {
    public void createAutor(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para criar um novo Autor: ");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Telefone: ");
        String telefone = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();

        // Criar o autor e converter para Document
        AutoresBean mb = new AutoresBean(0, nome, telefone, email);
        MongoCollection<Document> autoresCollection = db.getCollection("autores");
        Document autorDoc = mb.toDocument();
        
        // Inserir no MongoDB
        autoresCollection.insertOne(autorDoc);
        System.out.println("Autor cadastrado com sucesso!");
    }

    // Método para listar todos os autores no MongoDB
    public void listarAutores(MongoDatabase db) {
        MongoCollection<Document> autoresCollection = db.getCollection("autores");

        if (autoresCollection.countDocuments() == 0) {
            System.out.println("Nenhum autor encontrado.");
        } else {
            System.out.println("Lista de Autores:");
            for (Document doc : autoresCollection.find()) {
                AutoresBean autor = AutoresBean.fromDocument(doc); // Converte o documento para objeto AutoresBean
                System.out.println(autor.toString()); // Exibe os dados do autor
            }
        }
    }


    // Método para remover um autor do MongoDB
    public void removerAutor(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarAutores(db);
        System.out.println("Informe o ID do autor a remover: ");
        
        int n = input.nextInt();
        input.nextLine();  // Consumir a quebra de linha pendente
        
        MongoCollection<Document> autoresCollection = db.getCollection("autores");
        Document filtro = new Document("idautor", n);
        
        // Remover autor
        autoresCollection.deleteOne(filtro);
        System.out.println("Autor removido com sucesso!");
    }

    // Método para alterar um autor no MongoDB
    public void alterarAutores(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarAutores(db);
        
        System.out.println("Informe o ID do autor a alterar: ");
        int n = input.nextInt();
        input.nextLine();  // Consumir a quebra de linha pendente
        
        System.out.print("Nome: ");
        String nome_autor = input.nextLine();
        
        System.out.print("Telefone: ");
        String telefone_autor = input.nextLine();
        
        System.out.print("Email: ");
        String email_autor = input.nextLine();
        
        // Criar o autor atualizado
        AutoresBean ab = new AutoresBean(n, nome_autor, telefone_autor, email_autor);
        
        MongoCollection<Document> autoresCollection = db.getCollection("autores");
        Document filtro = new Document("idautor", n);
        Document novoValor = new Document("$set", ab.toDocument());
        
        // Atualizar o autor no MongoDB
        autoresCollection.updateOne(filtro, novoValor);
        System.out.println("Autor alterado com sucesso!");
    }
}