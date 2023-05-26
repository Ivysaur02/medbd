import com.example.medbd.models.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    private Patient patient;
    private String id;
    private String fam;
    private String imya;
    private String otch;
    private String sex;
    private String date;
    private String street;
    private String home;
    private String apart;
    private String snils;
    private String phone;

    @BeforeEach
    public void setUp() {
        id = "123";
        fam = "Smith";
        imya = "John";
        otch = "Doe";
        sex = "Male";
        date = "1990-01-01";
        street = "Main St";
        home = "123";
        apart = "A";
        snils = "123456789";
        phone = "123-456-7890";
        patient = new Patient(id, fam, imya, otch, sex, date, street, home, apart, snils, phone);
    }

    @Test
    public void testConstructorAndGetters() {
        // Assert
        assertEquals(id, patient.getId());
        assertEquals(fam, patient.getFam());
        assertEquals(imya, patient.getImya());
        assertEquals(otch, patient.getOtch());
        assertEquals(sex, patient.getSex());
        assertEquals(date, patient.getDate());
        assertEquals(street, patient.getStreet());
        assertEquals(home, patient.getHome());
        assertEquals(apart, patient.getApart());
        assertEquals(snils, patient.getSnils());
        assertEquals(phone, patient.getPhone());
    }

    @Test
    public void testDefaultStreetHomeApartPhoneValues() {
        // Act
        patient = new Patient(id, fam, imya, otch, sex, date, null, null, null, snils, null);

        // Assert
        assertEquals("", patient.getStreet());
        assertEquals("", patient.getHome());
        assertEquals("", patient.getApart());
        assertEquals("", patient.getPhone());
    }

    @Test
    public void testSetters() {
        // Act
        patient.setStreet("Main St");
        patient.setHome("123");
        patient.setApart("A");
        patient.setPhone("123-456-7890");

        // Assert
        assertEquals("Main St", patient.getStreet());
        assertEquals("123", patient.getHome());
        assertEquals("A", patient.getApart());
        assertEquals("123-456-7890", patient.getPhone());
    }
}
