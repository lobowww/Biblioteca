package grupoid.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import grupoid.model.Livro;

public class LivroDAO {

    private Connection conexao;

    public LivroDAO(Connection conexao){
        this.conexao = conexao;
    }
    //Criar novo livro
    public void create(Livro livro) throws SQLException{//Indica que o método pode lançar uma exceção do tipo SQLException, que ocorre na interação com o BD
        String sql = "INSERT INTO livros (titulo, autor_id, isbn, genero) VALUES (?, ?, ?, ?)";//Definição da consulta SQL que será executada no banco, específica que está inserindo dados e indica onde os valores serãop colocados
        try (PreparedStatement statement = conexao.prepareStatement(sql)){//bloco que garante que o objeto preparedStatement seja fechado automaticamente após o uso, mesmo que ocorra uma exceção
            statement.setString(1, livro.getTitulo());//Define o valor do primeiro placeholder(?) na consulta SQL
            statement.setInt(2, livro.getAutorId());
            statement.setString(3, livro.getIsbn());
            statement.setString(4, livro.getGenero());
            statement.executeUpdate();//Executa a consulta SQL no BD
        }
    }
    //Consultar livro no banco por meio do ID
    public Livro read(int id) throws SQLException{
        String sql = "SELECT * FROM livros WHERE id = ?";//Seleciona todas as colunas da tabela livros, filtra os resultados para retorno do ID fornecido, ? é um placeholder que será substituído
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){//Move o cursor para o próximo registro ResultSet, logo se houver o livro retorna as insformações do mesmo inclusas abaixo
                    return new Livro(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getInt("autor_id"),
                        resultSet.getString("isbn"),
                        resultSet.getString("genero")
                    );
                }
                return null;//Caso não entre o livro, retorna nulo
            }
        }
    }
    //Atualizar um livro existente no banco
    public void update(Livro livro) throws SQLException{
        String sql = "UPDATE livros SET titulo = ?, autor_id = ?, isbn = ?, genero = ? WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, livro.getTitulo());
            statement.setInt(2, livro.getAutorId());
            statement.setString(3, livro.getIsbn());
            statement.setString(4, livro.getGenero());
            statement.executeUpdate();
        }
    }
    //deletar um livro existente no banco
    public void delete(int id) throws SQLException{
        String sql = "DELETE FORM livros WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    //Pegar todos os livros armazenados no banco
    public List<Livro> getAll() throws SQLException{
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    livros.add(new Livro(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getInt("autor_id"),
                        resultSet.getString("isbn"),
                        resultSet.getString("genero")
                    ));
                }
            }
            return livros;
    }

}
