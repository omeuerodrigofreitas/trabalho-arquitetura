package dps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {

    private static String url = "jdbc:oRestoVaiAqui";

    private static String user =  "user";
    private static String senha = "senha";


    public Connection getConnection(){
        try{
            return DriverManager.getConnection(url,user,senha);
        } catch (SQLException e) {
            System.out.print("Deu zebra");
            throw new RuntimeException();
        }
    }


}
