package com.mycompany.mavenproject2;

import org.bson.Document;

public class AutoresBean {
    private int idautor;
    private String nome_autor;
    private String telefone_autor;
    private String email_autor;

    // Construtor
    public AutoresBean(int idautor, String nome, String telefone, String email) {
        this.idautor = idautor;
        this.nome_autor = nome;
        this.telefone_autor = telefone;
        this.email_autor = email;
    }

    // Getters e Setters
    public int getIdAutor() { return idautor; }
    public void setIdAutor(int idautor) { this.idautor = idautor; }
    public String getNome() { return nome_autor; }
    public void setNome(String nome) { this.nome_autor = nome; }
    public String getTelefone() { return telefone_autor; }
    public void setTelefone(String telefone) { this.telefone_autor = telefone; }
    public String getEmail() { return email_autor; }
    public void setEmail(String email) { this.email_autor = email; }

    // Método toString
    @Override
    public String toString(){
        return "ID: " + idautor + ", Nome: " + nome_autor + ", Telefone: " + telefone_autor + ", Email: " + email_autor;
    }

    // Método para converter o bean para um Document do MongoDB
    public Document toDocument() {
        Document doc = new Document("idautor", this.idautor)
                        .append("nome", this.nome_autor)
                        .append("telefone", this.telefone_autor)
                        .append("email", this.email_autor);
        return doc;
    }

    // Método para criar um bean a partir de um Document do MongoDB
    public static AutoresBean fromDocument(Document doc) {
        int idautor = doc.getInteger("idautor");
        String nome = doc.getString("nome_autor");
        String telefone = doc.getString("telefone_autor");
        String email = doc.getString("email_autor");

        return new AutoresBean(idautor, nome, telefone, email);
    }
}
