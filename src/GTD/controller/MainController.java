/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.controller;

import GTD.View.ActionsPanel;
import GTD.View.ThoughtsPanel;
import GTD.View.ThoughtsPopUp;
import GTD.model.*;

/**
 *
 * @author PimGame
 */
public class MainController
  {

    private ThoughtsPanel thoughtsPanel;
    private ActionsPanel actionsPanel;
    private ActionTable actions;
    private ThoughtTable thoughts;
    private ProjectTable projects;
    private ContextTable contexts;
    private StatusTable statuses;

    public MainController()
      {
        actions = new ActionTable();
        thoughts = new ThoughtTable();
        projects = new ProjectTable();
        contexts = new ContextTable();
        statuses = new StatusTable();


      }

    public void addThought(String thought, String notes)
      {
        ThoughtRow t = thoughts.createRow();
        t.setName(thought);
        t.addNote(notes);
        t.save();
        thoughts.fetchAll().add(t);
      }

    public void workThoughtOut(int index)
      {
        ThoughtsPopUp pop = new ThoughtsPopUp();
        pop.setController(this);
        pop.setTitle(thoughts.fetchAll().get(index).getName());
        pop.setThoughtField(thoughts.fetchAll().get(index).getName());
        pop.setStatuses(statuses.fetchAll());
        pop.setProjects(projects.fetchAll());
        pop.setContexts(contexts.fetchAll());
        pop.setNotes(thoughts.fetchAll().get(index).getNotesAsString());
        pop.setVisible(true);
      }

    public void removeThought(int index)
      {
        System.out.println("Index: " + index + " ID: " + thoughts.fetchAll().get(index).getID());
        thoughts.remove(thoughts.fetchAll().get(index).getID());
        thoughts.fetchAll().remove(index);
      }

    public void addAction()
      {
        System.out.println("GA SAVEN, KUT!");
      }

    public void setThoughtsPanel(ThoughtsPanel thoughtsPanel)
      {
        this.thoughtsPanel = thoughtsPanel;
        thoughtsPanel.fillModel(thoughts);
      }

    public void setActionsPanel(ActionsPanel actionsPanel)
      {
        this.actionsPanel = actionsPanel;
      }

    //alle menubar option actions
    public void newThoughtAction()
      {
        
      }

    public void printThoughtsAction()
      {
      }

    public void printActionsAction()
      {
      }

    public void quitAction()
      {
        System.exit(0);
      }

    public void filterOption1Action()
      {
      }

    public void filterOption2Action()
      {
      }

    public void statusfilter1Action()
      {
      }

    public void statusfilter2Action()
      {
      }

    public void statusfilter3Action()
      {
      }

    public void statusfilter4Action()
      {
      }

    public void statusfilter5Action()
      {
      }
  }
