/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.controller;

import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author PimGame
 */
public class PrintController {

    private JTable table;
    private HashPrintRequestAttributeSet attr;
    private MessageFormat header = null;
    private MessageFormat footer = null;

    public PrintController(JTable table, MessageFormat header, MessageFormat footer) {
        this.table = table;
        this.header = header;
        this.footer = footer;
    }

    public void printTable() {
        try {


            Printable printable = table.getPrintable(JTable.PrintMode.FIT_WIDTH, header, footer);
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(printable);

            if (attr == null) {
                attr = new HashPrintRequestAttributeSet();
                attr.add(OrientationRequested.LANDSCAPE);
            }

            boolean printAccepted = job.printDialog(attr);
            if (printAccepted) {
                job.print(attr);
            }
        } catch (PrinterException pe) {
            pe.printStackTrace();
        }
    }
}
