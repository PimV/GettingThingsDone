package GTD.View;

import GTD.controller.MainController;
import GTD.model.*;
import java.awt.Dimension;
import javax.swing.*;
import org.jdesktop.swingx.JXDatePicker;
import static GTD.model.LayoutConstants.*;
import GTD.view.DocumentSizeFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import javax.swing.text.AbstractDocument;

@SuppressWarnings({"unchecked", "serial"})
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
    private JScrollPane descriptionScroller;
    private JCheckBox doneCheckBox;
    private JButton saveButton;
    private int ID;
    private final boolean save;

    public ThoughtsPopUp(final boolean save) {

        this.save = save;

        setMinimumSize(new Dimension(350, 500));

        setResizable(false);
        setLayout(null);

        JLabel nameLabel = new JLabel("Action Name: ");
        nameLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 0 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(nameLabel);

        thoughtName = new JTextField();
        AbstractDocument thoughtDoc = (AbstractDocument) thoughtName.getDocument();    
        thoughtDoc.setDocumentFilter(new DocumentSizeFilter(45));
        thoughtName.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 0 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);
        thoughtName.setEditable(true);
        thoughtName.setHorizontalAlignment(JTextField.CENTER);
        add(thoughtName);

        JLabel statusLabel = new JLabel("Status: ");
        statusLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 2 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(statusLabel);

        statusBox = new JComboBox();
        statusBox.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 2 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);
        add(statusBox);

        JLabel contextLabel = new JLabel("Context: ");
        contextLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 4 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(contextLabel);

        contextBox = new JComboBox(); 
        contextBox.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 4 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);
        addActionListenerComboBox(contextBox, "Context");
        add(contextBox);

        JLabel projectLabel = new JLabel("Project: ");
        projectLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 6 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(projectLabel);

        projectBox = new JComboBox(); 
        projectBox.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 6 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);
        addActionListenerComboBox(projectBox, "Project");
        add(projectBox);

        JLabel dateLabel = new JLabel("Date: ");
        dateLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 8 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(dateLabel);

        dateBox = new JXDatePicker();
        dateBox.setFormats("dd-MM-yyyy");
        dateBox.getMonthView().setLowerBound(new Date());
        dateBox.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 8 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);

        add(dateBox);

        JLabel dateChangedLabel = new JLabel("Last modified on: ");
        dateChangedLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 10 * LABEL_HEIGHT, 2 * LABEL_WIDTH, LABEL_HEIGHT);
        add(dateChangedLabel);

        dateChangedBox = new JXDatePicker();
        dateChangedBox.setFormats("dd-MM-yyyy");
        dateChangedBox.setDate(new Date());
        dateChangedBox.setEditable(false);
        dateChangedBox.setBounds(getWidth() - 2 * LABEL_WIDTH - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 10 * LABEL_HEIGHT - 2, 2 * LABEL_WIDTH, FIELD_HEIGHT);

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
        AbstractDocument pDoc = (AbstractDocument) descriptionArea.getDocument();        
        pDoc.setDocumentFilter(new DocumentSizeFilter(200));
        descriptionScroller = new JScrollPane(descriptionArea);
        descriptionScroller.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 18 * LABEL_HEIGHT, getWidth() - 2 * STANDARD_MARGIN_X, 6 * LABEL_HEIGHT);
        descriptionScroller.setBorder(BorderFactory.createEtchedBorder());
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        add(descriptionScroller);

        JLabel doneLabel = new JLabel("Done?");
        doneLabel.setBounds(STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 25 * LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        add(doneLabel);

        doneCheckBox = new JCheckBox();
        doneCheckBox.setBounds(getWidth() - 30 - STANDARD_MARGIN_X, STANDARD_MARGIN_Y + 25 * LABEL_HEIGHT - 2, 30, 30);
        add(doneCheckBox);

        String buttonText = "Create action";
        if (!save) {
            buttonText = "Save action";
        }
        saveButton = new JButton(buttonText);
        saveButton.setBounds(getWidth() / 2 - 1 * LABEL_WIDTH, STANDARD_MARGIN_Y + 27 * LABEL_HEIGHT, LABEL_WIDTH * 2, LABEL_HEIGHT * 2);
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //SAVE
                String name = thoughtName.getText().trim();
                if (name.isEmpty()) {
                    name = "No name";
                }
                String description = descriptionArea.getText().trim();
                String notes = notesField.getText().trim();
                Date actionDate = dateBox.getDate();
                Date statusChangeDate = dateChangedBox.getDate();
                boolean done = doneCheckBox.isSelected();

                StatusRow status = (StatusRow) statusBox.getSelectedItem();
                int statusID = -1;
                if (status != null) {
                    statusID = status.getID();
                }

                System.out.println(statusID);
                ProjectRow project = (ProjectRow) projectBox.getSelectedItem();
                int projectID = -1;
                if (project != null) {
                    projectID = project.getID();
                }
                ContextRow context = (ContextRow) contextBox.getSelectedItem();
                int contextID = -1;
                if (context != null) {
                    contextID = context.getID();
                }

                if (save) {
                    controller.addAction(0, name,
                            description, notes, actionDate,
                            statusChangeDate, done, contextID,
                            statusID, projectID, ID);
                } else {
                    controller.addAction(1, name, description, notes, actionDate,
                            statusChangeDate, done, contextID,
                            statusID, projectID, ID);
                }
                dispose();
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

    public void setSelectedStatus(int id) {
        for (int i = 0; i < statusBox.getModel().getSize(); i++) {
            StatusRow sr = (StatusRow) statusBox.getItemAt(i);
            if (sr != null) {
                if (sr.getID() == id) {
                    System.out.println("ID: " + sr.getID() + " INDEX: " + id);
                    statusBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public void setSelectedProject(int id) {
        for (int i = 0; i < projectBox.getModel().getSize() - 1; i++) {
            ProjectRow pr = (ProjectRow) projectBox.getItemAt(i);
            if (pr != null) {
                if (pr.getID() == id) {
                    projectBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public void setSelectedContext(int id) {
        for (int i = 0; i < contextBox.getModel().getSize() - 1; i++) {
            ContextRow cr = (ContextRow) contextBox.getItemAt(i);
            if (cr != null) {
                if (cr.getID() == id) {
                    contextBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public void addActionListenerComboBox(final JComboBox comboBox, final String type) {
        comboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (comboBox.getSelectedIndex() == comboBox.getItemCount() - 1) {
                    comboBox.setSelectedIndex(0);

                    controller.showAddNewPopUp(type);
                    controller.checkAvailabilityPopUp();
                }
            }
        });
    }

    public void setActionName(String name) {
        thoughtName.setText(name);
    }

    public void setDate(Date date) {
        dateBox.setDate(date);
    }

    public void setDescription(String description) {
        descriptionArea.setText(description);
    }

    public void setDone(int done) {
        boolean isDone = false;
        if (done == 1) {
            isDone = true;
        }
        doneCheckBox.setSelected(isDone);
    }

    public void setIndex(int index) {
        this.ID = index;
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }
}
