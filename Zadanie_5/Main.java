import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main implements Receive {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        final var path = "C:/Users/User/Desktop/save/";
        final var format = ".data";

        File file;

        ArrayList<Timetable> times;
        MedicalCard card;
        MedicalCard card2;
        Patient patient;
        Patient patient2;
        Physician physician;
        Reception reception;
        ArrayList<Recording> recordings;

        file = new File(path + "times" + format);
        if (file.exists()) times = Serializer.deserialization(file.getPath());
        else {
            times = new ArrayList<>();
            times.add(new Timetable(DateReception.FRIDAY, TimeReception.EIGHT, 1, true));
            times.add(new Timetable(DateReception.FRIDAY, TimeReception.NINE, 1, true));
        }

        file = new File(path + "card" + format);
        if (file.exists()) card = Serializer.deserialization(file.getPath());
        else card = new MedicalCard("1");

        file = new File(path + "card2" + format);
        if (file.exists()) card2 = Serializer.deserialization(file.getPath());
        else card2 = new MedicalCard("2");

        file = new File(path + "patient" + format);
        if (file.exists()) patient = Serializer.deserialization(file.getPath());
        else patient = new Patient("1", "Викторов", "Валентин", "Петрович", "ул1", "8800555", "124-234", card);

        file = new File(path + "patient2" + format);
        if (file.exists()) patient2 = Serializer.deserialization(file.getPath());
        else patient2 = new Patient("2", "Баринов", "Олег", "Владимирович", "ул1", "8800555", "124-234", card2);

        file = new File(path + "physician" + format);
        if (file.exists()) physician = Serializer.deserialization(file.getPath());
        else physician = new Physician("1", "Павлов", "Виталий", "Петрович", Post.CARDIOLOGIST, times);

        System.out.println("Попытка записи пациента " + patient.toString() + " к врачу " + physician + ", на четверг, 8 утра");

        file = new File(path + "reception" + format);
        if (file.exists()) reception = Serializer.deserialization(file.getPath());
        else reception = new Reception(DateReception.FRIDAY, TimeReception.EIGHT, 1, 2);


        file = new File(path + "recordings" + format);
        if (file.exists()) recordings = Serializer.deserialization(file.getPath());
        else {
            recordings = new ArrayList<>();
            recordings.add(new Main().receive(patient, physician, reception, "Боли в сердце", Service.INSPECTION));
            recordings.add(new Main().receive(patient2, physician, reception, "Кардиограмма", Service.ANALYSIS));
            recordings.add(new Main().receive(patient2, physician, reception, "Кардиограмма", Service.ANALYSIS));
            recordings.add(new Main().receive(patient2, physician, reception, "Кардиограмма", Service.ANALYSIS));
            recordings.removeAll(Collections.singleton(null));
        }

        System.out.println();
        System.out.println("Попытка записи пациента " + patient2 + " к врачу " + physician + ", на четверг, 8 утра");

        System.out.println();
        reception.time = TimeReception.TEN;
        System.out.println("Попытка записи пациента " + patient2 + " к врачу " + physician + ", на четверг, 10 утра");

        System.out.println();
        reception.time = TimeReception.NINE;
        System.out.println("Попытка записи пациента " + patient2 + " к врачу " + physician + ", на четверг, 9 утра");

        System.out.println();
        for (Recording recording : recordings) {
            System.out.println(recording.toString());
        }

        Serializer.serialization(times, path + "times" + format);
        Serializer.serialization(card, path + "card" + format);
        Serializer.serialization(card2, path + "card2" + format);
        Serializer.serialization(patient, path + "patient" + format);
        Serializer.serialization(patient2, path + "patient2" + format);
        Serializer.serialization(physician, path + "physician" + format);
        Serializer.serialization(reception, path + "reception" + format);
        Serializer.serialization(recordings, path + "recordings" + format);
    }

    @Override
    public Recording receive(Patient patient, Physician physician, Reception reception, String description, Service service) {
        for (Timetable timetable : physician.freeTimes) {
            if (timetable.date == reception.date && timetable.time == reception.time) {
                if (timetable.isFree) {
                    timetable.isFree = false;
                    System.out.println("Пациент " + patient.toString() + ", успешно записан к врачу " + physician.toString());
                    return new Recording(description, service, patient, physician);
                } else {
                    System.out.println("Не удалось записать пациента " + patient.toString() + ", к врачу " + physician.toString() + ", врач уже занят");
                    return null;
                }
            }
        }
        System.out.println("Врач не принимает на такое время");
        return null;
    }
}
