/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr2;


import static java.lang.System.out;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
    
    public List<imagen> getAllImagenes (){
        
        try {
            List<imagen> vimg = new ArrayList<>();
            imagen img;
            ResultSet res =  handMadeQuery("select * from image");
            
            while (res.next()) {
                img = new imagen(
                                 res.getString("title"),
                                 res.getString("description"),
                                 res.getString("keywords"),
                                 res.getString("author"),
                                 res.getString("creation_date"),
                                 res.getString("storage_date"),
                                 res.getString("filename")
                                 );
                
                vimg.add(img);
            }
            
            return vimg;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean usuarioExists (String username, String password){        
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
    
    public boolean imageExists(String imageName) {       
        
        try {            
            ResultSet res = handMadeQuery("select * from image i where i.title= '"+imageName+"'");
            
            if(res == null) return false;
            if(res.next() == false) return false;
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    public imagen getImage(String imageName) {
        imagen img = null;
        String s;
        try {            
            ResultSet res = handMadeQuery("select * from image i where i.title= '"+imageName+"'");
            
            if(res == null) return img;
            if(res.next() == false) return img;                    
            
            img = new imagen(res.getString("title"),
                         res.getString("description"),
                         res.getString("keywords"),
                         res.getString("author"),
                         res.getString("creation_date"),
                         res.getString("storage_date"),
                         res.getString("filename")
            );

            return img;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return img;
    }
    
    
    private int newImageID(){
        try {            
            ResultSet resID = handMadeQuery("SELECT MAX(id) FROM IMAGE");
            
            if(resID == null) return -1;
            if(resID.next() == false) return -1; 
            return resID.getInt(1) +1;
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    
    
    public boolean insertImage(String title, String description, String keywords, String author, String creationDate){
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String storageDate = simpleDateFormat.format(new Date());
        int ID = this.newImageID();
        if(ID == -1) return false;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            
            Statement statement = connection.createStatement();
                                               
            int res = statement.executeUpdate(
                "INSERT INTO IMAGE VALUES("+String.valueOf(ID)+",'"+title+"','"
                +description+"','"+keywords+"','"+author+"','"
                +creationDate+"','"+storageDate+"','"
                +title+".jpg')"
            );
            
            connection.close();
            if (res > 0) return true;
            return false;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return false;                    
        }
    }
}
