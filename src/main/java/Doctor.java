import java.util.List;
import java.util.Arrays;
import org.sql2o.*;


public class Doctor {
  private int id;
  private String doctorname;
  private String specialty;

  public Doctor(String doctorname, String specialty) {
  this.doctorname = doctorname;
  this.specialty = specialty;
  }

  public int getId() {
    return id;
  }

  public String getDoctorName() {
    return doctorname;
  }

  public String getSpecialty() {
    return specialty;
  }

  public static List<Doctor> all() {
    String sql = "SELECT id, doctorname, specialty FROM doctors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctor.class);
    }
  }

  @Override
  public boolean equals(Object otherDoctor){
    if (!(otherDoctor instanceof Doctor)) {
      return false;
    } else {
      Doctor newDoctor = (Doctor) otherDoctor;
      return this.getDoctorName().equals(newDoctor.getDoctorName()) && this.getSpecialty().equals(newDoctor.getSpecialty());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO doctors(doctorname, specialty) VALUES (:doctorname, :specialty)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("doctorname", doctorname)
        .addParameter("specialty", specialty)
        .executeUpdate()
        .getKey();
    }
  }

  public static Doctor find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM doctors where id=:id";
      Doctor Doctor = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Doctor.class);
      return Doctor;
    }
  }

  public List<Patient> getPatients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients where doctorID=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Patient.class);
    }
  }
}
