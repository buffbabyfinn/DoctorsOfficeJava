import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class PatientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void patient_instantiatesCorrectly_true() {
    Patient testPatient = new Patient("Somebody Something", "01/01/1950", 1);
    assertEquals(true, testPatient instanceof Patient);
  }
}
