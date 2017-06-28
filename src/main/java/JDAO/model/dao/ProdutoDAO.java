package JDAO.model.dao;

import JDAO.connection.ConnectionFactory;
import JDAO.model.bean.Categoria;
import JDAO.model.bean.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rfreitas on 26/04/17.
 */
public class ProdutoDAO {


    private Connection connection = null;

    public ProdutoDAO() {
        connection = ConnectionFactory.getConnection();
    }

    public boolean save(Produto produto) {
        String query = "INSERT INTO produto (descricao, qtd, preco, categoria_fk) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            int index = 0;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(++index, produto.getDescricao());
            preparedStatement.setInt(++index, produto.getQuantidade());
            preparedStatement.setDouble(++index, produto.getPreco());
            preparedStatement.setLong(++index, produto.getCategoria().getPk());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement);
        }
    }

    public List<Produto> findAll() {
        String query = "SELECT * FROM view_produto_categoria";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Produto produto = new Produto();
                Categoria categoria = new Categoria();
                produto.setPk(resultSet.getLong("pid"));
                produto.setDescricao(resultSet.getString("pdesc"));
                produto.setQuantidade(resultSet.getInt("qtd"));
                produto.setPreco(resultSet.getDouble("preco"));
                categoria.setPk(resultSet.getLong("cid"));
                categoria.setDescricao(resultSet.getString("cdesc"));
                produto.setCategoria(categoria);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
        }

        return produtos;
    }

    public boolean update(Produto produto) {
        String query = "UPDATE produto SET descricao = ?, qtd = ?, preco = ?, categoria_fk = ? WHERE pk = ?";
        PreparedStatement preparedStatement = null;

        try {
            int index = 0;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(++index, produto.getDescricao());
            preparedStatement.setInt(++index, produto.getQuantidade());
            preparedStatement.setDouble(++index, produto.getPreco());
            preparedStatement.setLong(++index, produto.getCategoria().getPk());
            preparedStatement.setLong(++index, produto.getPk());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection,preparedStatement);
        }
    }

    public boolean delete(Produto produto) {
        String query = "DELETE FROM produto WHERE pk = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, produto.getPk());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement);
        }

    }


    public Produto findFirist() {
        return  findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
