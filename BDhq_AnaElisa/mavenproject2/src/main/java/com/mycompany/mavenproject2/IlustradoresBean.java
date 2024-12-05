package com.mycompany.mavenproject2;
import org.bson.Document;

public class IlustradoresBean {
    private int idilustrador;
    private String nome_ilustrador;
    private String telefone_ilustrador;
    private String email_ilustrador;

    // Construtor com todos os parâmetros
    public IlustradoresBean(int idilustrador, String nome, String telefone, String email) {
        this.idilustrador = idilustrador;
        this.nome_ilustrador = nome;
        this.telefone_ilustrador = telefone;
        this.email_ilustrador = email;
    }
    
    // Construtor vazio (opcional)
    public IlustradoresBean() {
    }

    // Getters e Setters
    public int getIdIlustrador() {
        return idilustrador;
    }

    public void setIdIlustrador(int idilustrador) {
        this.idilustrador = idilustrador;
    }

    public String getNome() {
        return nome_ilustrador;
    }

    public void setNome(String nome) {
        this.nome_ilustrador = nome;
    }

    public String getTelefone() {
        return telefone_ilustrador;
    }

    public void setTelefone(String telefone) {
        this.telefone_ilustrador = telefone;
    }

    public String getEmail() {
        return email_ilustrador;
    }

    public void setEmail(String email) {
        this.email_ilustrador = email;
    }

    // Método toString atualizado para melhor legibilidade
    @Override
    public String toString() {
        return "IdIlustrador: " + idilustrador + "; " +
               "Nome: " + nome_ilustrador + "; " +
               "Telefone: " + telefone_ilustrador + "; " +
               "Email: " + email_ilustrador ;
    }

    // Método para converter um IlustradoresBean para um Document do MongoDB
    public Document toDocument() {
        Document doc = new Document("idilustrador", this.idilustrador)
                            .append("nome_ilustrador", this.nome_ilustrador)
                            .append("telefone_ilustrador", this.telefone_ilustrador)
                            .append("email_ilustrador", this.email_ilustrador);
        return doc;
    }

    // Método para converter um Document do MongoDB para um IlustradoresBean
    public static IlustradoresBean fromDocument(Document doc) {
        IlustradoresBean ilustrador = new IlustradoresBean();
        ilustrador.setIdIlustrador(doc.getInteger("idilustrador"));
        ilustrador.setNome(doc.getString("nome_ilustrador"));
        ilustrador.setTelefone(doc.getString("telefone_ilustrador"));
        ilustrador.setEmail(doc.getString("email_ilustrador"));
        return ilustrador;
    }
}
