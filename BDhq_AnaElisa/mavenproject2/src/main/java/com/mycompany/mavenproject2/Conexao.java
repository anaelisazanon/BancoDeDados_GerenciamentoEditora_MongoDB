package com.mycompany.mavenproject2;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Conexao {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public Conexao() {
        String url = "mongodb://localhost:27017";
        try {
            // Criando o cliente MongoDB
            mongoClient = MongoClients.create(url);
            database = mongoClient.getDatabase("hq"); 
            System.out.println("Conectado ao banco de dados MongoDB!");
           
           

            // Verificando se a conexão foi bem-sucedida
            MongoIterable<String> collections = database.listCollectionNames();
            if (collections.first() != null) {
                System.out.println("Conexao bem-sucedida ao banco de dados 'hq'.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados MongoDB.");
            System.exit(1);  // Encerra o programa em caso de erro na conexão
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void closeConnection() {
        try {
            if (mongoClient != null) {
                mongoClient.close();  // Fechando a conexão
                System.out.println("Conexao MongoDB fechada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
