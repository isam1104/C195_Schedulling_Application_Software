package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;

/***
 * This class holds the inventory for appointments, as well as methods to search (by ID or full/partial name), update, and delete for appointments.
 * @author Isam Elder
 */
public class InventoryDaolmpl {
    private static final ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     * This is the method to search appointments by exact or partial part name (it is overloaded to do so). When this method is called, it parses the current appointments in the inventory and returns an exact or partial match if one or more exist.
     * @param appointmentName the part name to be searched for
     * @return The appointment or appointments matching the search text criteria, either exact matches or if the string is found at any point within appointments names (only if at least one appointment name or part of an appointment name is found).
     */
    public static ObservableList<Appointment> lookupAppointment(String appointmentName) {
        ObservableList<Appointment> resultAppointment = FXCollections.observableArrayList();
        for (Appointment appointment : allAppointments) {
            if (appointment.getTitle().toLowerCase().contains(appointmentName.toLowerCase())) {
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
     * @param appointmentId the part id to be searched for
     * @return The part matching the search ID criteria (only if a part is found).
     */
    public static Appointment lookupAppointment(int appointmentId) {
        Appointment tempSearchAppointmentID = null;
        for (Appointment appointment : getAllAppointments()) {
            if (appointment.getAppointment_ID() == appointmentId) {
                tempSearchAppointmentID = appointment;
            }
        }
        return tempSearchAppointmentID;
    }

    /**
     * This is the method to get all appointments. When this method is called, all appointments currently in the inventory are returned.
     * @return All appointments currently in the inventory.
     */
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public static ObservableList<Customer> lookupCustomer(String customerName) {
        ObservableList<Customer> resultCustomers = FXCollections.observableArrayList();
        for (Customer customer : allCustomers) {
            if (customer.getCustomerName().toLowerCase().contains(customerName.toLowerCase())) {
                resultCustomers.add(customer);
            }
        }
        return resultCustomers;
    }

    public static Customer lookupCustomer(int customerId) {
        Customer tempSearchProductID = null;
        for (Customer customer : InventoryDaolmpl.getAllCustomers()) {
            if (customer.getCustomerId() == customerId) {
                tempSearchProductID = customer;
            }
        }
        return tempSearchProductID;
    }

    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }
}
