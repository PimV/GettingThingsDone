/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

import GTD.controller.DatabaseController;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author PimGame
 */
public class StatusTable extends DbTable<StatusRow> {

    private StatusRowset list;

    public StatusTable() {
        super("statuses");
        ArrayList<String> cols = new ArrayList<String>();
        cols.add("Name");
        setColumns(cols);
        setIdField("Status_id");
    }

    public StatusRowset fetchAll() {
        if (list == null) {
            list = new StatusRowset();
        }
        try {
            ResultSet rs = DatabaseController.executeGetQuery(Query.GET_STATUSES);
            while (rs.next()) {
                StatusRow sr = createRow();
                sr.setID(rs.getInt(1));
                sr.setName(rs.getString(2));
                list.add(sr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public StatusRow createRow() {
        StatusRow sr = new StatusRow();
        sr.setTable(this);
        return sr;
    }
}
