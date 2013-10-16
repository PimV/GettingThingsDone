/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.View;

import GTD.controller.MainController;
import GTD.model.ThoughtRow;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author PimGame
 */
public class ThoughtsPopUp extends JFrame {

    private MainController controller;
    private ThoughtRow currentThought;

    public ThoughtsPopUp(ThoughtRow thought) {

        setMinimumSize(new Dimension(430, 350));

        currentThought = thought;

        setTitle("Working out - " + thought.getName());





        setLocationRelativeTo(null);
    }


    public void setController(MainController controller) {
        this.controller = controller;
    }
}
//        SpringLayout layout = new SpringLayout();
//        setLayout(layout);
//
//        String[] labels = {"ThoughtRow: ", "Status: ", "Context: ", "Project: ", "Action Date: ", "Status Changed Date: "};
//        int numPairs = labels.length;
//        JPanel p = new JPanel(new SpringLayout());
//        for (int i = 0; i < numPairs; i++) {
//            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
//            p.add(l);
//            JTextField textField = new JTextField(10);
//            l.setLabelFor(textField);
//            p.add(textField);
//        }
//
//        //Lay out the panel.
//        SpringUtilities.makeCompactGrid(p,
//                numPairs, 2,
//                6, 6,
//                6, 6);
//        setContentPane(p);
//
//
//
//        pack();
