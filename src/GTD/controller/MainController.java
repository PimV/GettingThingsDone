/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.controller;

import GTD.View.ActionsPanel;
import GTD.View.ThoughtsPanel;
import GTD.View.ThoughtsPopUp;
import GTD.model.*;
import java.util.Date;

/**
 *
 * @author PimGame
 */
public class MainController {

    private ThoughtsPanel thoughtsPanel;
    private ActionsPanel actionsPanel;
    private ActionTable actions;
    private ThoughtTable thoughts;
    private ProjectTable projects;
    private ContextTable contexts;
    private StatusTable statuses;

    public MainController() {
        actions = new ActionTable();
        thoughts = new ThoughtTable();
        projects = new ProjectTable();
        contexts = new ContextTable();
        statuses = new StatusTable();
    }

    public void addThought(String thought, String notes) {
        ThoughtRow t = thoughts.createRow();
        t.setName(thought);
        t.addNote(notes);
        t.save();
        thoughts.fetchAll().add(t);
    }

    public void workThoughtOut(int index) {
        ThoughtsPopUp pop = new ThoughtsPopUp();
        pop.setController(this);
        pop.setTitle(thoughts.fetchAll().get(index).getName());
        pop.setThoughtField(thoughts.fetchAll().get(index).getName());
        pop.setStatuses(statuses.fetchAll());
        pop.setProjects(projects.fetchAll());
        pop.setContexts(contexts.fetchAll());
        pop.setNotes(thoughts.fetchAll().get(index).getNotesAsString());
        pop.setIndex(index);
        pop.setVisible(true);
    }

    public void removeThought(int index) {
        thoughts.remove(thoughts.fetchAll().get(index).getID());
        thoughts.fetchAll().remove(index);
        thoughtsPanel.removeFromList(index);
    }

    public void addAction(
            String description, String notes, Date actionDate,
            Date statusChangeDate, boolean done, int contextID,
            int statusID, int projectID, int index) {

        ActionRow a = actions.createRow();
        a.setDate(actionDate);
        a.setLastChangedDate(statusChangeDate);
        a.addNote(notes);
        int numericDone = 0;
        if (done == true) {
            numericDone = 1;
        }
        a.setDone(numericDone);
        a.setDescription(description);

        if (!statuses.fetchAll().isEmpty()) {
            for (StatusRow sr : statuses.fetchAll()) {
                if (sr.getID() == statusID) {
                    a.setStatus(sr.getID());
                    break;
                }
            }
        } else {
            a.setStatus(-1);
        }

        if (!projects.fetchAll().isEmpty()) {
            for (ProjectRow pr : projects.fetchAll()) {
                if (pr.getID() == projectID) {
                    a.setProject(pr.getID());
                    break;
                } else {
                    a.setProject(-1);
                }
            }
        } else {
            a.setProject(-1);
        }

        if (!contexts.fetchAll().isEmpty()) {
            for (ContextRow cr : contexts.fetchAll()) {
                if (cr.getID() == contextID) {
                    a.setContext(cr.getID());
                    break;
                } else {
                    a.setContext(-1);
                }
            }
        } else {
            a.setContext(-1);
        }
        a.save();
        actions.fetchAll().add(a);
        removeThought(index);
    }

    public void setThoughtsPanel(ThoughtsPanel thoughtsPanel) {
        this.thoughtsPanel = thoughtsPanel;
        thoughtsPanel.fillModel(thoughts);
    }

    public void setActionsPanel(ActionsPanel actionsPanel) {
        this.actionsPanel = actionsPanel;
    }
}
