package grupoid.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import grupoid.model.Emprestimo;

public class EmprestimoDAO {
    private Connection conexao;

    public EmprestimoDAO(Connection conexao){
        this.conexao = conexao;
    }

     public void create(Emprestimo emprestimo) throws SQLException{
        String sql = "INSERT INTO emprestimos (usuarioId, livroId, dataEmprestimo, dataDevolucao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, emprestimo.getUsuarioId());
            statement.setInt(2, emprestimo.getLivroId());
            statement.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            statement.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
            statement.executeUpdate();
        }
    }

    public Emprestimo read(int id) throws SQLException{
        String sql = "SELECT * FROM emprestimos WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return new Emprestimo(
                        resultSet.getInt("id"),
                        resultSet.getInt("usuarioId"),
                        resultSet.getInt("livroId"),
                        resultSet.getDate("dataEmprestimo"),
                        resultSet.getDate("dataDevolucao")
                    );
                }
                return null;
            }
        }
    }
    public void update(Emprestimo emprestimo) throws SQLException{
        String sql = "UPDATE emprestimos SET usuarioId = ?, livroId = ?, dataEmprestimo = ?, dataDevolucao = ? WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, emprestimo.getUsuarioId());
            statement.setInt(2, emprestimo.getLivroId());
            statement.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            statement.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
            statement.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException{
        String sql = "DELETE FROM emprestimos WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    public List<Emprestimo> getAll() throws SQLException{
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    emprestimos.add(new Emprestimo(
                        resultSet.getInt("id"),
                        resultSet.getInt("usuarioId"),
                        resultSet.getInt("livroId"),
                        resultSet.getDate("dataEmprestimo"),
                        resultSet.getDate("dataDevolucao")
                    ));
                }
            }
            return emprestimos;
    }
}
