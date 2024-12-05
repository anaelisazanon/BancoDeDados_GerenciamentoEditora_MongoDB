package com.mycompany.mavenproject2;
import org.bson.Document;


public class ColecoesBean {
    private int idcolecao;
    private String nomecolecao;
    private String data;

    // Construtor
    public ColecoesBean(int idColecao, String nomeColecao, String data) {
        this.idcolecao = idColecao;
        this.nomecolecao = nomeColecao;
        this.data = data;
    }

    // Getters e Setters
    public int getIdColecao() {
        return idcolecao;
    }

    public void setIdColecao(int idColecao) {
        this.idcolecao = idColecao;
    }

    public String getNomeColecao() {
        return nomecolecao;
    }

    public void setNomeColecao(String nomeColecao) {
        this.nomecolecao = nomeColecao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // Método toString
    @Override
    public String toString() {
        return "ID: " + idcolecao + ", Nome da Colecao: " + nomecolecao + ", Data: " + data;
    }

    // Método para converter o bean para um Document do MongoDB
    public Document toDocument() {
        return new Document("idColecao", this.idcolecao)
                .append("nomeColecao", this.nomecolecao)
                .append("data", this.data);
    }

    // Método para criar um bean a partir de um Document do MongoDB
    public static ColecoesBean fromDocument(Document doc) {
        int idColecao = doc.getInteger("idcolecao");
        String nomeColecao = doc.getString("nomecolecao");
        String data = doc.getString("data");

        return new ColecoesBean(idColecao, nomeColecao, data);
    }
}
