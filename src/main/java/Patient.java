import java.util.List;
import java.util.Date;
import org.sql2o.*;

public class Patient {

private int id;
private int doctorid;
private String patientname;
private String birthdate;

public Patient(String patientname, String birthdate, int doctorid) {
  this.patientname = patientname;
  this.birthdate = birthdate;
  this.doctorid = doctorid;
}

  public int getId() {
    return id;
  }

  public int getDoctorId() {
    return doctorid;
  }

  public String getPatientName() {
    return patientname;
  }

  public String getBirthDate() {
    return birthdate;
  }

  public static List<Patient> all() {
    String sql = "SELECT id, patientname, birthdate, doctorid";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients (patientname, birthdate, doctorid) VALUES (:patientname, :birthdate, :doctorid)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("patientname",  this.patientname)
        .addParameter("birthdate", this.birthdate)
        .addParameter("doctorid", this.doctorid)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patient find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM patients where id = (:id)";
    Patient patient = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Patient.class);
      return patient;
  }
}


  @Override
  public boolean equals(Object otherPatient){
    if (!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return (this.getPatientName().equals(newPatient.getPatientName())) && (this.getBirthDate().equals(newPatient.getBirthDate())) && (this.getDoctorId() == newPatient.getDoctorId());
    }
  }

}
