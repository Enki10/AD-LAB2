/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcu
 */
public class DB {
    
    public DB(){}

    
    private ResultSet handMadeQuery(String query){
        ResultSet res = null;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            
            PreparedStatement statement;
            statement = connection.prepareStatement(query);   
            
            res = statement.executeQuery();
            
            return res;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return null;                    
        }

    }
    
    
    public List<usuario> getAllUsuarios (){
        
        try {
            List<usuario> vusr = new ArrayList<>();
            usuario usr;
            ResultSet res =  handMadeQuery("select * from usuarios");
            
            while (res.next()) {
                usr = new usuario(res.getString("id_usuario"), res.getString("password"));
                vusr.add(usr);
            }
            
            return vusr;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean usuarioExists (String username, String password)throws ClassNotFoundException, SQLException{        
        try {
            ResultSet res = handMadeQuery("select * from usuarios u where u.ID_USUARIO = '"+username+"' and u.PASSWORD = '"+password+"'");

            if(res == null) return false;
            if(res.next() == false) return false;
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
