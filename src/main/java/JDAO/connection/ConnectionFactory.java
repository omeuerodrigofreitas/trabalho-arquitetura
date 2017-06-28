package JDAO.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/jdao";
    private static final String USER = "postgres";
    private static final String SENHA = "postgres";


    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USER,SENHA);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Errao ao abrir: " ,ex);
        }
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
           try {
               connection.close();
           } catch (SQLException ex) {
               System.out.println("Errao ao fechar: " + ex);
           }
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                System.out.println("Errao ao fechar via preparedStatement : " + ex);
            }
        }
        closeConnection(connection);
    }

    public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                System.out.println("Errao ao fechar via resultSet : " + ex);
            }
        }
        closeConnection(connection, preparedStatement);
    }


}
