package util;

import com.sts.finncub.core.dto.DcbApplicationDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Shahzad Hussain
 */
@Slf4j
@Component
public class ExcelGeneratorUtil {

    public void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook) {
        String sheetName = (String) model.get("Excel Name");
        List<String> headers = (List<String>) model.get("HEADERS");
        List<List<String>> results = (List<List<String>>) model.get("RESULTS");
        List<String> numericColumns = new ArrayList<>();
        if (model.containsKey("numericcolumns")) numericColumns = (List<String>) model.get("numericcolumns");
        HSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(15);
        int currentRow = 0;
        short currentColumn = 0;
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        HSSFRow headerRow = sheet.createRow(currentRow);
        for (String header : headers) {
            HSSFRichTextString text = new HSSFRichTextString(header);
            HSSFCell cell = headerRow.createCell(currentColumn);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(text);
            currentColumn++;
        }
        currentRow++;
        for (List<String> result : results) {
            currentColumn = 0;
            HSSFRow row = sheet.createRow(currentRow);
            for (String value : result) {
                HSSFCell cell = row.createCell(currentColumn);
                if (numericColumns.contains(headers.get(currentColumn))) {
                    cell.setCellValue(value);
                } else {
                    HSSFRichTextString text = new HSSFRichTextString(value);
                    cell.setCellValue(text);
                }
                currentColumn++;
            }
            currentRow++;
        }
    }

    public Map<String, Object> populateHeaderAndName(String headerParam, String fileName) {
        Map<String, Object> map = new HashMap<>();
        map.put("Excel Name", fileName);
        map.put("HEADERS", Arrays.asList(headerParam.split(",")));
        return map;
    }

    public void downloadDocument(HttpServletResponse httpServletResponse, Map<String, Object> map, HSSFWorkbook workbook) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + getFileName(map));
            httpServletResponse.setContentType("application/xls");
            httpServletResponse.getOutputStream().write(bytes);
        } catch (Exception exception) {
            log.error("Exception occurs while downloading NEFT Excel {}", exception.getMessage());
        }
    }

    public void downloadColendingCollectionDocument(HttpServletResponse httpServletResponse, Map<String, Object> map, XSSFWorkbook workbook) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + getFileName(map)+".xlsx");
            httpServletResponse.setContentType("application/xlsx");
            httpServletResponse.getOutputStream().write(bytes);
        } catch (Exception exception) {
            log.error("Exception occurs while downloading NEFT Excel {}", exception.getMessage());
        }
    }

    public String getFileName(Map<String, Object> map) {
        log.info(String.valueOf(map.get("Excel Name")));
        return String.valueOf(map.get("Excel Name"));
    }

    public void downloadErrorDocument(HttpServletResponse httpServletResponse, Map<String, Object> map, HSSFWorkbook workbook) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + getFileName(map));
            httpServletResponse.setContentType("application/xls");
            httpServletResponse.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            httpServletResponse.getOutputStream().write(bytes);
        } catch (Exception exception) {
            log.error("Exception occurs while downloading Excel {}", exception.getMessage());
        }
    }

    public ByteArrayInputStream fillData(List<String> header, List<DcbApplicationDetail> data) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("BureauReport");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            //Row for Header-->
            Row headerRow = sheet.createRow(0);
            //Header
            for (int col = 0; col < header.size(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(header.get(col));
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for (DcbApplicationDetail applicationDetails : data) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(applicationDetails.getReferenceNo());
                row.createCell(1).setCellValue(applicationDetails.getUniqueNo());
                row.createCell(2).setCellValue("0E");
                row.createCell(3).setCellValue(applicationDetails.getAppliedAmount());
                row.createCell(4).setCellValue(applicationDetails.getCustomerName());
                row.createCell(5).setCellValue(applicationDetails.getRelationship());
                row.createCell(6).setCellValue(applicationDetails.getRelationshipName());
                row.createCell(7).setCellValue("");
                row.createCell(8).setCellValue("");
                row.createCell(9).setCellValue(getAddress(applicationDetails));
                row.createCell(10).setCellValue(applicationDetails.getStateName());
                row.createCell(11).setCellValue(applicationDetails.getPincode());
                row.createCell(12).setCellValue("");
                row.createCell(13).setCellValue(applicationDetails.getVoterCardNumber());
                row.createCell(14).setCellValue(applicationDetails.getAdditionalId1());
                row.createCell(15).setCellValue("");
                row.createCell(16).setCellValue(applicationDetails.getAdditionalId1());
                row.createCell(17).setCellValue(applicationDetails.getPanCardNumber());
                row.createCell(18).setCellValue("");
                row.createCell(19).setCellValue(applicationDetails.getMobileNumber());
                row.createCell(20).setCellValue(DateTimeUtil.localDateToString(applicationDetails.getDob(), DateTimeUtil.DD_MM_YYYY));
                row.createCell(21).setCellValue(applicationDetails.getGender());
                row.createCell(22).setCellValue(applicationDetails.getBranchCode());
                row.createCell(23).setCellValue(applicationDetails.getCenterCode());
                row.createCell(24).setCellValue(applicationDetails.getFamMemberId());
                row.createCell(25).setCellValue(applicationDetails.getHouseHoldIncome() != null ? String.valueOf(applicationDetails.getHouseHoldIncome()) : "");
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private String getAddress(DcbApplicationDetail applicationDetails) {
        String address = "";
        if (StringUtils.hasText(applicationDetails.getAddress1())) {
            address = address + applicationDetails.getAddress1();
        }
        if (StringUtils.hasText(applicationDetails.getDistrictName())) {
            address = address + " " + applicationDetails.getDistrictName();
        }
        if (StringUtils.hasText(applicationDetails.getStateName())) {
            address = address + " " + applicationDetails.getStateName();
        }
        return address;
    }

    public void downloadMultipleZipDocument(HttpServletResponse httpServletResponse, List<Map<String, Object>> map) {
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=loanDetail_repayment.zip");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        try (ZipOutputStream zippedOut = new ZipOutputStream(httpServletResponse.getOutputStream())) {
            for (Map<String, Object> file : map) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                buildExcelDocument(file, workbook);
                zippedOut.putNextEntry(new ZipEntry(getFileName(file)));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                workbook.write(bos);
                bos.writeTo(zippedOut);
                zippedOut.closeEntry();
            }
            zippedOut.finish();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook) {
        String sheetName = (String) model.get("Excel Name");
        List<String> headers = (List<String>) model.get("HEADERS");
        List<List<String>> results = (List<List<String>>) model.get("RESULTS");
        List<String> numericColumns = new ArrayList<>();
        if (model.containsKey("numericcolumns")) numericColumns = (List<String>) model.get("numericcolumns");
        XSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(15);
        int currentRow = 0;
        short currentColumn = 0;
        XSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        XSSFRow headerRow = sheet.createRow(currentRow);
        for (String header : headers) {
            XSSFRichTextString text = new XSSFRichTextString(header);
            XSSFCell cell = headerRow.createCell(currentColumn);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(text);
            currentColumn++;
        }
        currentRow++;
        for (List<String> result : results) {
            currentColumn = 0;
            XSSFRow row = sheet.createRow(currentRow);
            for (String value : result) {
                XSSFCell cell = row.createCell(currentColumn);
                if (numericColumns.contains(headers.get(currentColumn))) {
                    cell.setCellValue(value);
                } else {
                    XSSFRichTextString text = new XSSFRichTextString(value);
                    cell.setCellValue(text);
                }
                currentColumn++;
            }
            currentRow++;
        }
    }

    public byte[] downloadDocument(XSSFWorkbook workbook) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (Exception exception) {
            log.error("Exception occurs while downloading Collection Detail Excel {}", exception.getMessage());
            return new byte[0];
        }
    }

    public void downloadDocuments(HttpServletResponse httpServletResponse, Map<String, Object> map, XSSFWorkbook workbook) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + getFileName(map)+".xlsx");
            httpServletResponse.setContentType("application/xlsx");
            httpServletResponse.getOutputStream().write(bytes);
        } catch (Exception exception) {
            log.error("Exception occurs while downloading IBL Excel {}", exception.getMessage());
        }
    }

    public void downloadNonIblApplications(HttpServletResponse httpServletResponse, Map<String, Object> map, HSSFWorkbook workbook) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + getFileName(map));
            httpServletResponse.setContentType("application/xls");
            httpServletResponse.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            httpServletResponse.getOutputStream().write(bytes);
        } catch (Exception exception) {
            log.error("Exception occurs while downloading non IBL_CLM applications", exception.getMessage());
        }
    }

}
