/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gym;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author mto_l
 */
public class ConnectionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionGym().getConnection();
        System.out.println("Connection on");
        connection.close();
    }
    
}
