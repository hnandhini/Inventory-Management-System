/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HARITHA NANDHINI
 */
public class DBhelper2 {
    public static Statement st;
    public static Connection con;
    public static PreparedStatement getData;
    static
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","123");
            st=con.createStatement();
            getData=con.prepareStatement("select * from prodentry where pname like ?");
        
        
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBhelper2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBhelper2.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    public static ResultSet getData(String pname) throws SQLException
    {
     getData.setString(1,"%"+pname+"%");
     return getData.executeQuery();
     
    
    
    }



}
    
