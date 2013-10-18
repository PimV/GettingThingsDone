/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.main;

import GTD.View.EDITOR_THOUGHTPOPUP;
import GTD.View.MainFrame;
import GTD.controller.DatabaseController;
import GTD.controller.MainController;
import GTD.model.Query;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gijs
 */
public class GettingThingsDone {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {        

        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        
        MainController controller = new MainController(mainFrame);

//        
        


        

        DatabaseController.openConnection();

        ResultSet rs = DatabaseController.executeGetQuery(Query.GET_ACTIONS);
        if (rs != null) {
        }
        mainFrame.getActionsPanel().setTableModel(DatabaseController.buildTableModel(rs));



        mainFrame.setController(controller);
        controller.setThoughtsPanel(mainFrame.getThoughtsPanel());

    }
}
