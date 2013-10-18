/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.View;

import GTD.controller.MainController;
import GTD.model.ActionTable;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private TableRowSorter<TableModel> sorter;
    private RowFilter doneFilter;
    private RowFilter contextFilter;
    private RowFilter projectFilter;
    private RowFilter statusFilter;
    private List<RowFilter<TableModel, Object>> filters = new ArrayList<RowFilter<TableModel, Object>>();
    private RowFilter totalFilter = null;
    private MainController controller;

    public ActionsPanel() {
        setBackground(Color.BLACK);

        table = new JTable();
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                    int selectedColumn = table.getModel().getColumnCount() - 1;
                    int ID = (int) table.getModel().getValueAt(selectedRow, selectedColumn);
                    // System.out.println(ID);
                }
            }
        });


        table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                    int selectedColumn = table.getModel().getColumnCount() - 1;
                    int ID = (int) table.getModel().getValueAt(selectedRow, selectedColumn);
                    JOptionPane.showMessageDialog(null, "DOUBLE CLICKED WITH ID: " + ID);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });



        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        setLayout(new GridLayout(1, 1));

        add(scrollPane);
    }

    public void clearTableSelection() {
        //table.getSelectionModel().clearSelection();
        table.clearSelection();
    }

    public void setTableModel(final ActionTable actions) {


        DefaultTableModel dtm = new DefaultTableModel() {

            @Override
            public int getRowCount() {
                return actions.fetchAll().size();
            }

            @Override
            public int getColumnCount() {
                return actions.getColumns().size() + 1;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                // System.out.println(columnIndex);
                return actions.getValueAt(rowIndex, columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        String[] columns = {"Description", "Notes", "Action Date", "Last Changed", "Done?", "Context", "Status", "Project"};
        dtm.setColumnIdentifiers(columns);

        table.setModel(dtm);


        table.removeColumn(table.getColumnModel().getColumn(table.getColumnCount() - 1));

        sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);
        table.setAutoResizeMode(table.AUTO_RESIZE_LAST_COLUMN);
    }

    public void filterDone() {
        if (doneFilter == null) {
            doneFilter = RowFilter.regexFilter("false", 4);
            filters.add(doneFilter);

        } else {
            filters.remove(doneFilter);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
    }

    public void filterProject() {
        if (projectFilter == null) {
            projectFilter = RowFilter.regexFilter("", 7);
            filters.add(projectFilter);
        } else {
            filters.remove(projectFilter);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
    }

    public void filterContext() {
        if (contextFilter == null) {
            contextFilter = RowFilter.regexFilter("", 5);
            filters.add(contextFilter);
        } else {
            filters.remove(contextFilter);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
    }

    void setController(MainController controller) {
        this.controller = controller;
    }
}
