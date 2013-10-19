/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author PimGame
 */
public class ActionRow extends DbRow {

    private ThoughtRow thoughtOrigin;
    private ArrayList<String> notes = new ArrayList<String>();
    private String description = "No description";
    private String date;
    private String lastChangedDate;

    public ActionRow() {
        //this.thoughtOrigin = thoughtOrigin;
    }

    public void addNote(String note) {
        notes.clear();
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
        //System.out.println("STATUS IS: " + status);

        if (status == -1 || status == 0) {
            set("Statuses_Status_id", "null");
        } else {
            set("Statuses_Status_id", status + "");
        }
    }

    public void setProject(int project) {
        if (project == -1 || project == 0) {
            set("Projects_Project_id", "null");
        } else {
            set("Projects_Project_id", project + "");
        }
    }

    public void setContext(int context) {
        if (context == -1 || context == 0) {
            set("Contexts_Context_id", "null");
        } else {
            set("Contexts_Context_id", context + "");
        }
    }

    public void setDone(int completed) {
        set("Done", completed + "");
    }

    public void setDate(java.util.Date date) {
        if (date != null) {
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
            //System.out.println("DATE: " + sqlDate.toString());
            set("Action_date", sqlDate + "");
        } else {
            set("Action_date", "null");
        }
    }

    public void setLastChangedDate(java.util.Date lastChangedDate) {
        if (lastChangedDate != null) {
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(lastChangedDate.getTime());
            //System.out.println("DATE: " + sqlDate.toString());
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
        //  return Integer.valueOf(get("Statuses_Status_id"));
        if (!get("Statuses_Status_id").equals("null")) {
            //System.out.println("GOTTEN STATUS: " + get("Statuses_Status_id") );
            return Integer.valueOf(get("Statuses_Status_id"));
        } else {
            return -1;
        }
    }

    public int getProject() {
        //    return Integer.valueOf(get("Projects_Project_id"));
        if (!get("Projects_Project_id").equals("null")) {
            // System.out.println("PROJEC ID: " + Integer.valueOf(get("Projects_Project_id")));
            return Integer.valueOf(get("Projects_Project_id"));
        } else {
            return -1;
        }
    }

    public int getContext() {
        if (!get("Contexts_Context_id").equals("null")) {
            return Integer.valueOf(get("Contexts_Context_id"));
        } else {
            return -1;
        }
    }

    public Date getDate() {

        Date date = null;
        //Calender c = new Calendar();
        try {
            Timestamp t = Timestamp.valueOf(get("Action_date"));

            date = new Date(t.getTime());
        } catch (Exception e) {
            return null;
        }



        //date = new Date(get("Action_date"));

        return date;
    }

    public int getDone() {
        return Integer.valueOf(get("Done"));
    }

    //public String getDate() {
    //  return date;
    //}
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
