/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import gym.ConnectionGym;
import java.sql.*;
import java.sql.PreparedStatement;
import modelo.Client;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


/**
 *
 * @author mto_l
 */
public class ClientDAO {
    private static Connection connection = new ConnectionGym().getConnection();
    
    public static void addName(Client client){
        
        String sql = "INSERT INTO users(cpf, client_name, birthday, weight, height) VALUES(?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO weight_evolution(client_id, register_date, weight) VALUES(?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql);
            PreparedStatement stmt2 = connection.prepareStatement(sql2);){
            
            stmt.setString(1,client.getCpf());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getBirthday());
            stmt.setDouble(4,client.getWeight());
            stmt.setDouble(5,client.getHeight());
            stmt.execute();
            
            stmt2.setString(1,client.getCpf());
            stmt2.setString(2, String.valueOf(LocalDate.now()));
            stmt2.setDouble(3, client.getWeight());
            
            stmt2.execute();
            
        }
        catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    public static void excluirPeso(int idPeso) {
       // Método para excluir o peso através do id 
        String sql = "DELETE FROM weight_evolution WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idPeso);
            stmt.executeUpdate();
            System.out.println("Peso excluído com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            // Tratar exceções
        }
    }
    public static void excluirAluno(String cpf) {
        // Método para excluir o cadastro pelo cpf
        String sqlDeletePesos = "DELETE FROM weight_evolution WHERE cpf_aluno = ?";
        String sqlDeleteUsuario = "DELETE FROM users WHERE cpf = ?";

        try (PreparedStatement stmtDeletePesos = connection.prepareStatement(sqlDeletePesos);
             PreparedStatement stmtDeleteUsuario = connection.prepareStatement(sqlDeleteUsuario)) {

            // Excluir pesos do aluno
            stmtDeletePesos.setString(1, cpf);
            stmtDeletePesos.executeUpdate();

            // Excluir usuário
            stmtDeleteUsuario.setString(1, cpf);
            stmtDeleteUsuario.executeUpdate();

            System.out.println("Aluno e pesos associados excluídos com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            // Tratar exceções
        }
    }
    public static boolean clientExists(String cpf) {
        // Método para verificar se o cliente existe antes de tentar inserir no banco de dados
        boolean exists = false;
        String sql = "SELECT COUNT(*) AS count FROM users WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    exists = count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar cliente existente: " + e.getMessage());
        }

        return exists;
    }
    public static String getUserName(String cpf){
        String sql = "SELECT client_name FROM users WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar cliente existente: " + e.getMessage());
        }

        return null;
    }
    public static String getHistorico(String cpf){
    //adiciona o histórico dos pesos juntamente com id
        String resultados = "";
        String exibirHistorico = "SELECT weight_evolution_id, register_date, weight FROM weight_evolution WHERE client_id = ?";
        try(PreparedStatement infoStatement = connection.prepareStatement(exibirHistorico)){
            infoStatement.setString(1, cpf);
            ResultSet infoResult = infoStatement.executeQuery();
            try{
                if(!infoResult.isBeforeFirst()){ 
                }else {
                    while (infoResult.next()){
                        String weightEvolutionId = infoResult.getString("weight_evolution_id");
                        String dataRegistro = infoResult.getString("register_date");
                        String weight = infoResult.getString("weight");
                        resultados += String.format("Id: %s   Data: %s, Peso: %s\n", 
                            weightEvolutionId, dataRegistro, weight);
                    }
                }
            }catch (Exception e) {
        // Trate aqui se houver erro ao converter para double
        }
        } catch (Exception e) {
        // Trate aqui se houver erro ao converter para double
        }
        return resultados;
    }
    public static String getWeight(String cpf) throws SQLException {
        // busca o peso atual na tabela users para cálculo do imc
    String sql = "SELECT weight FROM users WHERE cpf = ?";
    try(PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, cpf);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString("weight");
            }
        }
    } catch(Exception e) {
    
    }
    return "Nome não encontrado";
}
    public static String getHeight(String cpf) throws SQLException {
        // Busca a altura no banco de dados para calcular o imc
    String sql = "SELECT height FROM users WHERE cpf = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, cpf);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString("height");
            }
        }
    } catch(Exception e) {
    
    }
    return "Nome não encontrado";
}
}
        
    
