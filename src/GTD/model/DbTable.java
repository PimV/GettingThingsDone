/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

import GTD.controller.DatabaseController;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PimGame
 */
public class DbTable<T> {

    private String name; // Database table name
    public static final String DATABASE_NAME = "gagpvenn_db";
    private ArrayList<String> columns;
    private String idField;

    public DbTable(String name) {
        this.name = name;
        this.columns = new ArrayList<>();
    }

    protected void setColumns(ArrayList<String> c) {
        columns = c;
    }

    protected void setIdField(String idField) {
        this.idField = idField;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    public String getIdField() {
        return this.idField;
    }

    public void remove(int id) {
        String q = "DELETE FROM " + DbTable.DATABASE_NAME + "." + getName() + " WHERE " + getIdField() + "=? LIMIT 1";
        try {
            DatabaseController.openConnection();
            PreparedStatement stmt = DatabaseController.con.prepareStatement(q);

            stmt.setString(1, id + "");
            stmt.execute();
            //stmt.closeOnCompletion();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //DatabaseController.closeConnection();
        }
    }
}