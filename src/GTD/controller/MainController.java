package GTD.controller;

import GTD.View.*;
import GTD.model.*;
import java.util.Date;

public class MainController {

    private MainFrame mainFrame;
    private ThoughtsPanel thoughtsPanel;
    private ActionsPanel actionsPanel;
    private ActionTable actions;
    private ThoughtTable thoughts;
    private ProjectTable projects;
    private ContextTable contexts;
    private StatusTable statuses;
    private ThoughtsPopUp pop;
    private AddNewPopUp anpu;
    private ContextPanel contextsPanel;
    private ProjectsPanel projectsPanel;

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
        pop = new ThoughtsPopUp(true);
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

    public void removeAction(int index) {
        actions.remove(actions.fetchAll().get(index).getID());
        actions.fetchAll().remove(index);
        showActions();
    }

    /*
     * Type = 0 if new action Type = 1 if action is being edited
     */
    public void addAction(int type,
            String name, String description, String notes, Date actionDate,
            Date statusChangeDate, boolean done, int contextID,
            int statusID, int projectID, int actionID) {
        ActionRow a = null;
        if (type == 0) {
            a = actions.createRow();
        } else {
            for (ActionRow ar : actions.fetchAll()) {
                if (ar.getID() == actionID) {
                    System.out.println("ACTION FOUND");
                    a = ar;
                    break;
                }
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
        if (type == 1) {
            a.setID(actionID);
        }
        a.save();

        if (type == 0) {
            actions.fetchAll().add(a);
            removeThought(actionID);
        }
        showActions();
    }

    public void showActions() {
        actions.setStatuses(statuses);
        actions.setProjects(projects);
        actions.setContexts(contexts);
        actionsPanel.setTableModel(actions);
    }

    public void showEditPopup(int ID) {
        ActionRow selectedAction = null;
        for (ActionRow ar : actions.fetchAll()) {
            if (ar.getID() == ID) {
                selectedAction = ar;
                break;
            }
        }


        pop = new ThoughtsPopUp(false);
        pop.setController(this);

        pop.setStatuses(statuses.fetchAll());
        pop.setProjects(projects.fetchAll());
        pop.setContexts(contexts.fetchAll());
        pop.setActionName(selectedAction.getName());

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

    public void checkAvailabilityPopUp() {
//        System.out.println("ENABLE POP");
//        if (anpu.isShowing()) {
//            pop.setEnabled(false);
//        } else {
//            pop.setEnabled(true);
//            pop.toFront();
//        }
    }

    public void showAddNewPopUp(String type) {
        System.out.println("ADD POPUP TYPE: " + type);
        if (anpu != null) {
            anpu.dispose();
        }
        anpu = new AddNewPopUp(type);
        anpu.setController(this);
        anpu.setVisible(true);
    }

    public void addContext(String contextName) {
        ContextRow cr = contexts.createRow();
        cr.setName(contextName);
        cr.save();
        contexts.fetchAll().add(cr);
        if (pop != null) {
            pop.setContexts(contexts.fetchAll());
            pop.setSelectedContext(cr.getID());
        }
        contextsPanel.addToModel(cr);
    }

    public void addProject(String projectName) {
        ProjectRow pr = projects.createRow();
        pr.setName(projectName);
        pr.save();
        projects.fetchAll().add(pr);
        if (pop != null) {
            pop.setProjects(projects.fetchAll());
            pop.setSelectedProject(pr.getID());
        }
        projectsPanel.addToModel(pr);
   }

    public void removeContext(int selectedIndex) {
        int contextID = contexts.fetchAll().get(selectedIndex).getID();
        for (ActionRow ar : actions.fetchAll()) {
            if (ar.getContext() == contextID) {
                ar.setContext(-1);
                ar.save();
            }
        }

        contexts.remove(contextID);
        contexts.fetchAll().remove(selectedIndex);
        contextsPanel.removeFromList(selectedIndex);
    }

    public void removeProject(int selectedIndex) {
        int projectID = projects.fetchAll().get(selectedIndex).getID();
        for (ActionRow ar : actions.fetchAll()) {
            if (ar.getProject() == projectID) {
                ar.setProject(-1);
                ar.save();
            }
        }

        projects.remove(projectID);
        projects.fetchAll().remove(selectedIndex);
        projectsPanel.removeFromList(selectedIndex);
    }

    public void setThoughtsPanel(ThoughtsPanel thoughtsPanel) {
        this.thoughtsPanel = thoughtsPanel;
        thoughtsPanel.fillModel(thoughts);
    }

    public void setActionsPanel(ActionsPanel actionsPanel) {
        this.actionsPanel = actionsPanel;
    }

    public void setContextPanel(ContextPanel contextPanel) {
        this.contextsPanel = contextPanel;
        contextPanel.fillModel(contexts);
    }

    public void setProjectsPanel(ProjectsPanel projectsPanel) {
        this.projectsPanel = projectsPanel;
        projectsPanel.fillModel(projects);
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
        actionsPanel.filterStatus(0);
    }

    public void statusfilter2Action() {
        actionsPanel.filterStatus(1);
    }

    public void statusfilter3Action() {
        actionsPanel.filterStatus(2);
    }

    public void statusfilter4Action() {
        actionsPanel.filterStatus(3);
    }

    public void statusfilter5Action() {
        actionsPanel.filterStatus(4);
    }
    
    public void filterDoneAction() {
        actionsPanel.filterDone();
    }
}
