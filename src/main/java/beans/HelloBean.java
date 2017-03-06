/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0587637
 */
@Named
@ApplicationScoped
public class HelloBean {

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HelloBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection("jdbc:sqlite:sample.db");
    }

    public HelloBean() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("drop table if exists person");
            stmt.executeUpdate("create table person (id INTEGER PRIMARY KEY AUTOINCREMENT, name string)");
            stmt.executeUpdate("insert into person (name) values('Len')");
            ResultSet rs = stmt.executeQuery("SELECT * FROM person");
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelloBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String name = "World";

    public String getName() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM person");
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelloBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    public void setName(String name) {
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("insert into person (name) values(?)");
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            this.name = name;
        } catch (SQLException ex) {
            Logger.getLogger(HelloBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
