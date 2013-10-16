/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.controller;

import GTD.model.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pim
 */
public  class DatabaseController {

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

    public static boolean openConnection() {
        try {

            con = DriverManager.getConnection("jdbc:mysql://databases.aii.avans.nl/gagpvenn_db", "gagpvenn", "runescape1");
            System.out.println("Connection opened.  -- " + con);
            connectionOpen = true;


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
        //openConnection();
        ResultSet rs = null;
        try {
            con.setAutoCommit(false);
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

    public static int executeNormalQuery(String query) {
        openConnection();

        int generatedKey = -1;

        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(query);
            System.out.println(query);
            ps.executeUpdate();
            ResultSet keys = ps.executeQuery("SELECT LAST_INSERT_ID()");
            if (keys.next()) {
                generatedKey = keys.getInt(1);
            }
            keys.close();
            keys = null;
            con.commit();

        } catch (SQLException ex) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
        } finally {
            closeConnection();
        }
        return generatedKey;

    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
}
