package GTD.View;

import GTD.controller.MainController;
import static GTD.model.LayoutConstants.*;
import GTD.model.ProjectRow;
import GTD.model.ProjectTable;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ProjectsPanel extends JPanel {

    private DefaultListModel listModel;
    private JList projectList = new JList();
    private JScrollPane listPane = new JScrollPane();
    private JTextField projectField = new JTextField("Project");
    private JButton[] buttons = new JButton[2];
    private JButton addProjectButton = createButton(0, "Add Project");
    private JButton removeProjectButton = createButton(1, "Remove Project");
    private MainController controller;

    public ProjectsPanel() {

        checkAvailabilityButtons();

        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        listModel = new DefaultListModel();
        projectList.setModel(listModel);
        projectList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                checkAvailabilityButtons();
            }
        });
        listPane.setViewportView(projectList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 672;
        gridBagConstraints.ipady = 328;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(STANDARD_MARGIN_X, STANDARD_MARGIN_X, STANDARD_MARGIN_X, 11);
        add(listPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        add(projectField, gridBagConstraints);


        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 11, STANDARD_MARGIN_X);
        add(addProjectButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 11, STANDARD_MARGIN_X);
        add(removeProjectButton, gridBagConstraints);
    }

    public JButton createButton(final int index, String text) {
        JButton b = new JButton();
        b.setText(text);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (index) {
                    case 0:
                        //ADD CONTEXT;
                        if (!projectField.getText().isEmpty()) {
                            controller.addProject(projectField.getText().trim());
                        } else {
                            JOptionPane.showMessageDialog(null, "No project entered.");
                        }
                        break;
                    case 1:
                        if (projectList.getSelectedIndex() != -1) {
                            int result = JOptionPane.showConfirmDialog(null,
                                    "Are you sure you want to delete this project? It might be connected to an action."
                                    + "\n"
                                    + "\n"
                                    + "Actions with this project will be set to empty project.",
                                    "Are you sure?",
                                    JOptionPane.YES_NO_OPTION);
                            if (result == JOptionPane.OK_OPTION) {
                                controller.removeProject(projectList.getSelectedIndex());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No project selected.");
                        }
                        break;

                }
            }
        });
        if (index < 2 && index >= 0) {
            buttons[index] = b;
        }
        return b;
    }

    public void checkAvailabilityButtons() {
        if (projectList.getSelectedIndex() != -1) {
            buttons[1].setEnabled(true);

        } else {
            buttons[1].setEnabled(false);
        }
    }

    public void addToModel(ProjectRow pr) {
        listModel.addElement(pr);
    }

    public void fillModel(ProjectTable toFill) {
        for (ProjectRow pr : toFill.fetchAll()) {
            listModel.addElement(pr.getName());
        }
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public void removeFromList(int selectedIndex) {
        listModel.remove(selectedIndex);
    }
}
