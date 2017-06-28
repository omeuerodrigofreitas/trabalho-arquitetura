package JDAO.model.bean;


public class Produto {

    private Long pk;
    private String descricao;
    private int quantidade;
    private double preco;
    private Categoria categoria;


    public Produto() {
    }

    public Produto(Categoria categoria){
        this.categoria = categoria;
    }

    public Produto(String descricao, int quantidade, double preco, Categoria categoria) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double valor) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "pk " + pk +
                ", descricao '" + descricao  +
                ", quantidade " + quantidade +
                ", preco " + preco +
                ", categoria " + categoria.getDescricao();
    }
}
