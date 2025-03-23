package grupoid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/biblioteca";
        String usuario = "felipe";
        String senha = "3029";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}