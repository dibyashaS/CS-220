import java.util.*;

public class Main {

    // Use enum to reduce the possibility of an error
    public enum VisitType{
        EMERGENCY,
        REGULAR
    }
    class Appointment {
        String clientName;
        String visitType; 
        String helpDescription;

        public Appointment(String clientName, String visitType, String helpDescription) {
            this.clientName = clientName;
            this.visitType = visitType;
            this.helpDescription = helpDescription;
        }
    }

    // Implementing a vet class in order to manage appointment queues
    class Vet {
        Queue<Appointment> eq = new LinkedList<>();  // Emergency queue
        Queue<Appointment> rq = new LinkedList<>();  // Regular queue

        // Method to add appointments to the appropriate queue
        public void appointmentQueue(String clientName, String visitType, String helpDescription) {
            Appointment a = new Appointment(clientName, visitType, helpDescription);
            if (visitType==VisitType.EMERGENCY) {
                eq.add(a);  // Add to emergency queue if it's an emergency
            } else {
                rq.add(a);  // Add to regular queue if it's a regular visit
            }
        }

        // Get the next client (priority given to emergencies)
        public Appointment getNextClient() {
            if (!eq.isEmpty()) {
                return eq.poll();  // Using poll to get client from the front of the queue (FIFO)
            } else if (!rq.isEmpty()) {
                return rq.poll(); 
            } else {
                return null;  
            }
        }

        //Edge case: Condition where eq and rq are both empty queues
        public boolean isEmpty() {
            return eq.isEmpty() && rq.isEmpty();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        Vet clinic = m.new Vet();  // Creating Vet instance

        // Appointments
        clinic.appointmentQueue("Dibyasha", "Regular", "Annual checkup");
        clinic.appointmentQueue("Jaime", "Regular", "Vaccination");
        clinic.appointmentQueue("UnknownPerson", "Emergency", "Concussion");

        while (!clinic.isEmpty()) {
            Appointment client = clinic.getNextClient();
            if (client != null) {
                System.out.println("Serving " + client.clientName + ": " + client.helpDescription);
            }
        }
    }
}



    
