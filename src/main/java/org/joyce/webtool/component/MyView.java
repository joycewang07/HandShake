package org.joyce.webtool.component;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import sun.font.FontFamily;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 14-4-21.
 */


public class MyView extends AbstractPdfView {
    Font titleFont;
    Font redFont;
    Font subFont;
    Font normalFont;

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document,
                                    PdfWriter arg2, HttpServletRequest arg3, HttpServletResponse arg4) throws Exception {

        titleFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
        redFont = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL, Color.RED);
        subFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
        normalFont = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);

        String imageUrl = "http://localhost:8080//images/HandShakelogo.jpg";
        Image image = Image.getInstance(imageUrl);
        image.scalePercent(30f);
        image.setAbsolutePosition(100f, 530f);
        document.add(image);
        //this is the first subtitile
        document.add(new Paragraph(""));
        document.add(new Paragraph("HandShake General Report", titleFont));
        document.add(new Paragraph(new Date().toString()));
        document.add(new Paragraph("******************************************************************************************"));

        //add one empty line
        document.add(new Paragraph(""));

        PdfPTable table = new PdfPTable(3);
        PdfPCell titleCell = new PdfPCell(new Paragraph("Update Client Information"));
        titleCell.setBackgroundColor(Color.LIGHT_GRAY);
        titleCell.setColspan(3);

        table.addCell(titleCell);

        table.addCell("Company");
        table.addCell("Activity");
        table.addCell("User Amount");

        List<Object[]> pdfData = (List<Object[]>) map.get("pdfData");
        for(Object[] pdfObjects: pdfData){
            table.addCell(pdfObjects[0].toString());
            table.addCell(pdfObjects[1].toString());
            table.addCell(pdfObjects[2].toString());
        }


        document.add(table);

        document.close();
    }

}

