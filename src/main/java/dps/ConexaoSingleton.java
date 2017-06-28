package dps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoSingleton {

    private Connection connection;
    private String nomeBanco;
    private static ConexaoSingleton conexaoSingleton;

    private Connection getConnection() {
        try{
            return DriverManager.getConnection("url","user","senha");
        } catch (SQLException e) {
            System.out.print("Deu zebra");
            throw new RuntimeException();
        }
    }

    public static synchronized ConexaoSingleton getInstance() {
      if(conexaoSingleton == null) {
          conexaoSingleton = new ConexaoSingleton();
      }
      return conexaoSingleton;
    }

    public void conectar() {
        System.out.println("agora foi aberta!");
    }

    public void desconectar() {
        System.out.println("Agora foi fechado");
    }


}
