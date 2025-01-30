import java.util.ArrayList;

class Doctor {
        String nombre, especialidad, horario;
        Doctor(String nombre, String especialidad, String horario) {
                this.nombre = nombre;
                this.especialidad = especialidad;
                this.horario = horario;
        }
}

class Paciente {
        String nombre, historial;
        int edad;
        Paciente(String nombre, int edad, String historial) {
                this.nombre = nombre;
                this.edad = edad;
                this.historial = historial;
        }
}

class Cita {
        Doctor doctor;
        Paciente paciente;
        String fecha, hora;
        Cita(Doctor doctor, Paciente paciente, String fecha, String hora) {
                this.doctor = doctor;
                this.paciente = paciente;
                this.fecha = fecha;
                this.hora = hora;
        }
        @Override
        public String toString() {
                return "Cita: Doctor = " + doctor.nombre + " (" + doctor.especialidad +
                        "), Paciente = " + paciente.nombre + ", Fecha = " + fecha + ", Hora = " + hora;
        }
}

public class Main {
        public static void main(String[] args) {
                // Ejemplo
                Doctor doctor = new Doctor("Dra. Carmen Oropeza", "Cardiology", "Lunes a Viernes, 9:00-15:00");
                Paciente paciente = new Paciente("Julio Morales", 47, "Hypertension");
                Cita cita = new Cita(doctor, paciente, "2025-02-01", "10:00");

                // Registro y muestra del ejemplo
                ArrayList<Cita> citas = new ArrayList<>();
                citas.add(cita);

                System.out.println("--- Ejemplo de Sistema de Citas ---");
                System.out.println("Doctor Registrado: " + doctor.nombre + ", Especialidad: " + doctor.especialidad);
                System.out.println("Paciente Registrado: " + paciente.nombre + ", Edad: " + paciente.edad);
                System.out.println("Citas Programadas:");
                for (Cita c : citas) System.out.println(c);
        }
}
