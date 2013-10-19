/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.controller;

import GTD.model.Query;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pim
 */
public class DatabaseController {

    private static boolean connectionOpen;
    public static Connection con = null;

    public DatabaseController() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            openConnection();
        } catch (ClassNotFoundException ex) {
            System.err.println("MySQL Driver not found.");
        }
    }

    public static synchronized boolean openConnection() {
        try {
            if (con == null) {
                con = DriverManager.getConnection("jdbc:mysql://databases.aii.avans.nl/gagpvenn_db", "gagpvenn", "runescape1");
                System.out.println("Connection opened.  -- " + con);
                connectionOpen = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            connectionOpen = false;
        }
        return connectionOpen;
    }

    public static boolean closeConnection() {
        try {
            con.close();
            System.out.println("Connection closed.");
            connectionOpen = false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connectionOpen;
    }

    public static ResultSet executeGetQuery(Query q) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(q.getQuery());

            rs = ps.executeQuery();

            ps.closeOnCompletion();



        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        return rs;
    }
}
