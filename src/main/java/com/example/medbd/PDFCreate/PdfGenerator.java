package com.example.medbd.PDFCreate;

import com.example.medbd.models.Ticket;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.IOException;

public class PdfGenerator {

    public void generatePdf(Ticket ticket, String fileName) throws IOException {
        PdfWriter writer = new PdfWriter(fileName);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf,PageSize.A4);
        // Создаем шрифт
        PdfFont font = PdfFontFactory.createFont("src/main/resources/folds/times.ttf", PdfEncodings.IDENTITY_H);


        try {

            // Добавляем информацию из объекта Ticket в документ
            addLine(document, "Дата записи на прием: " + ticket.getDate_appointment(), font);
            addLine(document, "Дата получения: " + ticket.getDate_receipt(), font);
            addLine(document, "Идентификатор тикета: " + ticket.getId_ticket(), font);
            addLine(document, "Отчество пациента: " + ticket.getPat_otch(), font);
            addLine(document, "Фамилия пациента: " + ticket.getDc_fam(), font);
            addLine(document, "Имя пациента: " + ticket.getDc_im(), font);
            addLine(document, "Отчество пациента: " + ticket.getDc_otch(), font);
            addLine(document, "Специальность: " + ticket.getName_of_specialty(), font);
            addLine(document, "Кабинет: " + ticket.getRoom(), font);
            addLine(document, "Фамилия врача: " + ticket.getUs_fam(), font);
            addLine(document, "Имя врача: " + ticket.getUs_im(), font);
            addLine(document,"Отчество врача: " + ticket.getUs_otch(), font);
            addLine(document, "Фамилия пациента: " + ticket.getPat_fam(), font);
            addLine(document, "Имя пациента: " + ticket.getPat_im(), font);

            // Закрываем документ
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addLine(Document document, String text, PdfFont font) {
        Paragraph paragraph = new Paragraph(text);
        paragraph.setFont(font);
        paragraph.setMarginBottom(10f);
        document.add(paragraph);
    }
}