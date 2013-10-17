/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

/**
 *
 * @author PimGame
 */
public enum Query {

    GET_THOUGHTS("1: ID, 2: Name, 3: Notes",
    "SELECT * FROM gagpvenn_db.thoughts"),
    GET_PROJECTS("1: ID, 2: Name, 3: Notes",
    "SELECT * FROM gagpvenn_db.projects"),
    GET_STATUSES("1: ID, 2: Name",
    "SELECT * FROM gagpvenn_db.statuses"),
    GET_CONTEXTS("1: ID, 2: Name",
    "SELECT * FROM gagpvenn_db.context"),
    GET_ACTIONS("1: ID, 2: Description, 3: Notes, 4: Action_Date, 5: Statuschange_Date, 6: Done, 7: Context_ID, 8: Status_ID, 9: Project_ID",
    "SELECT * FROM gagpvenn_db.actions");
    private final String usage;
    private final String query;

    private Query(String usage, String query) {
        this.usage = usage;
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public String getUsage() {
        return usage;
    }
}
