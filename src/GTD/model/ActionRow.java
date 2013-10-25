package GTD.model;

import java.sql.Date;
import java.util.ArrayList;

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

    public void setName(String name) {
        set("Name", name);
    }

    public String getName() {
        return get("Name");
    }

    public void setDescription(String description) {
        this.description = description;
        set("Description", description);
    }

    public void setStatus(int status) {
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
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            set("Action_date", sqlDate + "");
        } else {
            set("Action_date", "null");
        }
    }

    public void setLastChangedDate(java.util.Date lastChangedDate) {
        if (lastChangedDate != null) {
            java.sql.Date sqlDate = new java.sql.Date(lastChangedDate.getTime());
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
        if (!get("Statuses_Status_id").equals("null")) {
            return Integer.valueOf(get("Statuses_Status_id"));
        } else {
            return -1;
        }
    }

    public int getProject() {
        if (!get("Projects_Project_id").equals("null")) {
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
        try {
            Date t = Date.valueOf(get("Action_date"));

            date = new Date(t.getTime());


        } catch (Exception e) {
            return null;
        }
        return date;
    }

    public int getDone() {
        return Integer.valueOf(get("Done"));
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
