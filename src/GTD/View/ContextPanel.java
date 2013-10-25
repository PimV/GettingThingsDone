/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.View;

import GTD.controller.MainController;
import GTD.model.ContextRow;
import GTD.model.ContextTable;
import static GTD.model.LayoutConstants.*;
import GTD.model.ProjectRow;
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

/**
 *
 * @author Pim
 */
public class ContextPanel extends JPanel {

    private DefaultListModel listModel;
    private JList contextList = new JList();
    private JScrollPane listPane = new JScrollPane();
    private JTextField contextField = new JTextField("Context");
    private JButton[] buttons = new JButton[2];
    private JButton addContextButton = createButton(0, "Add Context");
    private JButton removeContextButton = createButton(1, "Remove Context");
    private MainController controller;

    public ContextPanel() {

        checkAvailabilityButtons();

        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        listModel = new DefaultListModel();
        contextList.setModel(listModel);
        contextList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                checkAvailabilityButtons();
            }
        });
        listPane.setViewportView(contextList);

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
        add(contextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 11, STANDARD_MARGIN_X);
        add(addContextButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 11, STANDARD_MARGIN_X);
        add(removeContextButton, gridBagConstraints);
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
                        if (!contextField.getText().isEmpty()) {
                            controller.addContext(contextField.getText().trim());
                        } else {
                            JOptionPane.showMessageDialog(null, "No project entered.");
                        }
                        break;
                    case 1:
                        if (contextList.getSelectedIndex() != -1) {
                            int result = JOptionPane.showConfirmDialog(null,
                                    "Are you sure you want to delete this context? It might be connected to an action."
                                    + "\n"
                                    + "\n"
                                    + "Actions with this context will be set to empty context.",
                                    "Are you sure?",
                                    JOptionPane.YES_NO_OPTION);
                            if (result == JOptionPane.OK_OPTION) {
                                controller.removeContext(contextList.getSelectedIndex());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No context selected.");
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
        if (contextList.getSelectedIndex() != -1) {
            buttons[1].setEnabled(true);

        } else {
            buttons[1].setEnabled(false);

        }
    }

    public void addToModel(ContextRow cr) {
        listModel.addElement(cr);
    }

    public void fillModel(ContextTable toFill) {
        for (ContextRow cr : toFill.fetchAll()) {
            listModel.addElement(cr.getName());
        }
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public void removeFromList(int selectedIndex) {
        listModel.remove(selectedIndex);
    }
}
