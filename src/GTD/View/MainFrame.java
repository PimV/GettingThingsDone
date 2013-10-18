/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.View;

import GTD.controller.MainController;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author PimGame
 */
public class MainFrame extends JFrame {

    //Menubar stuff
    private JMenuBar menuBar;
    private JMenu fileMenu, actionMenu, actionFilterOptions, filterOption3;
    private JMenuItem newThought, printThoughts, printActions, quit;
    private JCheckBoxMenuItem statusfilter1, statusfilter2, statusfilter3, statusfilter4, statusfilter5;
    private JCheckBoxMenuItem doneFilter, contextFilter, projectFilter;
    //TabbedPanel
    private JTabbedPane tabbedPanel;
    //Panels
    private ActionsPanel actionsPanel;
    private ThoughtsPanel thoughtsPanel;
    //controller
    private MainController controller;

    public MainFrame() {

        setMinimumSize(new Dimension(700, 450)); //Sets the minimum size to a width of 700 and a height of 450.


        createMenuBar();     //Initializes the JMenuBar

        tabbedPanel = new JTabbedPane(); //Initializes the JTabbedPane

        actionsPanel = new ActionsPanel(); //Creates the JPanel containing the Actions.
        thoughtsPanel = new ThoughtsPanel(); //Creates the JPanel containing the Thoughts.

        //Add the JPanels to the JTabbedPane
        tabbedPanel.add("Actions", actionsPanel);
        tabbedPanel.add("Thoughts", thoughtsPanel);
        tabbedPanel.setSelectedIndex(0);
        tabbedPanel.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                actionsPanel.clearTableSelection();
            }
        });
        //Add the JTabbedPane to the JFrame
        add(tabbedPanel);

        //Set default close operation of the JFrame to exit when closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Centers the JFrame on startup.
        setLocationRelativeTo(null);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar); //Sets the JMenuBar for the JFrame.

        // file menu gets created here
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        newThought = new JMenuItem("New thought");
        fileMenu.add(newThought);
        newThought.addActionListener(createListener(0));

        printThoughts = new JMenuItem("Print thoughts");
        fileMenu.add(printThoughts);
        printThoughts.addActionListener(createListener(1));

        printActions = new JMenuItem("Print Actions");
        fileMenu.add(printActions);
        printActions.addActionListener(createListener(2));

        quit = new JMenuItem("Quit");
        fileMenu.add(quit);
        quit.addActionListener(createListener(3));

        //actionMenu gets created here
        actionMenu = new JMenu("Actions");
        menuBar.add(actionMenu);

        actionFilterOptions = new JMenu("Filters"); //Adds another JMenu to a different JMenu to create a submenu
        actionMenu.add(actionFilterOptions);

        doneFilter = new JCheckBoxMenuItem("Hide finished actions"); //Add a JCheckBoxMenuItem for filtering.
        actionFilterOptions.add(doneFilter);
        doneFilter.addActionListener(createListener(11));
        
        contextFilter = new JCheckBoxMenuItem("Show actions with context"); //Add a JCheckBoxMenuItem for filtering.
        actionFilterOptions.add(contextFilter);
        contextFilter.addActionListener(createListener(4));

        projectFilter = new JCheckBoxMenuItem("Show actions with project"); //Add a JCheckBoxMenuItem for filtering.
        actionFilterOptions.add(projectFilter);
        projectFilter.addActionListener(createListener(5));



        filterOption3 = new JMenu("Show actions with status:");
        actionFilterOptions.add(filterOption3);

        statusfilter1 = new JCheckBoxMenuItem("Information");
        filterOption3.add(statusfilter1);
        statusfilter1.addActionListener(createListener(6));


        statusfilter2 = new JCheckBoxMenuItem("Postponed");
        filterOption3.add(statusfilter2);
        statusfilter2.addActionListener(createListener(7));


        statusfilter3 = new JCheckBoxMenuItem("Delegated");
        filterOption3.add(statusfilter3);
        statusfilter3.addActionListener(createListener(8));


        statusfilter4 = new JCheckBoxMenuItem("Do ASAP");
        filterOption3.add(statusfilter4);
        statusfilter4.addActionListener(createListener(9));


        statusfilter5 = new JCheckBoxMenuItem("Planned");
        filterOption3.add(statusfilter5);
        statusfilter5.addActionListener(createListener(10));

        //        filterOption3 = new JCheckBoxMenuItem("Hide done actions"); //Add a JCheckBoxMenuItem for filtering.
        //        actionFilterOptions.add(filterOption3);

    }

    public ActionListener createListener(final int type) {
        ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (type) {
                    case 0:
                        controller.newThoughtAction();
                        break;

                    case 1:
                        controller.printThoughtsAction();
                        break;

                    case 2:
                        controller.printActionsAction();
                        break;

                    case 3:
                        controller.quitAction();
                        break;

                    case 4:
                        controller.contextFilterAction();
                        break;

                    case 5:
                        controller.projectFilterAction();
                        break;

                    case 6:
                        controller.statusfilter1Action();
                        break;
                    case 7:
                        controller.statusfilter2Action();
                        break;

                    case 8:
                        controller.statusfilter3Action();
                        break;

                    case 9:
                        controller.statusfilter4Action();
                        break;

                    case 10:
                        controller.statusfilter5Action();
                        break;
                    case 11:
                        controller.filterOption0Action();
                        break;
                }
            }
        };
        return al;
    }

    public ActionsPanel getActionsPanel() {
        return actionsPanel;
    }

    public ThoughtsPanel getThoughtsPanel() {
        return thoughtsPanel;
    }

    public void setActivePane(int index) {
        System.out.println(tabbedPanel.getTabCount());
        tabbedPanel.setSelectedIndex(index);
    }

    public void setController(MainController controller) {
        this.controller = controller;
        thoughtsPanel.setController(controller);
        actionsPanel.setController(controller);
    }
}
