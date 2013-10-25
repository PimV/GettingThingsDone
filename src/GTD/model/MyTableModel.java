/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

/**
 *
 * @author PimGame
 */
public class MyTableModel extends ActionTable {

    private StatusTable statuses;
    private ContextTable contexts;
    private ProjectTable projects;

    public void setStatuses(StatusTable statuses) {
        this.statuses = statuses;
    }

    public void setContexts(ContextTable contexts) {
        this.contexts = contexts;
    }

    public void setProjects(ProjectTable projects) {
        this.projects = projects;
        
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
