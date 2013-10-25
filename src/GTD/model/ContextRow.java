package GTD.model;

public class ContextRow extends DbRow {

    public ContextRow() {
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
