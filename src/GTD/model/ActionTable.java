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
public class ActionTable extends DbTable<ActionRow> {

    private ActionRowset list;

    public ActionTable() {
        super("actions");
        ArrayList<String> cols = new ArrayList<String>();
        cols.add("Description");
        cols.add("Notes");
        cols.add("Action_date");
        cols.add("Statuschange_date");
        cols.add("Done");
        cols.add("Contexts_Context_id");
        cols.add("Statuses_Status_id");
        cols.add("Projects_Project_id");
        setColumns(cols);
        setIdField("Action_id");
    }

    public ActionRowset fetchAll() {
        if (list == null) {
            list = new ActionRowset();
            try {
                ResultSet rs = DatabaseController.executeGetQuery(Query.GET_ACTIONS);

                while (rs.next()) {
                    ActionRow ar = createRow();
                    ar.setID(rs.getInt(1));
                    ar.setDescription(rs.getString(2));
                    ar.addNote(rs.getString(3));
                    ar.setDate(rs.getDate(4));
                    ar.setLastChangedDate(rs.getDate(5));
                    ar.setDone(rs.getInt(6));
                    ar.setContext(rs.getInt(7));
                    ar.setStatus(rs.getInt(8));
                    ar.setProject(rs.getInt(9));
                    list.add(ar);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ActionRow createRow() {
        ActionRow a = new ActionRow();
        a.setTable(this);
        return a;
    }
}
