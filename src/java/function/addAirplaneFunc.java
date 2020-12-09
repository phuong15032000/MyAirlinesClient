/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import DBHelper.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Airbrand;

/**
 *
 * @author DELL
 */
public class addAirplaneFunc {
    public Airbrand getAirbrandNameById(String id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Airbrand a = new Airbrand();
        try{
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM airbrand where airBrandId = '"+id+"'");
        ResultSet rs = (ResultSet) stmt.executeQuery();
        while (rs.next()) {
            a.setAirbrandId(rs.getInt("airBrandId"));
            a.setAirbrandName(rs.getString("nameAirBrand"));
        }
        }
        catch (SQLException ex) {
            Logger.getLogger(addAirplaneFunc.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            pool.freeConnection(connection);
           
        }
        return a;
    }
    
}
