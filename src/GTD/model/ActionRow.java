/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PimGame
 */
public class ActionRow extends DbRow {

    private ThoughtRow thoughtOrigin;
    private ArrayList<String> notes = new ArrayList<String>();
    private String description = "No description";
    private Status status;
    private boolean done;
    private String date;
    private String lastChangedDate;

    public ActionRow() {
        //this.thoughtOrigin = thoughtOrigin;
    }

    public void addNote(String note) {
        String[] noteBuilder = note.split(",");
        for (String s : noteBuilder) {
            if (s == null) {
                continue;
            }
            s = s.trim();
            if (s.isEmpty()) {
                continue;
            }
            notes.add(s);
        }
        set("Notes", getNotesAsString());
    }

    public void deleteNote(int index) {
        notes.remove(index);
    }

    public void setDescription(String description) {
        this.description = description;
        set("Description", description);
    }

    public void setStatus(int status) {
        System.out.println("STATUS IS: " + status);
        set("Statuses_Status_id", status + "");
    }

    public void setProject(int project) {
        if (project == -1) {
            set("Projects_Project_id", "null");
        } else {
            set("Projects_Project_id", project + "");
        }
    }

    public void setContext(int context) {
        if (context == -1) {
            set("Contexts_Context_id", "null");
        } else {
            set("Contexts_Context_id", context + "");
        }
    }

    public void setDone(int completed) {
        set("Done", completed + "");
    }

    public void setDate(Date date) {
        if (date != null) {
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
            System.out.println("DATE: " + sqlDate.toString());
            set("Action_date", sqlDate + "");
        } else {
            set("Action_date", date + "");
        }

    }

    public void setLastChangedDate(Date lastChangedDate) {
        if (lastChangedDate != null) {
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(lastChangedDate.getTime());
            System.out.println("DATE: " + sqlDate.toString());
            set("Statuschange_date", sqlDate + "");
        } else {
            set("Statuschange_date", lastChangedDate + "");
        }
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return Integer.valueOf(get("Statuses_Status_id"));
    }

    public int getProject() {
        return Integer.valueOf(get("Projects_Project_id"));
    }

    public int getContext() {
        return Integer.valueOf(get("Contexts_Context_id"));
    }

    public int getDone() {
        return Integer.valueOf(get("Done"));
    }

    public String getDate() {
        return date;
    }

    public String getLastChangedDate() {
        return lastChangedDate;
    }

    public String getNotesAsString() {
        StringBuilder sb = new StringBuilder();
        for (String s : notes) {
            sb.append(s + ", ");
        }
        String note = sb.substring(0, sb.length() - 2);

        return note;
    }

    @Override
    public String toString() {

        return get("Name");
    }
}
