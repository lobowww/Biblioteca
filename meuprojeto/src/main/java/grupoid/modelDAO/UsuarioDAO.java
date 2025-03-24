package grupoid.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import grupoid.model.Usuario;

public class UsuarioDAO {
    private Connection conexao;

    public UsuarioDAO(Connection conexao){
        this.conexao = conexao;
    }

     public void create(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuarios (nome, endereco, telefone, email, cpf) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEndereco());
            statement.setString(3, usuario.getTelefone());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getCpf());
            statement.executeUpdate();
        }
    }

    public Usuario read(int id) throws SQLException{
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("endereco"),
                        resultSet.getString("telefone"),
                        resultSet.getString("email"),
                        resultSet.getString("cpf")
                    );
                }
                return null;
            }
        }
    }
    public void update(Usuario usuario) throws SQLException{
        String sql = "UPDATE usuarios SET nome = ?, endereco = ?, telefone = ?, email = ?, cpf = ? WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEndereco());
            statement.setString(3, usuario.getTelefone());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getCpf());
            statement.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException{
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    public List<Usuario> getAll() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    usuarios.add(new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("endereco"),
                        resultSet.getString("telefone"),
                        resultSet.getString("email"),
                        resultSet.getString("cpf")
                    ));
                }
            }
            return usuarios;
    }
}
