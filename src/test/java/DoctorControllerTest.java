import com.example.medbd.BdConnection.BdTools;
import com.example.medbd.controllers.AdmController;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(BdTools.class)
public class DoctorControllerTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    private AdmController doctorController;
    private Mockito PowerMockito;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doctorController = new AdmController();

        PowerMockito.mockStatic(BdTools.class);
        when(BdTools.getConnection()).thenReturn(mockConnection);
    }

    @Test
    public void testGetDoctorFromBD() throws SQLException {
        // Arrange
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false); // Simulate two rows in the result set

        when(mockResultSet.getString(1)).thenReturn("1", "2"); // Mock the values for id
        when(mockResultSet.getString(2)).thenReturn("John", "Jane"); // Mock the values for name
        when(mockResultSet.getString(3)).thenReturn("Doe", "Smith"); // Mock the values for surname
        when(mockResultSet.getString(4)).thenReturn("Pat", "Ann"); // Mock the values for patronymic
        when(mockResultSet.getString(5)).thenReturn("Specialty 1", "Specialty 2"); // Mock the values for specialization
        when(mockResultSet.getString(6)).thenReturn("Room 1", "Room 2"); // Mock the values for roomNumber

        // Act
        doctorController.getDoctorFromBD();

        // Assert
        assertEquals(2, doctorController.getDoctorList().size()); // Check the size of the doctor list
    }

    // Add more test methods as needed

}
