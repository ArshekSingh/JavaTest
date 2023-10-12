package util;

import com.lowagie.text.pdf.PdfWriter;
import com.sts.finncub.core.constants.Constant;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@Component
public class ReportUtility {


    public void exportToExcel(HttpServletResponse httpServletResponse, JasperReport jasperReport, Map<String, Object> parameters, JRBeanCollectionDataSource dataSource,
                              String fileName) throws JRException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ServletOutputStream outputStream = null;
        try {
            parameters.put("autoSizeExcelColumns", "false");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JRXlsxExporter xlsExporter = new JRXlsxExporter();
            xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            byteArrayOutputStream = new ByteArrayOutputStream();
            xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            //xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName + ".xlsx"));
            SimpleXlsxReportConfiguration xlsReportConfiguration = new SimpleXlsxReportConfiguration();
            SimpleXlsxExporterConfiguration xlsExporterConfiguration = new SimpleXlsxExporterConfiguration();
            xlsExporterConfiguration.setCreateCustomPalette(true);
            xlsReportConfiguration.setOnePagePerSheet(false);
            xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(false);
            xlsReportConfiguration.setDetectCellType(true);
            xlsReportConfiguration.setWhitePageBackground(true);
            xlsReportConfiguration.setShowGridLines(true);
            xlsExporter.setConfiguration(xlsReportConfiguration);
            xlsExporter.setConfiguration(xlsExporterConfiguration);
            xlsExporter.exportReport();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            httpServletResponse.setContentType("application/xlsx");
            outputStream = httpServletResponse.getOutputStream();
            outputStream.write(byteArrayOutputStream.toByteArray());
        } finally {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public JasperReport getCompiledJasper(String jasperName) throws JRException, IOException {
        try (InputStream stream = getClass().getResourceAsStream("/report_templates/" + jasperName + ".jrxml")) {
            return JasperCompileManager.compileReport(stream);
        }
    }

    public void exportToPdf(HttpServletResponse httpServletResponse, JasperReport jasperReport,
                            Map<String, Object> parameters, JRBeanCollectionDataSource dataSource,
                            String fileName) throws JRException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ServletOutputStream outputStream = null;
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName + ".pdf");

//        byte[] fileData = JasperExportManager.exportReportToPdf(jasperPrint);
//        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");
//        httpServletResponse.setContentType("application/pdf");
//        httpServletResponse.getOutputStream().write(fileData);

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            byteArrayOutputStream = new ByteArrayOutputStream();
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setMetadataAuthor("finncub");
            configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");
            httpServletResponse.setContentType("application/pdf");
            outputStream = httpServletResponse.getOutputStream();
            outputStream.write(byteArrayOutputStream.toByteArray());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
    }

    public void exportToPdfWithoutDataSource(HttpServletResponse httpServletResponse, JasperReport jasperReport,
                                             Map<String, Object> parameters, String fileName) throws Exception {
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        ByteArrayOutputStream byteArrayOutputStream = null;
        ServletOutputStream outputStream = null;
//        byte[] fileData = JasperExportManager.exportReportToPdf(jasperPrint);
//        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");
//        httpServletResponse.setContentType("application/pdf");
//        httpServletResponse.getOutputStream().write(fileData);
        try {
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            byteArrayOutputStream = new ByteArrayOutputStream();
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setMetadataAuthor("finncub");
            configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");
            httpServletResponse.setContentType("application/pdf");
            outputStream = httpServletResponse.getOutputStream();
            outputStream.write(byteArrayOutputStream.toByteArray());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
    }

    public BufferedImage getBufferedImage(String path) throws IOException {
        try (InputStream stream = getClass().getResourceAsStream(path);
             ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Objects.requireNonNull(stream).readAllBytes())) {
            return ImageIO.read(byteArrayInputStream);
        }
    }

    public String getCenterGroupCode(String centerGroupCode) {
        String centerCode = "";
        if (centerGroupCode.toUpperCase().contains("A")) {
            centerCode = "A";
        } else if (centerGroupCode.toUpperCase().contains("B")) {
            centerCode = "B";
        } else if (centerGroupCode.toUpperCase().contains("C")) {
            centerCode = "C";
        } else if (centerGroupCode.toUpperCase().contains("D")) {
            centerCode = "D";
        } else if (centerGroupCode.toUpperCase().contains("E")) {
            centerCode = "E";
        } else if (centerGroupCode.toUpperCase().contains("F")) {
            centerCode = "F";
        } else {
            centerCode = centerGroupCode;
        }
        return centerCode;
    }

    public String getCenterGroupCodeForAof(String centerGroupCode) {
        String centerCode = "";
        if (centerGroupCode.toUpperCase().contains("A")) {
            centerCode = "AL";
        } else if (centerGroupCode.toUpperCase().contains("B")) {
            centerCode = "BL";
        } else if (centerGroupCode.toUpperCase().contains("C")) {
            centerCode = "CL";
        } else if (centerGroupCode.toUpperCase().contains("D")) {
            centerCode = "DL";
        } else if (centerGroupCode.toUpperCase().contains("E")) {
            centerCode = "EL";
        } else if (centerGroupCode.toUpperCase().contains("F")) {
            centerCode = "FL";
        } else {
            centerCode = centerGroupCode;
        }
        return centerCode;
    }

    public String getRelation(String relationShip) {
        String relationName = "";
        if (Constant.HUSBAND.equalsIgnoreCase(relationShip)) {
            relationName = "HUSBAND";
        } else if (Constant.FATHER.equalsIgnoreCase(relationShip)) {
            relationName = "FATHER";
        } else if (Constant.SON.equalsIgnoreCase(relationShip)) {
            relationName = "SON";
        } else if (Constant.DAUGHTER.equalsIgnoreCase(relationShip)) {
            relationName = "DAUGHTER";
        } else if (Constant.MOTHER.equalsIgnoreCase(relationShip)) {
            relationName = "MOTHER";
        }
        return relationName;
    }

    public String getClientRelationAgainstNomineeRelation(String relationShip) {
        String relationName = "";
        if (Constant.HUSBAND.equalsIgnoreCase(relationShip)) {
            relationName = "WIFE";
        } else if (Constant.FATHER.equalsIgnoreCase(relationShip)) {
            relationName = "DAUGHTER";
        } else if (Constant.SON.equalsIgnoreCase(relationShip)) {
            relationName = "MOTHER";
        } else if (Constant.DAUGHTER.equalsIgnoreCase(relationShip)) {
            relationName = "MOTHER";
        } else if (Constant.MOTHER.equalsIgnoreCase(relationShip)) {
            relationName = "SON";
        } else if (Constant.BROTHER.equalsIgnoreCase(relationShip)) {
            relationName = "BROTHER";
        } else if (Constant.WIFE.equalsIgnoreCase(relationShip)) {
            relationName = "HUSBAND";
        } else if (Constant.SISTER.equalsIgnoreCase(relationShip)) {
            relationName = "BROTHER";
        }
        return relationName;
    }

    public String getFamilyGenderAgainstFamilyRelation(String relationShip) {
        String gender = "";
        if (Constant.HUSBAND.equalsIgnoreCase(relationShip)) {
            gender = "M";
        } else if (Constant.FATHER.equalsIgnoreCase(relationShip)) {
            gender = "M";
        } else if (Constant.SON.equalsIgnoreCase(relationShip)) {
            gender = "M";
        } else if (Constant.DAUGHTER.equalsIgnoreCase(relationShip)) {
            gender = "F";
        } else if (Constant.MOTHER.equalsIgnoreCase(relationShip)) {
            gender = "F";
        } else if (Constant.BROTHER.equalsIgnoreCase(relationShip)) {
            gender = "M";
        } else if (Constant.WIFE.equalsIgnoreCase(relationShip)) {
            gender = "F";
        } else if (Constant.SISTER.equalsIgnoreCase(relationShip)) {
            gender = "F";
        }
        return gender;
    }
}
