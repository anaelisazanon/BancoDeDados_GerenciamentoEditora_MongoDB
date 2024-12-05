package com.mycompany.mavenproject2;
import org.bson.Document;

public class EditorasBean {
    private int ideditora;
    private String cnpj_editora;
    private String nome_editora;
    private String telefone_editora;
    private String cep;
    private String rua;
    private String numero;

    // Construtor com todos os parâmetros
    public EditorasBean(int ideditora, String cnpj_editora, String nome_editora, String telefone_editora, String cep, String rua, String numero) {
        this.ideditora = ideditora;
        this.cnpj_editora = cnpj_editora;
        this.nome_editora = nome_editora;
        this.telefone_editora = telefone_editora;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
    }
    
    // Construtor vazio (opcional)
    public EditorasBean() {
    }

    // Getters e Setters
    public int getIdEditora() {
        return ideditora;
    }

    public void setIdEditora(int ideditora) {
        this.ideditora = ideditora;
    }

    public String getCnpjEditora() {
        return cnpj_editora;
    }

    public void setCnpjEditora(String cnpj_editora) {
        this.cnpj_editora = cnpj_editora;
    }

    public String getNomeEditora() {
        return nome_editora;
    }

    public void setNomeEditora(String nome_editora) {
        this.nome_editora = nome_editora;
    }

    public String getTelefoneEditora() {
        return telefone_editora;
    }

    public void setTelefoneEditora(String telefone_editora) {
        this.telefone_editora = telefone_editora;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    // Método toString atualizado para melhor legibilidade
    @Override
    public String toString() {
        return "IdEditora: " + ideditora + "; " +
               "CNPJ: " + cnpj_editora + "; " +
               "Nome: " + nome_editora + "; " +
               "Telefone: " + telefone_editora + "; " +
               "CEP: " + cep + "; " +
               "Rua: " + rua + "; " +
               "Número: " + numero  ;
    }

    // Método para converter um EditorasBean para um Document do MongoDB
    public Document toDocument() {
        Document doc = new Document("ideditora", this.ideditora)
                            .append("cnpj_editora", this.cnpj_editora)
                            .append("nome_editora", this.nome_editora)
                            .append("telefone_editora", this.telefone_editora)
                            .append("cep", this.cep)
                            .append("rua", this.rua)
                            .append("numero", this.numero);
        return doc;
    }

    // Método para converter um Document do MongoDB para um EditorasBean
    public static EditorasBean fromDocument(Document doc) {
        EditorasBean editora = new EditorasBean();
        editora.setIdEditora(doc.getInteger("ideditora"));
        editora.setCnpjEditora(doc.getString("cnpj_editora"));
        editora.setNomeEditora(doc.getString("nome_editora"));
        editora.setTelefoneEditora(doc.getString("telefone_editora"));
        editora.setCep(doc.getString("cep"));
        editora.setRua(doc.getString("rua"));
        editora.setNumero(doc.getString("numero"));
        return editora;
    }
}
