import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;

public class DoctorTest {


  @Rule
  public DatabaseRule database = new DatabaseRule();// UNIT TESTING

  @Test
    public void doctor_instantiatesCorrectly_true() {
    Doctor testDoctor = new Doctor("Deborah Jenkins", "gastroentrology");
    assertEquals(true, testDoctor instanceof Doctor);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Doctor.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Doctor firstDoctor = new Doctor("Deborah Jenkins", "gastroentrology");
    Doctor secondDoctor = new Doctor("Deborah Jenkins", "gastroentrology");
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Doctor myDoctor = new Doctor("Bert Schneider", "podiatry");
    myDoctor.save();
    assertTrue(Doctor.all().get(0).equals(myDoctor));
  }

  @Test
  public void find_findCategoryInDatabase_true() {
    Doctor myDoctor = new Doctor("Bertha Johnson", "Household chores");
    myDoctor.save();
    Doctor savedDoctor = Doctor.find(myDoctor.getId());
    assertTrue(myDoctor.equals(savedDoctor));
  }

}
