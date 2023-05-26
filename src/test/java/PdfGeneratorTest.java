import com.example.medbd.PDFCreate.PdfGenerator;
import com.example.medbd.models.Ticket;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PdfGeneratorTest {

    @Mock
    private PdfFont mockFont;

    @Mock
    private Document mockDocument;

    private PdfGenerator pdfGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pdfGenerator = new PdfGenerator();
    }

    @Test
    public void testGeneratePdf() throws IOException {
        // Arrange
        Ticket ticket = new Ticket();
        String fileName = "test.pdf";

        // Mock the method calls in the PdfGenerator class
        doNothing().when(mockDocument).close();
        doAnswer(invocation -> {
            Paragraph paragraph = invocation.getArgument(0);
            // Assert that the correct text is added to the document
            assertEquals("Some text", paragraph.getTextRenderingMode());
            return null;
        }).when(mockDocument).add(any(Paragraph.class));

        // Call the method under test
        pdfGenerator.generatePdf(ticket, fileName);

    }

    @Test
    public void testAddLine() {
        // Arrange
        Document document = mock(Document.class);
        String text = "Some text";

        // Call the method under test
        pdfGenerator.addLine(document, text, mockFont);

        // Assert that the necessary methods are called
        verify(document, times(1)).add(any(Paragraph.class));
    }
}
