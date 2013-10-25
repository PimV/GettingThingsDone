package GTD.View;

import GTD.controller.MainController;
import GTD.model.ActionTable;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

@SuppressWarnings({
    "serial", "unchecked"
})
public class ActionsPanel extends JPanel {

    private JScrollPane scrollPane;
    private JTable table;
    private TableRowSorter<TableModel> sorter;
    private JButton[] buttons = new JButton[2];
    private JButton removeActionButton = createButton(1, "Remove Action"); //1
    private RowFilter doneFilter = RowFilter.regexFilter("false", 5);
    private RowFilter contextFilter = RowFilter.regexFilter(".", 6);
    private RowFilter projectFilter = RowFilter.regexFilter(".", 8);
    private RowFilter statusFilter1 = RowFilter.regexFilter("Information", 7);
    private RowFilter statusFilter2 = RowFilter.regexFilter("Postponed", 7);
    private RowFilter statusFilter3 = RowFilter.regexFilter("Delegate", 7);
    private RowFilter statusFilter4 = RowFilter.regexFilter("Do ASAP", 7);
    private RowFilter statusFilter5 = RowFilter.regexFilter("Planned", 7);    
    
    private static RowFilter fieldFilter;
    private JTextField filterField;
    private List<RowFilter<TableModel, Object>> statusFilters = new ArrayList<>();
    private List<RowFilter<TableModel, Object>> filters = new ArrayList<>();
    private RowFilter totalFilter = null;
    private RowFilter totalStatusFilter = null;
    private MainController controller;

    public ActionsPanel() {
        removeActionButton.setEnabled(false);

        table = new JTable();
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table.getSelectedRow() != -1) {
                    removeActionButton.setEnabled(true);

                } else {
                    removeActionButton.setEnabled(false);
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
        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        /*
         * scrollPane
         */
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 672;
        gridBagConstraints.ipady = 328;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(11, 11, 11, 11);
        add(scrollPane, gridBagConstraints);

        /*
         * Filter on label
         */
        JLabel label = new JLabel("Filter on: ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 101;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new Insets(0, 11, 0, 0);
        add(label, gridBagConstraints);

        /*
         * filter on search veld
         */
        filterField = new JTextField();
        filterField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (filters.contains(fieldFilter)) {
                    filters.remove(fieldFilter);
                }
                fieldFilter = RowFilter.regexFilter("(?i)" + filterField.getText());

                filters.add(fieldFilter);


                totalFilter = RowFilter.andFilter(filters);
                sorter.setRowFilter(totalFilter);
                removeFilters();
            }
        });
        filterField.setText("");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new Insets(5, 11, 11, 11);
        add(filterField, gridBagConstraints);

        /*
         * remove action button
         */
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 11, 11);
        add(removeActionButton, gridBagConstraints);
    }

    public void clearTableSelection() {
        table.clearSelection();
    }

    public void setTableModel(final ActionTable actions) {

        DefaultTableModel dtm = new DefaultTableModel() {

            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    case 5:
                        return Boolean.class;
                    case 6:
                        return String.class;
                    case 7:
                        return String.class;
                    case 8:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }

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
                if (columnIndex == 3) {
                    System.out.println("DATA: " + actions.getValueAt(rowIndex, columnIndex));
                }
                return actions.getValueAt(rowIndex, columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] columns = {
            "Name", "Description", "Notes", "Action Date", "Modified", "Done?", "Context", "Status", "Project"
        };
        dtm.setColumnIdentifiers(columns);

        table.setModel(dtm);
        table.removeColumn(table.getColumnModel().getColumn(table.getColumnCount() - 1));
        table.getColumnModel().moveColumn(5, 8);

        sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    public JButton createButton(final int index, String text) {
        JButton b = new JButton();
        b.setText(text);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.convertRowIndexToModel(table.getSelectedRow());
                controller.removeAction(selectedRow);
            }
        });
        if (index < 2 && index >= 0) {
            buttons[index] = b;
        }
        return b;
    }

    public void filterCheck(RowFilter rf) {
        if (filters.contains(rf)) {
            filters.remove(rf);
        } else {
            filters.add(rf);
        }
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
    }
   

    public void filterDone() {
        filterCheck(doneFilter);
    }

    public void filterProject() {
        filterCheck(projectFilter);
    }

    public void filterContext() {
        filterCheck(contextFilter);
    }

    public void filterStatus(int index) {
        switch (index) {
            case 0:
                filterCheck(statusFilter1);
                break;
            case 1:
                filterCheck(statusFilter2);
                break;
            case 2:
                filterCheck(statusFilter3);
                break;
            case 3:
                filterCheck(statusFilter4);
                break;
            case 4:
                filterCheck(statusFilter5);
                break;
        }
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

    public void removeFromList(int index) {
        table.revalidate();
    }
    
    public void reApplyFilters() {
        totalFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(totalFilter);
    }

    public JTable getJTable() {
        return table;
    }
}
