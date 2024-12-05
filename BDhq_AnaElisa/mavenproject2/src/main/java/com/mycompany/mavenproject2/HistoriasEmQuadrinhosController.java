package com.mycompany.mavenproject2;
import com.mycompany.mavenproject2.HistoriasEmQuadrinhosBean;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class HistoriasEmQuadrinhosController {

    // Método para criar uma nova História em Quadrinhos
    public void createHistoria(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os dados da nova História em Quadrinhos: ");
        
        System.out.print("Titulo: ");
        String titulo = input.nextLine();
        
        System.out.print("Volume: ");
        int volume = input.nextInt();
        input.nextLine();
        
        System.out.print("Data de Lancamento (YYYY-MM-DD): ");
        String data_lancamento = input.nextLine();
        input.nextLine(); 
        
        new ColecoesController().listarColecoes(db);
        System.out.print("ID da Colecao: ");
        int idcolecao = input.nextInt();
        
        new AutoresController().listarAutores(db);
        System.out.print("ID do Autor: ");
        int idautor = input.nextInt();
        
        new IlustradoresController().listarIlustradores(db);
        System.out.print("ID do Ilustrador: ");
        int idilustrador = input.nextInt();
        
        new EditorasController().listarEditora(db);       
        System.out.print("ID da Editora: ");
        int ideditora = input.nextInt();
        
        System.out.print("\n");
        
        
        // Criar a instância de HistoriasEmQuadrinhosBean e converter para Document
        HistoriasEmQuadrinhosBean hq = new HistoriasEmQuadrinhosBean(0, titulo, volume, data_lancamento,
                                                                     idcolecao, idautor, idilustrador, ideditora);
        MongoCollection<Document> HistoriaemQuadrinhosCollection = db.getCollection("hqs");
        Document autorDoc = hq.toDocument();
        
        // Inserir no MongoDB
        HistoriaemQuadrinhosCollection.insertOne(autorDoc);
        System.out.println("HQ cadastrada com sucesso!");
    }

    // Método para listar todas as Histórias em Quadrinhos
    public void listarHistorias(MongoDatabase db) {
        MongoCollection<Document> historiasemquadrinhosCollection = db.getCollection("historiasemquadrinhos");

        if (historiasemquadrinhosCollection.countDocuments() == 0) {
            System.out.println("Nenhuma hq encontrado.");
        } else {
            System.out.println("Lista de HQs:");
            for (Document doc : historiasemquadrinhosCollection.find()) {
                HistoriasEmQuadrinhosBean autor = HistoriasEmQuadrinhosBean.fromDocument(doc); // Converte o documento para objeto AutoresBean
                System.out.println(autor.toString()); // Exibe os dados do autor
            }
        }
    }

    // Método para remover uma História em Quadrinhos
    public void removerHistoria(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarHistorias(db);
        System.out.println("Informe o ID da Historia em Quadrinhos a remover: ");
        
        int n = input.nextInt();
        input.nextLine();  // Consumir a quebra de linha pendente
        
        // Chamar o método de modelo para remover no MongoDB
        HistoriasEmQuadrinhosModel.remove(n, db);
        System.out.println("Historia em Quadrinhos removida com sucesso!");
    }

    // Método para alterar uma História em Quadrinhos existente
    public void alterarHistoria(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarHistorias(db);
        
        System.out.println("Informe o ID da Historia em Quadrinhos a alterar: ");
        int n = input.nextInt();
        input.nextLine();  // Consumir a quebra de linha pendente
        
        System.out.print("Titulo: ");
        String titulo = input.nextLine();
        
        System.out.print("Volume: ");
        int volume = input.nextInt();
        input.nextLine(); 
        
        System.out.print("Data de Lancamento (YYYY-MM-DD): ");
        String data_lancamento = input.nextLine();
        input.nextLine(); 
        
        System.out.print("ID da Colecao: ");
        int idcolecao = input.nextInt();
        
        System.out.print("ID do Autor: ");
        int idautor = input.nextInt();
        
        System.out.print("ID do Ilustrador: ");
        int idilustrador = input.nextInt();
        
        System.out.print("ID da Editora: ");
        int ideditora = input.nextInt();
        
        System.out.print("\n");
        
        // Criar a instância de HistoriasEmQuadrinhosBean com o ID fornecido
        HistoriasEmQuadrinhosBean hq = new HistoriasEmQuadrinhosBean(n, titulo, volume, data_lancamento,
                                                                     idcolecao, idautor, idilustrador, ideditora);
        // Chamar o método de modelo para alterar no MongoDB
        HistoriasEmQuadrinhosModel.alterar(hq, db);
        System.out.println("Historia em Quadrinhos alterada com sucesso!");
    }
}
