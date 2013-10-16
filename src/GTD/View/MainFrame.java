/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.View;

import GTD.controller.MainController;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author PimGame
 */
public class MainFrame extends JFrame {

    //Menubar stuff
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu actionMenu;
    private JMenu actionFilterOptions;
    private JCheckBoxMenuItem filterOption1;
    private JCheckBoxMenuItem filterOption2;
    private JCheckBoxMenuItem filterOption3;
    //TabbedPanel
    private JTabbedPane tabbedPanel;
    //Panels
    private ActionsPanel actionsPanel;
    private ThoughtsPanel thoughtsPanel;

    public MainFrame() {

        setMinimumSize(new Dimension(700, 450)); //Sets the minimum size to a width of 700 and a height of 450.

        //Initializes the JMenuBar
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        actionMenu = new JMenu("Actions");
        actionFilterOptions = new JMenu("Filter options"); //Adds another JMenu to a different JMenu to create a submenu
        filterOption1 = new JCheckBoxMenuItem("Filter 1"); //Add a JCheckBoxMenuItem for filtering.
        filterOption2 = new JCheckBoxMenuItem("Filter 2"); //Add a JCheckBoxMenuItem for filtering.
        filterOption3 = new JCheckBoxMenuItem("Filter 3"); //Add a JCheckBoxMenuItem for filtering.
        actionFilterOptions.add(filterOption1);
        actionFilterOptions.add(filterOption2);
        actionFilterOptions.add(filterOption3);
        actionMenu.add(actionFilterOptions); //Add the submenu for filtering options to the actionMenu.

        menuBar.add(fileMenu);
        menuBar.add(actionMenu);

        setJMenuBar(menuBar); //Sets the JMenuBar for the JFrame.

        tabbedPanel = new JTabbedPane(); //Initializes the JTabbedPane

        actionsPanel = new ActionsPanel(); //Creates the JPanel containing the Actions.
        thoughtsPanel = new ThoughtsPanel(); //Creates the JPanel containing the Thoughts.

        //Add the JPanels to the JTabbedPane
        tabbedPanel.add("Actions", actionsPanel);
        tabbedPanel.add("Thoughts", thoughtsPanel);

        //Add the JTabbedPane to the JFrame
        add(tabbedPanel);

        //Set default close operation of the JFrame to exit when closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Centers the JFrame on startup.
        setLocationRelativeTo(null);
    }

    public ActionsPanel getActionsPanel() {
        return actionsPanel;
    }

    public ThoughtsPanel getThoughtsPanel() {
        return thoughtsPanel;
    }
    
    public void setController(MainController controller) {
        thoughtsPanel.setController(controller);
        actionsPanel.setController(controller);
    }
}
