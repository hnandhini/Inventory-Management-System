/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invshop;

import static invshop.DBhelper.con;
import static invshop.DBhelper.getData;
import static invshop.DBhelper.st;
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
public class DBhelpersr 
{
//This class will help us to communicate with the database
public static Statement st;
public static Connection conn;
public static PreparedStatement getData;
static
{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","123");
            st=conn.createStatement();        
            getData=conn.prepareStatement("select * from invoice where invid=?");
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBhelpersr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        Logger.getLogger(DBhelpersr.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
public static ResultSet getData(String invid) throws SQLException
{
    getData.setString(1,invid);
    return getData.executeQuery();
}
}
