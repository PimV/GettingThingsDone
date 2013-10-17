/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

import GTD.controller.DatabaseController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.HashMap;

/**
 *
 * @author PimGame
 */
public class DbRow<T> {

    private HashMap<String, String> data;
    protected DbTable<T> table;
    private boolean isChanged;
    private int id;

    public DbRow() {
        data = new HashMap<>();
        id = -1;
    }

    public void setTable(DbTable table) {
        this.table = table;
    }

    public void set(String k, String v) {
        this.data.put(k, v);
        isChanged = true;
    }

    public String get(String k) {
        if (this.data.containsKey(k)) {
            return this.data.get(k);
        }
        return null;
    }

    public void save() {
        if (isChanged || id == -1) {
            // Create row in database
            String query = "INSERT INTO " + DbTable.DATABASE_NAME + "." + table.getName() + " ";

            // Columns
            query += "(";
            for (String colName : table.getColumns()) {
                query += colName + ", ";
            }
            query = query.substring(0, query.length() - 2);
            query += ")";

            // Values
            query += " VALUES ";
            query += "(";
            for (String colName : table.getColumns()) {
                query += "?, ";
            }
            query = query.substring(0, query.length() - 2);
            query += ")";


            try {
                DatabaseController.openConnection();
                PreparedStatement stmt = DatabaseController.con.prepareStatement(query);

                // Set values
                int i = 1;
                for (String colName : table.getColumns()) {
                    if (get(colName, "").equals("null")) {
                        stmt.setNull(i, Types.NULL);
                    } else {
                        stmt.setString(i, get(colName, ""));
                    }
                    i++;
                }
                stmt.execute();
                stmt.closeOnCompletion();

                ResultSet keys = stmt.executeQuery("SELECT LAST_INSERT_ID()");
                if (keys.next()) {
                    setID(keys.getInt(1));
                }
                keys.close();

                stmt.closeOnCompletion();

            } catch (SQLException sx) {
                sx.printStackTrace();
            } finally {
                //DatabaseController.closeConnection();
            }



            System.out.println(query);
        } else if (isChanged) {
            // Update existing row
            String query = "UPDATE " + DbTable.DATABASE_NAME + "." + table.getName() + " SET ";
            for (String colName : table.getColumns()) {
                query += colName + "=?, ";
            }
            query = query.substring(0, query.length() - 2);

            //
            query += " WHERE " + table.getIdField() + "=? LIMIT 1";


            try {
                DatabaseController.openConnection();
                PreparedStatement stmt = DatabaseController.con.prepareStatement(query);

                // Set values
                int i = 1;
                for (String colName : table.getColumns()) {
                    stmt.setString(i, get(colName, ""));
                    i++;
                }
                stmt.setString(i, get(table.getIdField(), ""));

                stmt.execute();
                stmt.closeOnCompletion();
            } catch (SQLException sx) {
                sx.printStackTrace();
            } finally {
                //DatabaseController.closeConnection();
            }

            System.out.println(query);
        }
    }

    private String get(String k, String def) {
        String val = get(k);
        if (val == null) {
            return def;
        }
        return val;
    }

    public void setID(int id) {
        set(table.getIdField(), id + "");
    }

    public int getID() {
        return Integer.valueOf(get(table.getIdField()));
    }
}