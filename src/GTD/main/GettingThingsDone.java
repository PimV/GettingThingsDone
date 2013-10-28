package GTD.main;

import GTD.View.MainFrame;
import GTD.controller.DatabaseController;
import GTD.controller.MainController;
import java.sql.SQLException;

public class GettingThingsDone {

    public static void main(String[] args) throws SQLException {
        DatabaseController.openConnection();

        MainFrame mainFrame = new MainFrame();
        MainController controller = new MainController(mainFrame);
        
        mainFrame.setController(controller);
        controller.setThoughtsPanel(mainFrame.getThoughtsPanel());
        controller.setActionsPanel(mainFrame.getActionsPanel());
        controller.setContextPanel(mainFrame.getContextPanel());
        controller.setProjectsPanel(mainFrame.getProjectsPanel());
        controller.showActions();

        mainFrame.setVisible(true);
    }
}
