package org.joyce.webtool.component;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import sun.font.FontFamily;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.ImageProducer;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 14-4-21.
 */


public class MyView  extends AbstractPdfView {
    Font titleFont;
    Font redFont;
    Font subFont;
    Font normalFont;


        @Override
        protected void buildPdfDocument(Map<String, Object> map, Document document,
                                        PdfWriter pdf, HttpServletRequest request, HttpServletResponse response) throws Exception {

            titleFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
            redFont = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL, Color.RED);
            subFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
            normalFont = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);

          //  User user = (User) map.get("user");

            //Paragraph p1 = new Paragraph("Registered User: " + user.getFirst() + " " + user.getLast());
            Paragraph p2 = new Paragraph("Registration Time: " + map.get("time"));

            document.add(new Paragraph("State Level PSO Analysis Report", titleFont));
            document.add(new Paragraph(new Date().toString()));
            document.add(new Paragraph("******************************************************************************************"));

            Image image = Image.getInstance("HandShakelogo.jpg");
            image.scalePercent(80f);
            image.setAbsolutePosition(460f, 730f);
            //this is the first subtitile
            document.add(image);
            //add one empty line
            document.add(new Paragraph(""));

            PdfPTable table = new PdfPTable(2);
            PdfPCell titleCell = new PdfPCell(new Paragraph("Update Client Information"));
            titleCell.setBackgroundColor(Color.LIGHT_GRAY);
            titleCell.setColspan(4);

            table.addCell(titleCell);

            table.addCell("deviceDefectAmount");
            table.addCell(String.valueOf(i1));
            table.addCell("useErrorsAmount");
            table.addCell(String.valueOf(i2));
            table.addCell("unknownsAmount");
            table.addCell(String.valueOf(i3));
            table.addCell("bothsAmount");
            table.addCell(String.valueOf(i4));
            document.add(table);

            document.add(p2);

            document.close();
        }

    }

