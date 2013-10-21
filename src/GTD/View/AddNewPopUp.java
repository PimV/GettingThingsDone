/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.View;

import GTD.controller.MainController;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author PimGame
 */
@SuppressWarnings("serial")
public class AddNewPopUp extends JFrame {

    private JTextField newField;
    private JButton newButton;
    private JLabel newLabel;
    private final String type;
    private MainController controller;


    public AddNewPopUp(final String type) {

        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            //    throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowClosing(WindowEvent e) {
               
            }

            @Override
            public void windowClosed(WindowEvent e) {
               controller.checkAvailabilityPopUp();
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowIconified(WindowEvent e) {
               // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
               //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
               // throw new UnsupportedOperationException("Not supported yet.");
            }
            
        });

        this.type = type;

        setMinimumSize(new Dimension(250, 150));
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(null);



        newField = new JTextField();
        newField.setBounds(125 - 93, 50 - 20, 180, 20);
        newField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (newField.getText().isEmpty()) {
                    newButton.setEnabled(false);
                } else {
                    if (!newButton.isEnabled()) {
                        newButton.setEnabled(true);
                    }
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (newField.getText().isEmpty()) {
                    newButton.setEnabled(false);
                } else {
                    if (!newButton.isEnabled()) {
                        newButton.setEnabled(true);
                    }
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (newField.getText().isEmpty()) {
                    newButton.setEnabled(false);
                } else {
                    if (!newButton.isEnabled()) {
                        newButton.setEnabled(true);
                    }
                }
            }
        });
        add(newField);

        newLabel = new JLabel(type + ":");
        newLabel.setBounds(125 - 93, 50 - 25 - newField.getHeight(), 180, 20);
        add(newLabel);

        newButton = new JButton("Add new " + type);
        newButton.setBounds(125 - 93, getHeight() - 70, 180, 20);
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (type.equals("Project")) {
                    controller.addProject(newField.getText());
                } else if (type.equals("Context")) {
                    controller.addContext(newField.getText());
                } else if (type.equals("status")) {
                }
                dispose();
            }
        });
        newButton.setEnabled(false);
        add(newButton);

        setTitle("Add new " + type);
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }
}
