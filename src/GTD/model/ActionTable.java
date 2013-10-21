/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

import GTD.controller.DatabaseController;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JCheckBox;

/**
 *
 * @author PimGame
 */
public class ActionTable extends DbTable<ActionRow> {

    private ActionRowset list;
    private StatusTable statuses;
    private ContextTable contexts;
    private ProjectTable projects;

    public ActionTable() {
        super("actions");
        ArrayList<String> cols = new ArrayList<String>();
        cols.add("Name");
        cols.add("Description");
        cols.add("Notes");
        cols.add("Action_date");
        cols.add("Statuschange_date");
        cols.add("Done");
        cols.add("Contexts_Context_id");
        cols.add("Statuses_Status_id");
        cols.add("Projects_Project_id");
        setIdField("Action_id");
        setColumns(cols);
    }

    public void setStatuses(StatusTable statuses) {
        this.statuses = statuses;
    }

    public void setContexts(ContextTable contexts) {
        this.contexts = contexts;
    }

    public void setProjects(ProjectTable projects) {
        this.projects = projects;
    }

    public ActionRowset fetchAll() {
        if (list == null) {
            list = new ActionRowset();
            try {
                ResultSet rs = DatabaseController.executeGetQuery(Query.GET_ACTIONS);

                while (rs.next()) {
                    ActionRow ar = createRow();
                    ar.setID(rs.getInt(1));
                    ar.setName(rs.getString(2));
                    ar.setDescription(rs.getString(3));
                    ar.addNote(rs.getString(4));
                    ar.setDate(rs.getDate(5));
                    ar.setLastChangedDate(rs.getDate(6));
                    ar.setDone(rs.getInt(7));
                    ar.setContext(rs.getInt(8));
                    ar.setStatus(rs.getInt(9));
                    ar.setProject(rs.getInt(10));
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

    public Object getValueAt(int rowIndex, int columnIndex) {

        if (columnIndex == 9) {
            return fetchAll().get(rowIndex).getID();
        } else {
            if (getColumns().get(columnIndex).equals("Statuses_Status_id")) {
                if (fetchAll().get(rowIndex).getStatus() != -1) {
                    for (StatusRow sr : statuses.fetchAll()) {
                        if (sr.getID() == fetchAll().get(rowIndex).getStatus()) {
                            return sr.getName();
                        }
                    }
                } else {
                    return null;
                }
            }
            if (getColumns().get(columnIndex).equals("Contexts_Context_id")) {
                if (fetchAll().get(rowIndex).getContext() != -1) {
                    for (ContextRow cr : contexts.fetchAll()) {
                        if (cr.getID() == fetchAll().get(rowIndex).getContext()) {
                            return cr.getName();
                        }
                    }
                } else {
                    return null;
                }
            }
            if (getColumns().get(columnIndex).equals("Projects_Project_id")) {
                if (fetchAll().get(rowIndex).getProject() != -1) {
                    for (ProjectRow pr : projects.fetchAll()) {
                        if (pr.getID() == fetchAll().get(rowIndex).getProject()) {
                            return pr.getName();
                        }
                    }
                } else {
                    return null;
                }
            }
            if (getColumns().get(columnIndex).equals("Done")) {
                if (fetchAll().get(rowIndex).get(getColumns().get(columnIndex)).equals("0")) {
                    return false;
                } else {
                    return true;
                }
            }
            if (fetchAll().get(rowIndex).get(getColumns().get(columnIndex)).equals("null")) {
                return null;
            }
            return fetchAll().get(rowIndex).get(getColumns().get(columnIndex));
        }
    }
}
