/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

/**
 *
 * @author PimGame
 */
public class StatusRow extends DbRow {

    public StatusRow() {
    }

    public void setName(String name) {
        set("Name", name);
    }

    public String getName() {
        return get("Name");
    }
    
    @Override
    public String toString(){
        return getName();
    }
}
