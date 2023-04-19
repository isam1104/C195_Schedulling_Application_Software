package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/***
 * This class holds the inventory for appointments, as well as methods to search (by ID or full/partial name), update, and delete for appointments.
 * @author Isam Elder
 */
public class Inventory {
    private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     * This is the method to search appointments by exact or partial part name (it is overloaded to do so). When this method is called, it parses the current appointments in the inventory and returns an exact or partial match if one or more exist.
     * @param customerName the part name to be searched for
     * @return The appointment or appointments matching the search text criteria, either exact matches or if the string is found at any point within appointments names (only if at least one appointment name or part of an appointment name is found).
     */
    public static ObservableList<Customer> lookupAppointment(String customerName) {
        ObservableList<Customer> resultAppointment = FXCollections.observableArrayList();
        for (Customer appointment : allCustomers) {
            if (appointment.getCustomerName().toLowerCase().contains(customerName.toLowerCase())) {
                resultAppointment.add(appointment);
            }
        }
        return resultAppointment;
    }

    /**
     * <p><b>
     * RUNTIME ERROR: While creating the lookup methods, I struggled to have the search results reset when the search field was set to blank. I attempted several different logical arguments to reset the tables when the search field was blank, but no combination of if or try or for loop strategies worked. At best, these failed strategies simply did nothing to refresh the tables or, at worst, simply added even more errors to the problem I was trying to fix. After meeting with course instructors and discussing with classmates, I settled on the currently implemented solution of setting an On Key Typed KeyEvent that takes action to get all appointments when the search field isEmpty().
     * </b></p>*
     * This is the method to search appointments by exact appointment ID. When this method is called, it parses the current parts in the inventory and returns an exact match if one exists.
     * @param customerId the part id to be searched for
     * @return The part matching the search ID criteria (only if a part is found).
     */
    public static Customer lookupAppointment(int customerId) {
        Customer tempSearchAppointmentID = null;
        for (Customer customer : getAllCustomers()) {
            if (customer.getCustomerId() == customerId) {
                tempSearchAppointmentID = customer;
            }
        }
        return tempSearchAppointmentID;
    }

    /**
     * This is the method to get all appointments. When this method is called, all appointments currently in the inventory are returned.
     *
     * @return All appointments currently in the inventory.
     */
    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }
}
