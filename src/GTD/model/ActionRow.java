/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

import java.util.ArrayList;

/**
 *
 * @author PimGame
 */
public class ActionRow {

    private ThoughtRow thoughtOrigin;
    private ArrayList<String> notes = new ArrayList<String>();
    private String description = "No description";
    private Status status;
    private boolean done;
    private String date;
    private String lastChangedDate;

    public ActionRow(ThoughtRow thoughtOrigin) {
        this.thoughtOrigin = thoughtOrigin;
    }

    public void addNote(String note) {
        notes.add(note);
    }

    public void addNotes(ArrayList<String> notes) {
        for (String s : notes) {
            if (!this.notes.contains(s)) {
                this.notes.add(s);
            }
        }
    }

    public void deleteNote(int index) {
        notes.remove(index);
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDone(boolean completed) {
        this.done = completed;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLastChangedDate(String lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getLastChangedDate() {
        return lastChangedDate;
    }

    @Override
    public String toString() {
        String actionString = "";
        actionString += "Action Description: " + description + "\n";
        actionString += "Action Status: " + status + "\n";
        actionString += "Action Date: " + date + "\n";
        actionString += "Last status change: " + lastChangedDate;
        return actionString;
    }
}
