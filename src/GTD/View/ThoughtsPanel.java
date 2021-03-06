package GTD.View;

import GTD.controller.MainController;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

import static GTD.model.LayoutConstants.*;
import GTD.model.ThoughtRow;
import GTD.model.ThoughtTable;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AbstractDocument;

@SuppressWarnings({"serial", "unchecked"})
public final class ThoughtsPanel extends JPanel {

    private MainController controller;
    private DefaultListModel listModel;
    private JList thoughtList = new JList();
    private JScrollPane listPane = new JScrollPane();
    private JTextField thoughtInputField = new JTextField();
    private JTextArea notesInputField = new JTextArea();
    private JScrollPane notesScroller = new JScrollPane();
    private JScrollPane thoughtScroller = new JScrollPane();
    private JButton[] buttons = new JButton[3];
    private JButton addThoughtButton = createButton(0, "Add thought"); //0
    private JButton workThoughtButton = createButton(1, "Work thought out!"); //1
    private JButton removeThoughtButton = createButton(2, "Remove thought"); //2

    public ThoughtsPanel() {

        checkAvailabilityButtons();
        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        listModel = new DefaultListModel();
        thoughtList.setModel(listModel);
        thoughtList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                checkAvailabilityButtons();
            }
        });
        listPane.setViewportView(thoughtList);

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
        gridBagConstraints.insets = new java.awt.Insets(STANDARD_MARGIN_X, STANDARD_MARGIN_X, 0, 11);
        add(listPane, gridBagConstraints);

        thoughtInputField.setText("Thought");
        AbstractDocument thoughtDoc = (AbstractDocument) thoughtInputField.getDocument();
        thoughtDoc.setDocumentFilter(new GTD.View.DocumentSizeFilter(45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        thoughtScroller = new JScrollPane(thoughtInputField);
        thoughtScroller.setPreferredSize(new Dimension(0, 0));
        add(thoughtInputField, gridBagConstraints);

        notesInputField.setText("Notes");
        notesInputField.setLineWrap(true);
        notesInputField.setWrapStyleWord(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        notesScroller = new JScrollPane(notesInputField);
        notesScroller.setPreferredSize(new Dimension(0, 0));
        add(notesScroller, gridBagConstraints);


        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 11, STANDARD_MARGIN_X);
        add(addThoughtButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, STANDARD_MARGIN_X, 11, 0);
        add(workThoughtButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 11, STANDARD_MARGIN_X);
        add(removeThoughtButton, gridBagConstraints);


    }

    public JButton createButton(final int index, String text) {
        JButton b = new JButton();
        b.setText(text);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (index) {
                    case 0:
                        String thoughtName = thoughtInputField.getText();
                        String notes = notesInputField.getText();
                        if (!listModel.contains(thoughtName)) {
                            controller.addThought(thoughtName, notes);
                            listModel.addElement(thoughtName);
                            notesInputField.setText("Notes");
                            thoughtInputField.setText("Thought");
                        } else {
                            JOptionPane.showMessageDialog(null, "This thought already exists!");
                        }
                        break;

                    case 1:
                        controller.workThoughtOut(thoughtList.getSelectedIndex());
                        break;
                    case 2:
                        controller.removeThought(thoughtList.getSelectedIndex());
                        break;
                }
            }
        });
        if (index < 3 && index >= 0) {
            buttons[index] = b;
        }
        return b;
    }

    public void checkAvailabilityButtons() {
        if (thoughtList.getSelectedIndex() != -1) {
            buttons[1].setEnabled(true);
            buttons[2].setEnabled(true);
        } else {
            buttons[1].setEnabled(false);
            buttons[2].setEnabled(false);
        }
    }

    public void removeFromList(int index) {
        listModel.remove(index);
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public void fillModel(ThoughtTable thoughts) {
        listModel.clear();
        for (ThoughtRow t : thoughts.fetchAll()) {
            listModel.addElement(t.getName());
        }
    }
}
