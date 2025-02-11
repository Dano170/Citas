package org.medicalappointmentsystem;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sistema de Administración de Citas Médicas");
        FileManager fileManager = new FileManager();
        fileManager.validateFiles();

        while (true) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Agregar Doctor");
            System.out.println("2. Agregar Paciente");
            System.out.println("3. Agendar Cita");
            System.out.println("4. Mostrar Citas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Nombre del Doctor: ");
                    String doctorName = scanner.nextLine();
                    Doctor doctor = new Doctor(UUID.randomUUID().toString(), doctorName);
                    fileManager.saveDoctor(doctor);
                    System.out.println("Doctor agregado con ID: " + doctor.id);
                    break;
                case 2:
                    System.out.print("Nombre del Paciente: ");
                    String patientName = scanner.nextLine();
                    Patient patient = new Patient(UUID.randomUUID().toString(), patientName);
                    fileManager.savePatient(patient);
                    System.out.println("Paciente agregado con ID: " + patient.id);
                    break;
                case 3:
                    System.out.print("ID del Doctor: ");
                    String doctorId = scanner.nextLine();
                    System.out.print("ID del Paciente: ");
                    String patientId = scanner.nextLine();
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Motivo: ");
                    String reason = scanner.nextLine();

                    String appointmentId = UUID.randomUUID().toString();
                    Appointment appointment = new Appointment(appointmentId, doctorId, patientId, date, reason);
                    fileManager.saveAppointment(appointment);
                    System.out.println("Cita agendada con ID: " + appointmentId);
                    break;
                case 4:
                    fileManager.listAppointments();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}

class Doctor {
    String id;
    String name;

    public Doctor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + "," + name;
    }
}

class Patient {
    String id;
    String name;

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + "," + name;
    }
}

class Appointment {
    String id;
    String doctorId;
    String patientId;
    String date;
    String reason;

    public Appointment(String id, String doctorId, String patientId, String date, String reason) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return id + "," + doctorId + "," + patientId + "," + date + "," + reason;
    }
}

@SuppressWarnings("ResultOfMethodCallIgnored")
class FileManager {
    private static final String DOCTORS_FILE = "db/doctors.csv";
    private static final String PATIENTS_FILE = "db/patients.csv";
    private static final String APPOINTMENTS_FILE = "db/appointments.csv";

    public void validateFiles() {
        new File("db").mkdir();
        try {
            new File(DOCTORS_FILE).createNewFile();
            new File(PATIENTS_FILE).createNewFile();
            new File(APPOINTMENTS_FILE).createNewFile();
        } catch (IOException e) {
            System.out.println("Error al validar archivos: " + e.getMessage());
        }
    }

    public void saveDoctor(Doctor doctor) {
        saveToFile(DOCTORS_FILE, doctor.toString());
    }

    public void savePatient(Patient patient) {
        saveToFile(PATIENTS_FILE, patient.toString());
    }

    public void saveAppointment(Appointment appointment) {
        saveToFile(APPOINTMENTS_FILE, appointment.toString());
    }

    public void listAppointments() {
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENTS_FILE))) {
            String line;
            System.out.println("\nCitas Agendadas:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer citas: " + e.getMessage());
        }
    }

    private void saveToFile(String fileName, String data) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }
}
