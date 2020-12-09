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
import model.Airport;
import model.FlightRoute;

/**
 *
 * @author DELL
 */
public class addFlightRouteFunction {
    public Airport getAirportById(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Airport a = new Airport();
        try{
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM airport where airportId = '"+id+"'");
        ResultSet rs = (ResultSet) stmt.executeQuery();
        while (rs.next()) {
            a.setAirportId(rs.getInt("airportId"));
            a.setAirportName(rs.getString("airportName"));
            a.setCityName(rs.getString("cityName"));
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
