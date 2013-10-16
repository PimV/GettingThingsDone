/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.model;

/**
 *
 * @author PimGame
 */
public enum Status {

    NO_STATUS(0, "No status"),
    INFORMATION(1, "Information"),
    POSTPONED(2, "Postponed"),
    DELEGATED(3, "Delegated"),
    DO_ASAP(4, "Do ASAP"),
    PLANNED(5, "Planned");
    private final int id;
    private final String status;

    private Status(int id, String status) {
        this.id = id;
        this.status = status;
    }

    private int id() {
        return id;
    }

    private String status() {
        return status;
    }
}
