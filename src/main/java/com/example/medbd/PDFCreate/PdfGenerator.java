package com.example.medbd.PDFCreate;

import com.example.medbd.models.Ticket;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.IOException;

public class PdfGenerator {

    public void generatePdf(Ticket ticket, String fileName) throws IOException {
        PdfWriter writer = new PdfWriter(fileName);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        // Создаем шрифт
        PdfFont font = PdfFontFactory.createFont("src/main/resources/folds/times.ttf", PdfEncodings.IDENTITY_H);

        try {
            // Добавляем информацию из объекта Ticket в документ
            addLineOnCentreAndSepar(document, "Талон на приём к врачу № "+ ticket.getId_ticket() , font);
            addLine(document, "Дата обращения: " + ticket.getDate_receipt(), font);
            addLine(document, "Пациент(ФИО): " + ticket.getPat_fam() + " " + ticket.getPat_im() + " " +
                    ticket.getPat_otch(), font);
            addLine(document,"Назначен приём в "+ ticket.getDate_appointment()+ " У", font);
            addLine(document, "Врач " +  ticket.getName_of_specialty() + ": " +
                    ticket.getDc_fam()+ " "+ ticket.getDc_im()+ " " + ticket.getDc_otch() ,font);
            addLine(document, "Кабинет приёма: " +  ticket.getRoom(), font);
            addLine(document, "Записан на приём регистратором: " +  ticket.getUs_fam()+ " "+
                    ticket.getUs_im()+ " " + ticket.getUs_otch(), font);

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

    private void addLineOnCentreAndSepar(Document document, String text, PdfFont font) {
        LineSeparator lineSeparator = new LineSeparator(new DottedLine());
        Paragraph paragraph = new Paragraph(text);
        paragraph.setFont(font);
        paragraph.setMarginBottom(12f);
        paragraph.setTextAlignment(TextAlignment.CENTER);
        paragraph.add(lineSeparator); // Добавляем подчёркивание
        document.add(paragraph);
    }
}