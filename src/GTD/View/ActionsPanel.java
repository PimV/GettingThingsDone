/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.View;

import GTD.controller.MainController;
import GTD.model.ActionTable;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author PimGame
 */
@SuppressWarnings({"serial", "unchecked"})
public class ActionsPanel extends JPanel {

    private JScrollPane scrollPane;
    private JTable table;
    private TableRowSorter<TableModel> sorter;
    private RowFilter doneFilter = RowFilter.regexFilter("false", 5);
    private RowFilter contextFilter = RowFilter.regexFilter(".", 6);
    private RowFilter projectFilter = RowFilter.regexFilter(".", 8);
    private RowFilter statusFilter1 = RowFilter.regexFilter("Information", 7);
    private RowFilter statusFilter2 = RowFilter.regexFilter("Postponed", 7);
    private RowFilter statusFilter3 = RowFilter.regexFilter("Delegate", 7);
    private RowFilter statusFilter4 = RowFilter.regexFilter("Do ASAP", 7);
    private RowFilter statusFilter5 = RowFilter.regexFilter("Planned", 7);
    private List<RowFilter<TableModel, Object>> filters = new ArrayList<>();
    private RowFilter totalFilter = null;
    private MainController controller;

    public ActionsPanel() {
        // setBackground(Color.BLACK);

        table = new JTable();
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                    int selectedColumn = table.getModel().getColumnCount() - 1;
                    int ID = (int) table.getModel().getValueAt(selectedRow, selectedColumn);

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
                    controller.showEditPopup(ID);
                    // JOptionPane.showMessageDialog(null, "DOUBLE CLICKED WITH ID: " + ID);
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

        //setLayout(new GridLayout(1, 1));

        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 672;
        gridBagConstraints.ipady = 328;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        add(scrollPane, gridBagConstraints);

        JLabel label = new JLabel("Filter on: ");
        // field.setText("Thought");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 101;
        // gridBagConstraints.ipady = -5
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        add(label, gridBagConstraints);


        JTextField field = new JTextField();
        field.setText("Thought");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
       // gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 11, 11);
        add(field, gridBagConstraints);

        JButton button2 = new JButton("FILTER Action");
        //field.setText("Thought");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;      
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1.0;
        //gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 11, 0);
        add(button2, gridBagConstraints);
        
        JButton button = new JButton("Remove Action");
        //field.setText("Thought");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        //gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 11);
        add(button, gridBagConstraints);

        //  scrollPane.setBounds(0, 0, getWidth(), getHeight() - 6);
        // add(scrollPane);
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


        String[] columns = {
            "Name", "Description", "Notes", "Action Date", "Last Changed", "Done?", "Context", "Status", "Project"
        };
        dtm.setColumnIdentifiers(columns);

        table.setModel(dtm);


        table.removeColumn(table.getColumnModel().getColumn(table.getColumnCount() - 1));

        sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    public void filterDone() {
        if (filters.contains(doneFilter)) {
            filters.remove(doneFilter);
        } else {
            filters.add(doneFilter);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
    }

    public void filterProject() {
        if (filters.contains(projectFilter)) {
            filters.remove(projectFilter);
        } else {
            filters.add(projectFilter);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);

    }

    public void filterContext() {
        if (filters.contains(contextFilter)) {
            filters.remove(contextFilter);
        } else {
            filters.add(contextFilter);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
        //removeFilters();
    }

    public void filterStatus1() {
        if (filters.contains(statusFilter1)) {
            filters.remove(statusFilter1);
        } else {
            filters.add(statusFilter1);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
        removeFilters();
    }

    public void filterStatus2() {
        if (filters.contains(statusFilter2)) {
            filters.remove(statusFilter2);
        } else {
            filters.add(statusFilter2);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
        removeFilters();
    }

    public void filterStatus3() {
        if (filters.contains(statusFilter3)) {
            filters.remove(statusFilter3);
        } else {
            filters.add(statusFilter3);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
        removeFilters();
    }

    public void filterStatus4() {
        if (filters.contains(statusFilter4)) {
            filters.remove(statusFilter4);
        } else {
            filters.add(statusFilter4);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
        removeFilters();
    }

    public void filterStatus5() {
        if (filters.contains(statusFilter5)) {
            filters.remove(statusFilter5);
        } else {
            filters.add(statusFilter5);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
        removeFilters();
    }

    public void removeFilters() {
        if (filters.isEmpty()) {
            totalFilter = null;
            sorter.setRowFilter(totalFilter);
        }
    }

    void setController(MainController controller) {
        this.controller = controller;
    }
}
