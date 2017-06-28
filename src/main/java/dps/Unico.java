package dps;

import java.sql.Connection;

public class Unico {

    public static void main(String[] args) {
        //Connection connection = new Conexao().getConnection();

        ConexaoSingleton.getInstance().conectar();


    }

}
