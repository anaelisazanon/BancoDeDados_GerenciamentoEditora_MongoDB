package com.mycompany.mavenproject2;
import org.bson.Document;


public class HistoriasEmQuadrinhosBean {
    private int idhistoria;
    private String titulo;
    private int volume;
    private String data_lancamento;
    private int idcolecao;
    private int idautor;
    private int idilustrador;
    private int ideditora;

    // Construtor
    public HistoriasEmQuadrinhosBean(int idhistoria, String titulo, int volume, String data_lancamento,
                                      int idcolecao, int idautor, int idilustrador, int ideditora) {
        this.idhistoria = idhistoria;
        this.titulo = titulo;
        this.volume = volume;
        this.data_lancamento = data_lancamento;
        this.idcolecao = idcolecao;
        this.idautor = idautor;
        this.idilustrador = idilustrador;
        this.ideditora = ideditora;
    }

    // Getters e Setters
    public int getIdHistoria() {
        return idhistoria;
    }

    public void setIdHistoria(int idhistoria) {
        this.idhistoria = idhistoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getDataLancamento() {
        return data_lancamento;
    }

    public void setDataLancamento(String data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public int getIdColecao() {
        return idcolecao;
    }

    public void setIdColecao(int idcolecao) {
        this.idcolecao = idcolecao;
    }

    public int getIdAutor() {
        return idautor;
    }

    public void setIdAutor(int idautor) {
        this.idautor = idautor;
    }

    public int getIdIlustrador() {
        return idilustrador;
    }

    public void setIdIlustrador(int idilustrador) {
        this.idilustrador = idilustrador;
    }

    public int getIdEditora() {
        return ideditora;
    }

    public void setIdEditora(int ideditora) {
        this.ideditora = ideditora;
    }

    // Método para converter HistoriasEmQuadrinhosBean para um Document do MongoDB
    public Document toDocument() {
        Document doc = new Document();
        doc.append("idhistoria", idhistoria);
        doc.append("titulo", titulo);
        doc.append("volume", volume);
        doc.append("data_lancamento", data_lancamento);
        doc.append("idcolecao", idcolecao);
        doc.append("idautor", idautor);
        doc.append("idilustrador", idilustrador);
        doc.append("ideditora", ideditora);
        return doc;
    }

    // Método para criar um HistoriasEmQuadrinhosBean a partir de um Document do MongoDB
    public static HistoriasEmQuadrinhosBean fromDocument(Document doc) {
        int idhistoria = doc.getInteger("idhistoria");
        String titulo = doc.getString("titulo");
        int volume = doc.getInteger("volume");
        String data_lancamento = doc.getString("data_lancamento");
        int idcolecao = doc.getInteger("idcolecao");
        int idautor = doc.getInteger("idautor");
        int idilustrador = doc.getInteger("idilustrador");
        int ideditora = doc.getInteger("ideditora");

        return new HistoriasEmQuadrinhosBean(idhistoria, titulo, volume, data_lancamento,
                                             idcolecao, idautor, idilustrador, ideditora);
    }

    @Override
    public String toString() {
        return "Id: " + idhistoria + ", Título: " + titulo + ", Volume: " + volume +
               ", Data de Lançamento: " + data_lancamento + ", Coleção ID: " + idcolecao +
               ", Autor ID: " + idautor + ", Ilustrador ID: " + idilustrador + 
               ", Editora ID: " + ideditora;
    }
}
