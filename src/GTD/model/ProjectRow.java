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
public class ProjectRow extends DbRow {

    private ArrayList<String> notes;
    private String name;

    public ProjectRow() {
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

    public String getNotes() {
        String noteString = "";
        for (String s : notes) {
            noteString += s + ", ";
        }
        noteString = noteString.substring(0, noteString.length() - 2);
        return noteString;
    }

    public void deleteNote(int index) {
        notes.remove(index);
    }

    public String getName() {
        return get("Name");
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
        String projectString = "";
        projectString += "Project name: " + name + "\n";
        projectString += "Project notes: ";
        for (String s : notes) {
            projectString += s;
            if (s != notes.get(notes.size() - 1)) {
                projectString += ", ";
            }
        }
        return projectString;
    }
}
