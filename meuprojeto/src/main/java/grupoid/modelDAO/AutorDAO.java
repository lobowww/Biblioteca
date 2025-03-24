package grupoid.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import grupoid.model.Autor;

public class AutorDAO {
    private Connection conexao;

    public AutorDAO(Connection conexao){
        this.conexao = conexao;
    }

     public void create(Autor autor) throws SQLException{
        String sql = "INSERT INTO autores (nome, nacionalidade) VALUES (?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getNacionalidade());
            statement.executeUpdate();
        }
    }

    public Autor read(int id) throws SQLException{
        String sql = "SELECT * FROM autores WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Autor(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("nacionalidade")
                    );
                }
                return null;
            }
        }
    }
    public void update(Autor autor) throws SQLException{
        String sql = "UPDATE autores SET nome = ?, nacionalidade = ? WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getNacionalidade());
            statement.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException{
        String sql = "DELETE FROM autores WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    public List<Autor> getAll() throws SQLException{
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autores";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    autores.add(new Autor(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("nacionalidade")
                    ));
                }
            }
            return autores;
    }
}
