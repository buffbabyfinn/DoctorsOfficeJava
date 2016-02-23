import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;

public class DoctorTest {

  // UNIT TESTING

  @Test
    public void doctor_instantiatesCorrectly_true() {
    Doctor testDoctor = new Doctor("Deborah Jenkins", "gastroentrology");
    assertEquals(true, testDoctor instanceof Doctor);
  }
}
