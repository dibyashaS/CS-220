import java.util.*;

public class VetClinicManager {

    // Enum to represent visit types
    public enum VisitType {
        EMERGENCY,
        REGULAR
    }

    // Appointment class
    class Appointment {
        String clientName;
        VisitType visitType;  // Use enum here
        String helpDescription;

        public Appointment(String clientName, VisitType visitType, String helpDescription) {
            this.clientName = clientName;
            this.visitType = visitType;
            this.helpDescription = helpDescription;
        }
    }

    // Vet class to manage queues
    class Vet {
        Queue<Appointment> eq = new LinkedList<>();
        Queue<Appointment> rq = new LinkedList<>();

        // Add appointment to appropriate queue
        public void appointmentQueue(String clientName, VisitType visitType, String helpDescription) {
            Appointment a = new Appointment(clientName, visitType, helpDescription);
            if (visitType == VisitType.EMERGENCY) {
                eq.add(a);
            } else {
                rq.add(a);
            }
        }

        // Get next client (priority to emergency)
        public Appointment getNextClient() {
            if (!eq.isEmpty()) {
                return eq.poll();
            } else if (!rq.isEmpty()) {
                return rq.poll();
            } else {
                return null;
            }
        }

        public boolean isEmpty() {
            return eq.isEmpty() && rq.isEmpty();
        }
    }

    public static void main(String[] args) {
        VetClinicManager manager = new VetClinicManager();
        Vet clinic = manager.new Vet();

        // Add appointments
        clinic.appointmentQueue("Dibyasha", VisitType.REGULAR, "Annual checkup");
        clinic.appointmentQueue("Jaime", VisitType.REGULAR, "Vaccination");
        clinic.appointmentQueue("UnknownPerson", VisitType.EMERGENCY, "Concussion");

        // Process appointments
        while (!clinic.isEmpty()) {
            Appointment client = clinic.getNextClient();
            if (client != null) {
                System.out.println("Serving " + client.clientName + ": " + client.helpDescription);
            }
        }
    }
}




    
