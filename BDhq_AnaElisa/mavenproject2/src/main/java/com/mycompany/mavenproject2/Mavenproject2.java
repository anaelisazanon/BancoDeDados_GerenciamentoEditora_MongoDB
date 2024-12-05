/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;
import com.mongodb.client.MongoDatabase;
import java.util.Scanner;


/**
 *
 * @author anael
 */
public class Mavenproject2 {

    public static void main(String[] args) {
        // Criar conexão com MongoDB
        Conexao conexao = new Conexao();
        MongoDatabase db = conexao.getDatabase();
        

        int opcao=0;
        do {
            opcao = menu();
            try {
                switch (opcao) {
                    case 1: // Cadastrar Editora
                        new EditorasController().createEditora(db);
                        break;
                    case 2: // Listar Editoras
                        new EditorasController().listarEditora(db);
                        break;
                    case 3: // Atualizar Editora
                        new EditorasController().alterarEditora(db);
                        break;
                    case 4: // Remover Editora
                        new EditorasController().removerEditora(db);
                        break;
                    case 5: // Cadastrar Autor
                        new AutoresController().createAutor(db);
                        break;
                    case 6: // Listar Autores
                        new AutoresController().listarAutores(db);
                        break;
                    case 7: // Atualizar Autor
                        new AutoresController().alterarAutores(db);
                        break;
                    case 8: // Remover Autor
                        new AutoresController().removerAutor(db);
                        break;
                    case 9: // Cadastrar Ilustrador
                        new IlustradoresController().createIlustrador(db);
                        break;
                    case 10: // Listar Ilustradores
                        new IlustradoresController().listarIlustradores(db);
                        break;
                    case 11: // Atualizar Ilustrador
                        new IlustradoresController().alterarIlustrador(db);
                        break;
                    case 12: // Remover Ilustrador
                        new IlustradoresController().removerIlustrador(db);
                        break;
                    case 13: // Cadastrar Coleção
                        new ColecoesController().createColecao(db);
                        break;
                    case 14: // Listar Coleções
                        new ColecoesController().listarColecoes(db);
                        break;
                    case 15: // Cadastrar HQ
                        new HistoriasEmQuadrinhosController().createHistoria(db);
                        break;
                    case 16: // Listar HQs
                        new HistoriasEmQuadrinhosController().listarHistorias(db);
                        break;
                    case 17: // Alterar HQs
                        new HistoriasEmQuadrinhosController().alterarHistoria(db);
                        break;
                    default:
                        System.out.println("Saindo do sistema.");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        } while (opcao >= 1 && opcao <= 18);

        // Fechar a conexão com MongoDB
        System.out.println("Encerrando conexao com o banco de dados...");
        conexao.closeConnection();
        
    }

    private static int menu() {
        System.out.println("\nEscolha uma opcao:");
        System.out.println("1 - Cadastrar Editora");
        System.out.println("2 - Listar Editoras");
        System.out.println("3 - Atualizar Editora");
        System.out.println("4 - Remover Editora");
        System.out.println("5 - Cadastrar Autor");
        System.out.println("6 - Listar Autores");
        System.out.println("7 - Atualizar Autor");
        System.out.println("8 - Remover Autor");
        System.out.println("9 - Cadastrar Ilustrador");
        System.out.println("10 - Listar Ilustradores");
        System.out.println("11 - Atualizar Ilustrador");
        System.out.println("12 - Remover Ilustrador");
        System.out.println("13 - Cadastrar Colecao");
        System.out.println("14 - Listar Colecoes");
        System.out.println("15 - Cadastrar HQ");
        System.out.println("16 - Listar HQs");
        System.out.println("17 - Alterar HQs");
        System.out.println("18 - Relatorio HQs por editora");
        System.out.println("----");
        System.out.println("Digite qualquer outro valor para sair.");
        System.out.print("Opcao: ");


        Scanner input = new Scanner(System.in);
        return input.nextInt();
        

    }
}
