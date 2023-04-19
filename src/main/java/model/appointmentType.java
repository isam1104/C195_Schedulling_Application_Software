package model;

/**
 * This class creates the appointmentType objects.
 * @author Isam Elder
 */
public class appointmentType {
    private String Type;

    public appointmentType(String Type){
        this.Type = Type;
    }

    /**
     * @return the appointmentType type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type the appointmentType type to set
     */
    public void setType(String Type) {
        Type = Type;
    }

    /**
     * @return the appointmentType String type
     */
    @Override
    public String toString(){return Type;}
}