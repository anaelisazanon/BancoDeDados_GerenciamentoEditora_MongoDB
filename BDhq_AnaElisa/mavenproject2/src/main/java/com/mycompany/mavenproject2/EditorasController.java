package com.mycompany.mavenproject2;
import com.mycompany.mavenproject2.EditorasBean;
import com.mongodb.client.MongoDatabase;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class EditorasController {

    public void createEditora(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para registrar uma nova Editora: ");
        
        System.out.print("CNPJ: ");
        String cnpj_editora = input.nextLine();
        
        System.out.print("Nome: ");
        String nome_editora = input.nextLine();
        
        System.out.print("Telefone: ");
        String telefone_editora = input.nextLine();
        
        System.out.print("CEP: ");
        String cep = input.nextLine();
       
        System.out.print("Rua: ");
        String rua = input.nextLine();
        
        System.out.print("Numero: ");
        String numero = input.nextLine();
        
        System.out.print("\n");
        
        // Criar a instância de EditorasBean sem ID
        EditorasBean ab = new EditorasBean(0, cnpj_editora, nome_editora, telefone_editora, cep, rua, numero);
        EditorasModel.createEditora(ab, db);
        System.out.println("Editora criada com sucesso!");
    }

    public void listarEditora(MongoDatabase db) {
        HashSet<EditorasBean> all = EditorasModel.listAll(db);
        Iterator<EditorasBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
            System.out.print("\n");
        }
    }

    public void removerEditora(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarEditora(db);
        System.out.println("Informe o ID da editora a remover: ");
        
        int n = input.nextInt();
        input.nextLine();  // Consumir a quebra de linha pendente
        
        EditorasModel.remove(n, db);
        System.out.println("Editora removida com sucesso!");
    }

    public void alterarEditora(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarEditora(db);
        
        System.out.println("Informe o ID da editora a alterar: ");
        int n = input.nextInt();
        input.nextLine();  // Consumir a quebra de linha pendente
        
        System.out.print("CNPJ: ");
        String cnpj_editora = input.nextLine();
        
        System.out.print("Nome: ");
        String nome_editora = input.nextLine();
        
        System.out.print("Telefone: ");
        String telefone_editora = input.nextLine();
        
        System.out.print("CEP: ");
        String cep = input.nextLine();
        
        System.out.print("Rua: ");
        String rua = input.nextLine();
        
        System.out.print("Numero: ");
        String numero = input.nextLine();
        
        System.out.print("\n");
        
        // Criar a instância de EditorasBean com o ID fornecido
        EditorasBean ab = new EditorasBean(n, cnpj_editora, nome_editora, telefone_editora, cep, rua, numero);
        EditorasModel.alterar(ab, db);
        System.out.println("Editora alterada com sucesso!");
    }
}
