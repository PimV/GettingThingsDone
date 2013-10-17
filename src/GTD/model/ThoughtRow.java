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

    public String getNotesAsString() {
        String note = "";
        if (!notes.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String s : notes) {
                sb.append(s + ", ");
            }
            note = sb.substring(0, sb.length() - 2);
        }

        return note;
    }

    @Override
    public String toString() {
        return getName();
    }
}
