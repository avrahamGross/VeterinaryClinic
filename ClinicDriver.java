import java.io.FileNotFoundException;

/**
 * Driver class to demonstrate a Clinic treating various patients
 */
public class ClinicDriver {

public static void main(String[] args) {
        Clinic clinic = new Clinic("Patients.csv");
        String dayOneReport = "";
        try {
                dayOneReport = clinic.nextDay("Appointments.csv");
        } catch (FileNotFoundException | InvalidPetException exception) {
                exception.printStackTrace();
        }
        String[] dayOneAppointments = dayOneReport.split("\\n");
        try {
                for (String appointment : dayOneAppointments) {
                        if (!clinic.addToFile(appointment)) {
                                System.out.println("Appointment could not be added to file!");
                        }
                }
        }
        catch (FileNotFoundException e) {
                e.getMessage();
        }
}
}
