/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

import GTD.controller.DatabaseController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PimGame
 */
public class ContextTable extends DbTable<ContextRow> {

    private ContextRowset list;

    public ContextTable() {
        super("context");
        ArrayList<String> cols = new ArrayList<String>();
        cols.add("Name");
        setColumns(cols);
        setIdField("Context_id");
    }

    public ContextRowset fetchAll() {
        if (list == null) {
            list = new ContextRowset();
            try {
                ResultSet rs = DatabaseController.executeGetQuery(Query.GET_CONTEXTS);
                while (rs.next()) {
                    ContextRow cr = createRow();
                    cr.setID(rs.getInt(1));
                    cr.setName(rs.getString(2));
                    list.add(cr);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public ContextRow createRow() {
        ContextRow cr = new ContextRow();
        cr.setTable(this);
        return cr;
    }
}
