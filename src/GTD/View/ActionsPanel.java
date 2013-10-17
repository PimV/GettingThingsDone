/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.View;

import GTD.controller.MainController;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author PimGame
 */
public class ActionsPanel extends JPanel {

    private JScrollPane scrollPane;
    private JTable table;
    private MainController controller;

    public ActionsPanel() {
        setBackground(Color.BLACK);

        String[][] array = new String[3][3];
        array[0][0] = "Test 1";
        array[1][1] = "Test 2";
        String[] cols = {"Col1", "Col2", "Col3"};

        table = new JTable();


        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        setLayout(new GridLayout(1, 1));

        add(scrollPane);
    }

    public void setTableModel(DefaultTableModel dtm) {
        table.setModel(dtm);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        table.setAutoResizeMode(table.AUTO_RESIZE_LAST_COLUMN);
    }

    void setController(MainController controller) {
        this.controller = controller;
    }
}
