package com.mycompany.mavenproject2;
import com.mycompany.mavenproject2.IlustradoresBean;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class IlustradoresController {

    // Método para criar um novo Ilustrador
    public void createIlustrador(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para registrar um novo Ilustrador: ");
        
        System.out.print("Nome: ");
        String nome_ilustrador = input.nextLine();
        
        System.out.print("Telefone: ");
        String telefone_ilustrador = input.nextLine();
        
        System.out.print("Email: ");
        String email_ilustrador = input.nextLine();
        
        System.out.print("\n");
        
        
        
        // Criar a instância de IlustradoresBean sem ID
        IlustradoresBean ib = new IlustradoresBean(0, nome_ilustrador, telefone_ilustrador, email_ilustrador);
        
        // Acessar a coleção 'ilustradores' no banco de dados MongoDB
        MongoCollection<Document> ilustradoresCollection = db.getCollection("ilustradores");
        
        // Converter o objeto IlustradoresBean para um Document
        Document ilustradorDoc = ib.toDocument();
        
        // Inserir no MongoDB
        ilustradoresCollection.insertOne(ilustradorDoc);
        
 
        System.out.println("Ilustrador criado com sucesso!");
    }

    // Método para listar todos os Ilustradores no MongoDB
  public void listarIlustradores(MongoDatabase db) {
      MongoCollection<Document> ilustradoresCollection = db.getCollection("ilustradores");

      if (ilustradoresCollection.countDocuments() == 0) {
          System.out.println("Nenhum ilustrador encontrado.");
      } else {
          System.out.println("Lista de Ilustradores:");
          for (Document doc : ilustradoresCollection.find()) {
              // Convertendo o documento para um objeto IlustradoresBean
              IlustradoresBean ilustrador = IlustradoresBean.fromDocument(doc);
              System.out.println(ilustrador.toString());
          }
      }
  }


    // Método para remover um Ilustrador do MongoDB
    public void removerIlustrador(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarIlustradores(db);  // Método para listar os ilustradores antes de remover
        System.out.println("Informe o ID do ilustrador a remover: ");

        int id = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha pendente

        MongoCollection<Document> ilustradoresCollection = db.getCollection("ilustradores");
        Document filtro = new Document("idilustrador", id);  // Considerando que o campo "id" é usado para identificar o ilustrador

        // Remover o ilustrador
        ilustradoresCollection.deleteOne(filtro);
        System.out.println("Ilustrador removido com sucesso!");
    }

    // Método para alterar um Ilustrador no MongoDB
    public void alterarIlustrador(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarIlustradores(db);  // Listar os ilustradores antes de alterar

        System.out.println("Informe o ID do ilustrador a alterar: ");
        int id = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha pendente

        System.out.print("Nome: ");
        String nomeIlustrador = input.nextLine();

        System.out.print("Telefone: ");
        String telefoneIlustrador = input.nextLine();

        System.out.print("Email: ");
        String emailIlustrador = input.nextLine();

        // Criar o ilustrador atualizado
        IlustradoresBean ilustrador = new IlustradoresBean(id, nomeIlustrador, telefoneIlustrador, emailIlustrador);

        MongoCollection<Document> ilustradoresCollection = db.getCollection("ilustradores");
        Document filtro = new Document("id", id);  // Considerando que o campo "id" é usado para identificar o ilustrador
        Document novoValor = new Document("$set", ilustrador.toDocument());  // O campo $set é usado para atualizar os valores

        // Atualizar o ilustrador no MongoDB
        ilustradoresCollection.updateOne(filtro, novoValor);
        System.out.println("Ilustrador alterado com sucesso!");
    }

}
