/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.View;

import GTD.controller.MainController;
import GTD.model.ActionTable;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
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

        table = new JTable();


        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        setLayout(new GridLayout(1, 1));

        add(scrollPane);
    }

    public void setTableModel(final ActionTable actions) {
        
       
        AbstractTableModel dtm = new AbstractTableModel() {

            @Override
            public int getRowCount() {
                return actions.fetchAll().size();
            }

            @Override
            public int getColumnCount() {
                return actions.getColumns().size();
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return actions.fetchAll().get(rowIndex).get(actions.getColumns().get(columnIndex));
            }
            
            
            
            
        };
        
      //  dtm.
        
        
        table.setModel(dtm);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        table.setAutoResizeMode(table.AUTO_RESIZE_LAST_COLUMN);
    }

    void setController(MainController controller) {
        this.controller = controller;
    }
}
