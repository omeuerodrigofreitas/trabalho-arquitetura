package JDAO.model.dao;

import JDAO.model.bean.Categoria;
import JDAO.model.bean.Produto;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by rfreitas on 14/06/17.
 */
public class ProdutoDAOTest {

    public ProdutoDAOTest() {}

    @Test
    public void inserir() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Categoria categoria  = categoriaDAO.findFirist();

        Produto produto = new Produto(categoria);
        produto.setPreco(10);
        produto.setQuantidade(1);
        produto.setDescricao("teste");

        assertTrue("Deu xoxoh", produtoDAO.save(produto));

    }


    @Test
    public void findAll() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.findAll();
        produtos.forEach(System.out::println);

    }


    @Test
    public void editar() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.findFirist();
        produtoDAO = new ProdutoDAO();
        assertNotNull("Deu xoxoh categoria null",produto);
        produto.setDescricao("editado" + LocalDateTime.now());
        assertTrue("Deu xoxoh", produtoDAO.update(produto));
    }

    @Test
    public void excluir() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.findFirist();
        produtoDAO = new ProdutoDAO();
        assertNotNull("Deu xoxoh categoria null",produto);
        assertTrue("Deu xoxoh", produtoDAO.delete(produto));
    }
}
