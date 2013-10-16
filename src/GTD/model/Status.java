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

    NO_STATUS(1, "No status"),
    INFORMATION(2, "Information"),
    POSTPONED(3, "Postponed"),
    DELEGATED(4, "Delegated"),
    DO_ASAP(5, "Do ASAP"),
    PLANNED(6, "Planned");
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
