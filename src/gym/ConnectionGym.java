/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author mto_l
 */
public class ConnectionGym {
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/gym","root","fatec");
        }
        catch(SQLException excecao){
            throw new RuntimeException(excecao);
            
        }
    }
}
