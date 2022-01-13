import java.io.Serial;
import java.io.Serializable;

public class Recording implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public String description;
    public Service service;
    public Patient patient;
    public Physician physician;

    public Recording(String description, Service service, Patient patient, Physician physician) {
        this.description = description;
        this.service = service;
        this.patient = patient;
        this.physician = physician;
    }

    @Override
    public String toString() {
        return "Причина записи: " + description + ", услуга: " + service.toString() + ", пациент: " + patient.toString() + ", врач: " + physician.toString();
    }
}
