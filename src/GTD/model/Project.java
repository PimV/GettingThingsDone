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
public class Project {

    private ArrayList<String> notes = new ArrayList<String>();
    private String name;

    public Project(String name) {
        this.name = name;
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

    public String getName() {
        return name;
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
