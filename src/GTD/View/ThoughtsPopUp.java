/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.View;

import GTD.controller.MainController;
import GTD.model.*;
import java.awt.Dimension;
import javax.swing.*;
import org.jdesktop.swingx.JXDatePicker;
import static GTD.model.LayoutConstants.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 *
 * @author PimGame
 */
public class ThoughtsPopUp extends JFrame {

    private MainController controller;
    private JTextField thoughtName;
    private JComboBox statusBox;
    private JComboBox contextBox;
    private JComboBox projectBox;
    private JXDatePicker dateBox;
    private JXDatePicker dateChangedBox;
    private JTextField notesField;
    private JTextArea descriptionArea;
    private JCheckBox doneCheckBox;
    private JButton saveButton;

    public ThoughtsPopUp() {

        setMinimumSize(new Dimension(350, 500));

        setResizable(false);
        setLayout(null);

        JLabel nameLabel = new JLabel("Thought: ");
        nameLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 0 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(nameLabel);

        thoughtName = new JTextField();
        thoughtName.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 0 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);
        thoughtName.setEditable(false);
        thoughtName.setHorizontalAlignment(JTextField.CENTER);
        add(thoughtName);

        JLabel statusLabel = new JLabel("Status: ");
        statusLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 2 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(statusLabel);

        statusBox = new JComboBox(Status.values());
        statusBox.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 2 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);
        add(statusBox);

        JLabel contextLabel = new JLabel("Context: ");
        contextLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 4 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(contextLabel);

        contextBox = new JComboBox(); //CONTEXTEN MOETEN NOG GEVULD WORDEN!!
        contextBox.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 4 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);
        add(contextBox);

        JLabel projectLabel = new JLabel("Project: ");
        projectLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 6 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(projectLabel);

        projectBox = new JComboBox(); //PROJECTEN MOETEN NOG GEVULD WORDEN!!
        projectBox.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 6 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);
        add(projectBox);

        JLabel dateLabel = new JLabel("Date: ");
        dateLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 8 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(dateLabel);

        dateBox = new JXDatePicker();
        dateBox.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 8 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);
        add(dateBox);

        JLabel dateChangedLabel = new JLabel("Status Date Changed: ");
        dateChangedLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 10 * LABEL_HEIGHT, 2 * LABEL_WIDTH, LABEL_HEIGHT);
        add(dateChangedLabel);

        dateChangedBox = new JXDatePicker();
        dateChangedBox.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 10 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);
        dateChangedBox.setDate(new Date());
        add(dateChangedBox);

        JLabel notesLabel = new JLabel("Notes: ");
        notesLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 12 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(notesLabel);

        notesField = new JTextField();
        notesField.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 14 * LABEL_HEIGHT, getWidth() - 2 * STANDARD_MARGIN_X, (int) (1.5 * LABEL_HEIGHT));
        add(notesField);

        JLabel descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 16 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(descriptionLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 18 * LABEL_HEIGHT, getWidth() - 2 * STANDARD_MARGIN_X, 6 * LABEL_HEIGHT);
        descriptionArea.setBorder(BorderFactory.createEtchedBorder());
        add(descriptionArea);

        JLabel doneLabel = new JLabel("Done?");
        doneLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 25 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(doneLabel);

        doneCheckBox = new JCheckBox();
        doneCheckBox.setBounds(getWidth() - 30 - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 25 * LABEL_HEIGHT - 2, 30, 30);
        add(doneCheckBox);

        saveButton = new JButton("Create action");
        saveButton.setBounds(getWidth() / 2 - 1 * LABEL_WIDTH, STANDARD_MARGIN_Y + 27 * LABEL_HEIGHT, LABEL_WIDTH * 2, LABEL_HEIGHT * 2);
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkIfComplete()) {
                    int response = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to save?",
                            "",
                            JOptionPane.OK_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    if (response == 0) {
                        //SAVE
                        StatusRow status = (StatusRow) statusBox.getSelectedItem();
                        ProjectRow project = (ProjectRow) projectBox.getSelectedItem();
                        ContextRow context = (ContextRow) contextBox.getSelectedItem();
                        


                        //FINALLY:
                        controller.addAction();
                    } else {
                        //DOE NIETS
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "More information needed!");
                }

            }
        });
        add(saveButton);



        setLocationRelativeTo(null);
    }

    public boolean checkIfComplete() {
        if (statusBox.getSelectedIndex() != -1) {
            return true;
        }
        return false;
    }

    public void setThoughtField(String name) {
        thoughtName.setText(name);
    }

    public void setNotes(String notes) {
        notesField.setText(notes);
    }

    public void setStatuses(final StatusRowset sr) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        model.addElement(null);

        for (StatusRow s : sr) {
            model.addElement(s);
        }

        model.addElement("New status...");

        statusBox.setModel(model);
    }

    public void setProjects(final ProjectRowset pr) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        model.addElement(null);

        for (ProjectRow p : pr) {
            model.addElement(p);
        }

        model.addElement("New project...");

        projectBox.setModel(model);
    }

    public void setContexts(final ContextRowset cr) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        model.addElement(null);

        for (ContextRow c : cr) {
            model.addElement(c);
        }

        model.addElement("New context...");

        contextBox.setModel(model);
    }

    /*
     * public void setContexts(ContextRowset cr) {
     *
     * }
     */
    public void setController(MainController controller) {
        this.controller = controller;
    }
}
