/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import gym.ConnectionGym;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;



public class WeightUpdateDAO {
    // Método para atualizar o peso adicionado como peso atual na tabela users
    private static Connection connection = new ConnectionGym().getConnection();   
    
    public static void updateWeight(String cpf, double newWeight){
        try{
            String updateSql = "UPDATE users SET weight = ? WHERE cpf = ?";
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setDouble(1, newWeight);
                updateStmt.setString(2, cpf);
                updateStmt.executeUpdate();
            }  
            
            String insertSql = "INSERT INTO weight_evolution (client_id, register_date, weight) VALUES (?,?,?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                insertStmt.setString(1,cpf);
                insertStmt.setString(2, String.valueOf(LocalDate.now()));
                insertStmt.setDouble(3,newWeight);
                insertStmt.executeUpdate();
            }
            
            }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        //lembrar de criar um botão para fazer o updade do novo peso e seu respectivo registro no BD
        //WeightUpdateDAO weightUpdateDAO = new WeightUpdateDAO();
        //int clientId = 123; // Substitua pelo ID do cliente atual
        //double newWeight = 75.5; // Substitua pelo novo peso
        //weightUpdateDAO.updateWeight(clientId, newWeight);
    }
}