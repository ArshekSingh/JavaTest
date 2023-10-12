package codebase;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Uploader {

    @PostMapping("/uploadIblSanctionedLoans")
    public Response uploadIblSanctionedLoans(@RequestParam("file") MultipartFile multipartFile,  @RequestParam String bcPartnerId) throws IOException, BadRequestException {
        log.info("Request initiated to upload Sanctioned Loans");
        return colendingService.uploadIblSanctionedLoans(multipartFile, bcPartnerId);
    }

    @Override
    public Response uploadIblSanctionedLoans(MultipartFile multipartFile, String bcPartnerId) throws IOException, BadRequestException {
        UserSession userSession = userCredentialService.getUserSession();
        if (!commonUtil.checkFileType(multipartFile)) {
            log.error("Invalid file type");
            return new Response("Invalid excel type", HttpStatus.BAD_REQUEST);
        }
        String batchId = commonUtil.getBatchId(userCredentialService.getUserSession());
        log.info("request received to upload sanctioned loan details {}", batchId);
        validateSourcingExcelHeader(multipartFile.getInputStream());
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().build();
        List<IBLSanctionedLoanExcelRequest> requests = Poiji.fromExcel(multipartFile.getInputStream(), PoijiExcelType.XLSX, IBLSanctionedLoanExcelRequest.class, options);
        List<IblSanctionDataUploader> iblSanctionDataUploaderList = new ArrayList<>();
        Integer count = 0;
        if (!requests.isEmpty()) {
            for (IBLSanctionedLoanExcelRequest request : requests) {
                if ((!StringUtils.hasText(request.getSlNo())) && (!StringUtils.hasText(request.getSpLoanNumber()))) {
                    return new Response("Invalid value for SL_N0. and SP loan Number", HttpStatus.BAD_REQUEST);
                }
                if (!StringUtils.hasText(request.getSlNo())) {
                    return new Response("Invalid value for SL_N0.", HttpStatus.BAD_REQUEST);
                }
                if (!StringUtils.hasText(request.getSpLoanNumber())) {
                    return new Response("Invalid value for SP loan number", HttpStatus.BAD_REQUEST);
                }
                IblSanctionDataUploader entity = colendingAssembler.dtoToEntity(request, userSession, batchId);
                iblSanctionDataUploaderList.add(entity);
                count++;
            }
            colendingRepository.saveAll(iblSanctionDataUploaderList);

            SanctionLoanHeader sanctionLoanHeader = new SanctionLoanHeader();
            sanctionLoanHeader.setBatchId(batchId);
            sanctionLoanHeader.setOrgId(userSession.getOrganizationId());
            sanctionLoanHeader.setBcPartnerId(bcPartnerId);
            sanctionLoanHeader.setTransactionCount(count);
            sanctionLoanHeader.setTransactionDate(LocalDate.now());
            sanctionLoanHeader.setStatus("U");
            sanctionLoanHeader.setInsertedBy(userSession.getUserId());
            sanctionLoanHeader.setInsertedOn(LocalDateTime.now());
            sanctionLoanHeaderRepository.save(sanctionLoanHeader);

            return new Response("SUCCESS", batchId, count.longValue(), HttpStatus.OK);
        }
        return new Response("Empty Excel Cannot be uploaded", HttpStatus.OK);
    }

    private void validateSourcingExcelHeader(InputStream file) throws BadRequestException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            // Iterate through each rows one by one
            for (Row row : sheet) {

                if (!"SL.NO.".equals(row.getCell(0).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for SL.NO.", HttpStatus.BAD_REQUEST);
                }
                if (!"IBL Base Branch Code".equals(row.getCell(1).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for IBL Base Branch Code", HttpStatus.BAD_REQUEST);
                }
                if (!"IBL Base Branch Name".equals(row.getCell(2).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for IBL Base Branch Name", HttpStatus.BAD_REQUEST);
                }
                if (!"Rural/Urban".equals(row.getCell(3).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Rural/Urban", HttpStatus.BAD_REQUEST);
                }
                if (!"SP Name".equals(row.getCell(4).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for SP Name", HttpStatus.BAD_REQUEST);
                }
                if (!"SP Customer ID".equals(row.getCell(5).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for SP Customer ID", HttpStatus.BAD_REQUEST);
                }
                if (!"SP Loan Number".equals(row.getCell(6).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for SP Loan Number", HttpStatus.BAD_REQUEST);
                }
                if (!"BankCustomerID".equals(row.getCell(7).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for BankCustomerID", HttpStatus.BAD_REQUEST);
                }
                if (!"Bank Loan No".equals(row.getCell(8).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Bank Loan No", HttpStatus.BAD_REQUEST);
                }
                if (!"SP Branch".equals(row.getCell(9).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for SP Branch", HttpStatus.BAD_REQUEST);
                }
                if (!"SP Centre Name".equals(row.getCell(10).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for  SP Centre Name", HttpStatus.BAD_REQUEST);
                }
                if (!"SP Village".equals(row.getCell(11).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for SP Village", HttpStatus.BAD_REQUEST);
                }
                if (!"CustomerName".equals(row.getCell(12).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for CustomerName", HttpStatus.BAD_REQUEST);
                }
                if (!"Spouse Name".equals(row.getCell(13).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Spouse Name", HttpStatus.BAD_REQUEST);
                }
                if (!"Bank Product Code".equals(row.getCell(14).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Bank Product Code", HttpStatus.BAD_REQUEST);
                }
                if (!"SP Product Code".equals(row.getCell(15).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for SP Product Code", HttpStatus.BAD_REQUEST);
                }
                if (!"Purpose".equals(row.getCell(16).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Purpose", HttpStatus.BAD_REQUEST);
                }
                if (!"BSR CODE".equals(row.getCell(17).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for BSR CODE", HttpStatus.BAD_REQUEST);
                }
                if (!"Applied Amount".equals(row.getCell(18).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Applied Amount", HttpStatus.BAD_REQUEST);
                }
                if (!"Sanctioned Loan Amount".equals(row.getCell(19).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Sanctioned Loan Amount", HttpStatus.BAD_REQUEST);
                }
                if (!"Credit Bureau Verification Done By (Himark / Equifax)".equals(row.getCell(20).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Credit Bureau Verification Done By (Himark / Equifax)", HttpStatus.BAD_REQUEST);
                }
                if (!"Credit Bureau Status".equals(row.getCell(21).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Credit Bureau Status", HttpStatus.BAD_REQUEST);
                }
                if (!"Sanctioned Date".equals(row.getCell(22).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Sanctioned Date", HttpStatus.BAD_REQUEST);
                }
                if (!"Expected Disbursal Date".equals(row.getCell(23).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Expected Disbursal Date", HttpStatus.BAD_REQUEST);
                }
                if (!"Loan Status".equals(row.getCell(24).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Loan Status", HttpStatus.BAD_REQUEST);
                }
                if (!"Lapsed Date".equals(row.getCell(25).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for Lapsed Date", HttpStatus.BAD_REQUEST);
                }
                if (!"No Of Days Left for Lapse".equals(row.getCell(26).getStringCellValue())) {
                    throw new BadRequestException("Invalid header for No Of Days Left for Lapse", HttpStatus.BAD_REQUEST);
                }
                break;
            }
        } catch (Exception e) {
            log.error("Exception occured while validating excel header");
            throw new BadRequestException("Invalid Excel file", HttpStatus.BAD_REQUEST);
        }
    }
}
