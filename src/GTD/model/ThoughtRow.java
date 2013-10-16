/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

import GTD.controller.DatabaseController;
import java.util.ArrayList;

/**
 *
 * @author PimGame
 */
public class ThoughtRow extends DbRow {

    private ArrayList<String> notes;

    public ThoughtRow() {
        notes = new ArrayList<String>();
    }

    public void setName(String name) {
        set("Name", name);

    }

    public void addNote(String note) {
        notes.add(note);
        set("Notes", getNotesAsString());

    }

    public void addNotes(ArrayList<String> notes) {
        for (String s : notes) {
            if (!this.notes.contains(s)) {
                this.notes.add(s);
            }
        }
        set("Notes", getNotesAsString());
    }

    public void deleteNote(int index) {
        notes.remove(index);

    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;

    }

    public String getName() {
        return get("Name");
    }

    private String getNotesAsString() {
        return "Henk,Jan";
    }
}
