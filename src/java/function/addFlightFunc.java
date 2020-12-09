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
import model.Aircraft;
import model.Airport;
import model.FlightRoute;

/**
 *
 * @author DELL
 */
public class addFlightFunc {
    addFlightRouteFunction func = new addFlightRouteFunction();
    public String getDepartureByRouteId(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        FlightRoute fr = new FlightRoute();
        try{
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM flightroute where routeId = '"+id+"'");
        ResultSet rs = (ResultSet) stmt.executeQuery();
        while (rs.next()) {
            fr.setFlightRouteId(rs.getInt("routeId"));
            fr.setDeparturePlace(rs.getString("departurePlaceId"));
            fr.setArrivalPlace(rs.getString("arrivalPlaceId"));
            fr.setStandardPrice(rs.getInt("standardPrice"));
        }
        }
        catch (SQLException ex) {
            Logger.getLogger(addAirplaneFunc.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            pool.freeConnection(connection);
        }
        int intDepart = Integer.parseInt(fr.getDeparturePlace());
        return func.getAirportById(intDepart).getCityName();
    }
    public String getArrivalByRouteId(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        FlightRoute fr = new FlightRoute();
        try{
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM flightroute where routeId = '"+id+"'");
        ResultSet rs = (ResultSet) stmt.executeQuery();
        while (rs.next()) {
            fr.setFlightRouteId(rs.getInt("routeId"));
            fr.setDeparturePlace(rs.getString("departurePlaceId"));
            fr.setArrivalPlace(rs.getString("arrivalPlaceId"));
            fr.setStandardPrice(rs.getInt("standardPrice"));
        }
        }
        catch (SQLException ex) {
            Logger.getLogger(addAirplaneFunc.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            pool.freeConnection(connection);
        }
        int intDepart = Integer.parseInt(fr.getArrivalPlace());
        return func.getAirportById(intDepart).getCityName();
    }
    public Aircraft getAircraftById(String id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Aircraft ac = new Aircraft();
        try{
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM aircraft where airCraftId = '"+id+"'");
        ResultSet rs = (ResultSet) stmt.executeQuery();
        while (rs.next()) {
            ac.setAircraftId(rs.getInt("airCraftId"));
            ac.setAirbrand(rs.getString("airBrandId"));
            ac.setAircraftName(rs.getString("airCraftName"));
            ac.setModel(rs.getString("model"));
            ac.setSeatNumber(rs.getInt("seatNumber"));
        }
        }
        catch (SQLException ex) {
            Logger.getLogger(addAirplaneFunc.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            pool.freeConnection(connection);
        }
        return ac;
    }
}