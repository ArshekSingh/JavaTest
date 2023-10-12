package codebase;

import enums.IblStatus;

import java.util.*;
import java.util.stream.Collectors;

public class Downloader {

    @PostMapping("ibl/downloadUnprocessedSanctionData")
    public void downloadUnprocessedSanctionData(@RequestBody @Valid IblRequest request, HttpServletResponse httpServerResponse) throws BadRequestException {
        log.info("Request initiated to download unprocessed sourcing data with [BATCH_ID] {}", request.getBatchId());
        colendingService.downloadUnprocessedSanctionData(request, httpServerResponse);
        log.info("unprocessed sourcing data download successfully for [BATCH_ID] {}", request.getBatchId());
    }

    @Override
    public void downloadUnprocessedSanctionData(IblRequest request, HttpServletResponse httpServerResponse) throws BadRequestException {
        UserSession userSession = userCredentialService.getUserSession();
        request.setOrgId(userSession.getOrganizationId());
        request.setStatus(IblStatus.E.name());
        List<IblSanctionDataUploader> data = colendingDao.getSanctionData(request);
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            Map<String, Object> map = excelGeneratorUtil.populateHeaderAndName(IBL_SANCTION_HEADER, "Ibl_sanction_unprocessed.xls");
            map.put("RESULTS", colendingAssembler.prepareIblSanctionErrorData(data));
            excelGeneratorUtil.buildExcelDocument(map, workbook);
            excelGeneratorUtil.downloadDocument(httpServerResponse, map, workbook);
        } catch (Exception exception) {
            log.error("Exception occurs while downloading Excel {}", exception.getMessage());
        }
    }

    @Override
    public List<IblSanctionDataUploader> getSanctionData(IblRequest request) throws BadRequestException {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<IblSanctionDataUploader> criteria = builder.createQuery(IblSanctionDataUploader.class);
            Root<IblSanctionDataUploader> root = criteria.from(IblSanctionDataUploader.class);
            List<Predicate> predicates = getSanctionPredicates(request.getOrgId(), request.getBatchId(), request.getStatus(), builder, root);
            criteria.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
            return entityManager.createQuery(criteria).setFirstResult(request.getStart()).setMaxResults(request.getLimit()).getResultList();
        } catch (Exception exception) {
            log.error("Exception occurred while fetching ");
            throw new BadRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private List<Predicate> getSanctionPredicates(Long orgId, String batchId, String status, CriteriaBuilder builder, Root<IblSanctionDataUploader> root) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("iblSanctionDataUploaderPK").get("orgId"), orgId));
        if (StringUtils.hasText(batchId)) {
            predicates.add(builder.equal(root.get("iblSanctionDataUploaderPK").get("batchId"), batchId));
        }
        if (StringUtils.hasText(status)) {
            predicates.add(builder.equal(root.get("status"), status));
        }
        return predicates;
    }

    public Map<String, Object> populateHeaderAndName(String headerParam, String fileName) {
        Map<String, Object> map = new HashMap<>();
        map.put("Excel Name", fileName);
        map.put("HEADERS", Arrays.asList(headerParam.split(",")));
        return map;
    }

    public List<List<String>> prepareIblSanctionErrorData(List<IblSanctionDataUploader> data) {
        return data.stream().map(this::constructData).collect(Collectors.toList());
    }

    private List<String> constructData(IblSanctionDataUploader request) {
        List<String> data = new ArrayList<>();
        IblSanctionDataUploaderPK iblSanctionDataUploaderPK = request.getIblSanctionDataUploaderPK();
        data.add(StringUtils.hasText(request.getSlNo()) ? request.getSlNo() : "");
        data.add(StringUtils.hasText(request.getIblBaseBranchCode()) ? request.getIblBaseBranchCode() : "");
        data.add(StringUtils.hasText(request.getIblBaseBranchName()) ? request.getIblBaseBranchName() : "");
        data.add(StringUtils.hasText(request.getRuralUrban()) ? request.getRuralUrban() : "");
        data.add(StringUtils.hasText(request.getSpName()) ? request.getSpName() : "");
        data.add(StringUtils.hasText(request.getSpCustomerId()) ? request.getSpCustomerId() : "");
        data.add(StringUtils.hasText(iblSanctionDataUploaderPK.getSpLoanNumber()) ? iblSanctionDataUploaderPK.getSpLoanNumber() : "");
        data.add(StringUtils.hasText(request.getBankCustomerId()) ? request.getBankCustomerId() : "");
        data.add(StringUtils.hasText(request.getBankLoanNum()) ? request.getBankLoanNum() : "");
        data.add(StringUtils.hasText(request.getSpBranch()) ? request.getSpBranch() : "");
        data.add(StringUtils.hasText(request.getSpCentreName()) ? request.getSpCentreName() : "");
        data.add(StringUtils.hasText(request.getSpVillage()) ? request.getSpVillage() : "");
        data.add(StringUtils.hasText(request.getCustomerName()) ? request.getCustomerName() : "");
        data.add(StringUtils.hasText(request.getSpouseName()) ? request.getSpouseName() : "");
        data.add(StringUtils.hasText(request.getBankProductCode()) ? request.getBankProductCode() : "");
        data.add(StringUtils.hasText(request.getSpProductCode()) ? request.getSpProductCode() : "");
        data.add(StringUtils.hasText(request.getPurpose()) ? request.getPurpose() : "");
        data.add(StringUtils.hasText(request.getBsrCode()) ? request.getBsrCode() : "");
        data.add(StringUtils.hasText(request.getAppliedAmount()) ? request.getAppliedAmount() : "");
        data.add(StringUtils.hasText(request.getSanctionedLoanAmount()) ? request.getSanctionedLoanAmount() : "");
        data.add(StringUtils.hasText(request.getCreditBureauVerificationDoneByHimarkEquifax()) ? request.getCreditBureauVerificationDoneByHimarkEquifax() : "");
        data.add(StringUtils.hasText(request.getCreditBureauStatus()) ? request.getCreditBureauStatus() : "");
        data.add(StringUtils.hasText(request.getSanctionDate()) ? request.getSanctionDate() : "");
        data.add(StringUtils.hasText(request.getExpectedDisbursalDate()) ? request.getExpectedDisbursalDate() : "");
        data.add(StringUtils.hasText(request.getLoanStatus()) ? request.getLoanStatus() : "");
        data.add(StringUtils.hasText(request.getLapsedDate()) ? request.getLapsedDate() : "");
        data.add(StringUtils.hasText(request.getNumOfDaysLeftForLapse()) ? request.getNumOfDaysLeftForLapse() : "");
        data.add(StringUtils.hasText(request.getStatus()) ? request.getStatus() : "");
        data.add(StringUtils.hasText(request.getRemarks()) ? request.getRemarks() : "");
        return data;
    }
}
