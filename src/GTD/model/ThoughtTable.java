package GTD.model;

import GTD.controller.DatabaseController;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ThoughtTable extends DbTable<ThoughtRow> {

    private ThoughtRowset list;

    public ThoughtTable() {
        super("thoughts");
        ArrayList<String> c = new ArrayList<>();
        c.add("Name");
        c.add("Notes");
        setColumns(c);
        setIdField("Thought_id");
    }

    public ThoughtRowset fetchAll() {
        if (list == null) {
            list = new ThoughtRowset();
            try {
                ResultSet res = DatabaseController.executeGetQuery(Query.GET_THOUGHTS);
                while (res.next()) {
                    ThoughtRow t = createRow();
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

    public ThoughtRow createRow() {
        ThoughtRow t = new ThoughtRow();
        t.setTable(this);
        return t;
    }
}
