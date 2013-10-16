/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.controller;

import GTD.View.ActionsPanel;
import GTD.View.ThoughtsPanel;
import GTD.View.ThoughtsPopUp;
import GTD.model.ActionTable;
import GTD.model.Query;
import GTD.model.ThoughtRow;
import GTD.model.ThoughtTable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PimGame
 */
public class MainController {

    private ThoughtsPanel thoughtsPanel;
    private ActionsPanel actionsPanel;
    private ActionTable actions;
    private ThoughtTable thoughts;

    public MainController() {
        actions = new ActionTable();
        thoughts = new ThoughtTable();

    }

    public void addThought(String thought, String notes) {
        //Thought t = new ThoughtRow();
        ThoughtRow t = thoughts.createRow();
        t.setName(thought);
        t.addNote(notes);
        t.save();
        thoughts.fetchAll().add(t);
    }

    public void workThoughtOut(int index) {
        ThoughtsPopUp pop = new ThoughtsPopUp(thoughts.fetchAll().get(index));
        pop.setController(this);
        pop.setVisible(true);

    }

    public void removeThought(int index) {
        thoughts.remove(thoughts.fetchAll().get(index).getID());
    }

    public void setThoughtsPanel(ThoughtsPanel thoughtsPanel) {
        this.thoughtsPanel = thoughtsPanel;
        thoughtsPanel.fillModel(thoughts);
    }

    public void setActionsPanel(ActionsPanel actionsPanel) {
        this.actionsPanel = actionsPanel;
    }
}
