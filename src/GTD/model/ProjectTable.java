package GTD.model;

import GTD.controller.DatabaseController;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProjectTable extends DbTable<ProjectRow> {

    private ProjectRowset list;

    public ProjectTable() {
        super("projects");
        ArrayList<String> cols = new ArrayList<String>();
        cols.add("Name");
        cols.add("Notes");
        setColumns(cols);
        setIdField("Project_id");
    }

    public ProjectRowset fetchAll() {
        if (list == null) {
            list = new ProjectRowset();
            try {
                ResultSet res = DatabaseController.executeGetQuery(Query.GET_PROJECTS);
                while (res.next()) {
                    ProjectRow t = createRow();
                    t.setID(res.getInt(1));
                    t.setName(res.getString(2));
                    t.addNote(res.getString(3));
                    list.add(t);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ProjectRow createRow() {
        ProjectRow p = new ProjectRow();
        p.setTable(this);
        return p;
    }
}
