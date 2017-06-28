package JDAO.model.dao;


import JDAO.model.bean.Categoria;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by rfreitas on 31/05/17.
 */


public class CategoriaDAOTest {

    public CategoriaDAOTest() {}

    @Test
    public void inserir() {
        Categoria categoria = new Categoria("Sport");
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        assertTrue("Deu xoxoh", categoriaDAO.save(categoria));

    }

    @Test
    public void inserirComErro() {
        Categoria categoria = new Categoria();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        assertFalse("Deu xoxoh", categoriaDAO.save(categoria));

    }

    @Test
    public void findAll() {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.findAll();
        categorias.forEach(System.out::println);


        categorias.stream()
               .filter(c -> Objects.equals(c.getDescricao(), "Sport"))
               .forEach(System.out::println);

    }


    @Test
    public void editar() {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoria = categoriaDAO.findFirist();
        categoriaDAO = new CategoriaDAO();
        assertNotNull("Deu xoxoh categoria null",categoria);
        categoria.setDescricao("editado" + LocalDateTime.now());
        assertTrue("Deu xoxoh", categoriaDAO.update(categoria));
    }

    @Test
    public void excluir() {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoria = categoriaDAO.findFirist();
        categoriaDAO = new CategoriaDAO();
        assertNotNull("Deu xoxoh categoria null",categoria);
        assertTrue("Deu xoxoh", categoriaDAO.delete(categoria));
    }


}
