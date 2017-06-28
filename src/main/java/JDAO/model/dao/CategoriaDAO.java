package JDAO.model.dao;

import JDAO.connection.ConnectionFactory;
import JDAO.model.bean.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO {

    private Connection connection = null;

    public  CategoriaDAO() {
        connection = ConnectionFactory.getConnection();
    }

    public boolean save(Categoria categoria) {
        String query = "INSERT INTO categoria (descricao) VALUES (?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, categoria.getDescricao());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement);
        }
    }

    public List<Categoria> findAll() {
        String query = "SELECT * FROM categoria";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Categoria> categorias = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setPk(resultSet.getLong("pk"));
                categoria.setDescricao(resultSet.getString("descricao"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
        }

        return categorias;
    }

    public boolean update(Categoria categoria) {
        String query = "UPDATE categoria SET descricao = ? WHERE pk = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, categoria.getDescricao());
            preparedStatement.setLong(2, categoria.getPk());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection,preparedStatement);
        }
    }

    public boolean delete(Categoria categoria) {
        String query = "DELETE FROM categoria WHERE pk = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, categoria.getPk());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection, preparedStatement);
        }

    }

    public Categoria findFirist() {
        return  findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }

}
