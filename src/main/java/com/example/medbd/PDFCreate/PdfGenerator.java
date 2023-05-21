package com.example.medbd.PDFCreate;

import com.example.medbd.models.Ticket;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.IOException;

public class PdfGenerator {

    public static void generateListImage(Ticket objects, String path) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(path));
        Document document = new Document(pdfDocument, PageSize.A4.rotate());

        PdfFont font = PdfFontFactory.createFont(".\\fonts\\times.ttf", PdfEncodings.IDENTITY_H);


    }
}
