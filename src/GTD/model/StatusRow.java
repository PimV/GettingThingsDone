package GTD.model;

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
