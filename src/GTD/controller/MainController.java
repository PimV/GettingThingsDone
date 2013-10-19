/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.controller;

import GTD.View.ActionsPanel;
import GTD.View.MainFrame;
import GTD.View.ThoughtsPanel;
import GTD.View.ThoughtsPopUp;
import GTD.View.MainFrame;
import GTD.model.*;
import java.util.Date;

/**
 *
 * @author PimGame
 */
public class MainController {

    private MainFrame mainFrame;
    private ThoughtsPanel thoughtsPanel;
    private ActionsPanel actionsPanel;
    private ActionTable actions;
    private ThoughtTable thoughts;
    private ProjectTable projects;
    private ContextTable contexts;
    private StatusTable statuses;

    public MainController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
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
        ThoughtsPopUp pop = new ThoughtsPopUp(true);
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
            String name, String description, String notes, Date actionDate,
            Date statusChangeDate, boolean done, int contextID,
            int statusID, int projectID, int index) {

        ActionRow a = actions.createRow();
        a.setName(name);
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
                } else {
                    a.setStatus(-1);
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
        System.out.println("REMOVE INDEX: " + index);
        removeThought(index);
        showActions();
    }

    public void showActions() {
        actions.setStatuses(statuses);
        actions.setProjects(projects);
        actions.setContexts(contexts);
        actionsPanel.setTableModel(actions);
    }

    public void editAction(int ID, String name, String description, String notes, Date actionDate,
            Date statusChangeDate, boolean done, int contextID,
            int statusID, int projectID, int index) {

        ActionRow a = null;
        System.out.println("ID IS: " + ID);
        for (ActionRow ar : actions.fetchAll()) {
            if (ar.getID() == ID) {
                System.out.println("ACTION FOUND");
                a = ar;
                break;
            }
        }
        a.setName(name);

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
                } else {
                    a.setStatus(-1);
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
        System.out.println("ID IN MAINCONTROLLER IS: " + ID);
        a.setID(ID);
        a.save();
        System.out.println("REMOVE INDEX: " + index);
        //removeThought(index);
        showActions();
    }

    public void showEditPopup(int ID) {
        ActionRow selectedAction = null;
        for (ActionRow ar : actions.fetchAll()) {
            if (ar.getID() == ID) {
                selectedAction = ar;
                break;
            }
        }


        ThoughtsPopUp pop = new ThoughtsPopUp(false);
        pop.setController(this);
        pop.setStatuses(statuses.fetchAll());
        pop.setProjects(projects.fetchAll());
        pop.setContexts(contexts.fetchAll());
        if (selectedAction.getDate() != null) {
            pop.setDate(selectedAction.getDate());
        } else {
        }
        selectedAction.getDate();
        pop.setSelectedStatus(selectedAction.getStatus());
        pop.setSelectedContext(selectedAction.getContext());
        pop.setSelectedProject(selectedAction.getProject());
        pop.setDescription(selectedAction.getDescription());
        pop.setNotes(selectedAction.getNotesAsString());
        pop.setDone(selectedAction.getDone());
        pop.setIndex(ID);
        pop.setVisible(true);

    }

    public void setThoughtsPanel(ThoughtsPanel thoughtsPanel) {
        this.thoughtsPanel = thoughtsPanel;
        thoughtsPanel.fillModel(thoughts);
    }

    public void setActionsPanel(ActionsPanel actionsPanel) {
        this.actionsPanel = actionsPanel;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    //alle menubar option actions
    public void newThoughtAction() {
        mainFrame.setActivePane(1);
    }

    public void printThoughtsAction() {
    }

    public void printActionsAction() {
    }

    public void quitAction() {
        System.exit(0);
    }

    public void contextFilterAction() {
        actionsPanel.filterContext();
    }

    public void projectFilterAction() {
        actionsPanel.filterProject();
    }

    public void statusfilter1Action() {
        actionsPanel.filterStatus1();
    }

    public void statusfilter2Action() {
        actionsPanel.filterStatus2();
    }

    public void statusfilter3Action() {
        actionsPanel.filterStatus3();
    }

    public void statusfilter4Action() {
        actionsPanel.filterStatus4();
    }

    public void statusfilter5Action() {
        actionsPanel.filterStatus5();
    }

    public void filterOption0Action() {
        actionsPanel.filterDone();
    }
}
