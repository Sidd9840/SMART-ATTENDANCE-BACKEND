package com.smartattendance.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.smartattendance.entity.Attendance;
import com.smartattendance.repository.AttendanceRepository;

@Service
public class AttendancePdfService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public ByteArrayInputStream generatePdf(Integer teacherId)  {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);

            Paragraph title = new Paragraph("SMART ATTENDANCE SYSTEM", titleFont);

            title.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(title);

            document.add(new Paragraph("Attendance Report"));

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);

            table.setWidthPercentage(100);

            table.addCell(new PdfPCell(new Paragraph("Student")));

            table.addCell(new PdfPCell(new Paragraph("Subject")));

            table.addCell(new PdfPCell(new Paragraph("Date")));

            table.addCell(new PdfPCell(new Paragraph("Time")));

            table.addCell(new PdfPCell(new Paragraph("Status")));

            List<Attendance> list =
            		attendanceRepository.findByTeacherId(teacherId);

            for (Attendance a : list) {

                table.addCell(a.getStudentName());

                table.addCell(a.getSubject());

                table.addCell(a.getAttendanceDate().toString());

                table.addCell(a.getAttendanceTime().toString());

                table.addCell(a.getStatus());

            }

            document.add(table);

            document.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return new ByteArrayInputStream(out.toByteArray());

    }

}