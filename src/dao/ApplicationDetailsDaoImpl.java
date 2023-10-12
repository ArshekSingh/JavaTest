package dao;

import com.sts.finncub.core.dto.ApplicationListDto;
import com.sts.finncub.core.dto.DcbApplicationDetail;
import com.sts.finncub.core.dto.MasReportCbListDto;
import com.sts.finncub.core.entity.*;
import com.sts.finncub.core.exception.BadRequestException;
import com.sts.finncub.core.repository.dao.ApplicationDetailsDao;
import com.sts.finncub.core.request.CbFilterRequest;
import com.sts.finncub.core.request.FilterRequest;
import com.sts.finncub.core.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class ApplicationDetailsDaoImpl implements ApplicationDetailsDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ApplicationDetails> findAllApplicationDetailsForCb(CbFilterRequest request, Long organizationId, String status) throws BadRequestException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ApplicationDetails> criteria = cb.createQuery(ApplicationDetails.class);
            Root<ApplicationDetails> applicationDetail = criteria.from(ApplicationDetails.class);
            Join<ApplicationDetails, CenterMaster> centerMaster = applicationDetail.join("centerMaster", JoinType.LEFT);
            applicationDetail.fetch("clientMasterDraft");
            applicationDetail.fetch("loanProductMaster");
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("orgId"), organizationId));
            predicates.add(cb.equal(applicationDetail.get("applicationStatus"), status));
            if (!CollectionUtils.isEmpty(request.getBranchList())) {
                predicates.add(cb.in(centerMaster.get("branchId")).value(request.getBranchList()));
            }
            if (request.getApplicationId() != null) {
                predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("applicationId"), request.getApplicationId()));
            }
            if (StringUtils.hasText(request.getBcPartnerId())) {
                predicates.add(cb.equal(applicationDetail.get("loanProductGroupMaster").get("bcId"), request.getBcPartnerId()));
            }
            if (StringUtils.hasText(request.getStartDate()) && StringUtils.hasText(request.getEndDate())) {
                Predicate date = cb.between(applicationDetail.get("appliedOn"), DateTimeUtil.stringToDate(request.getStartDate()), DateTimeUtil.stringToDate(request.getEndDate()));
                predicates.add(date);
            }
            criteria.select(applicationDetail).distinct(true).where(predicates.toArray(new Predicate[0]));
            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ApplicationDetails> findAllApplicationDetails(FilterRequest request, boolean isDcbRequest) throws BadRequestException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ApplicationDetails> criteria = cb.createQuery(ApplicationDetails.class);
            Root<ApplicationDetails> applicationDetail = criteria.from(ApplicationDetails.class);
            Join<ApplicationDetails, CenterMaster> centerMaster = applicationDetail.join("centerMaster");
            Join<ApplicationDetails, LoanProductMaster> loanProductMaster = applicationDetail.join("loanProductMaster");
            Join<ApplicationDetails, LoanProductGroupMaster> loanProductGroupMaster = applicationDetail.join("loanProductGroupMaster");
            Join<ApplicationDetails, ApplicationBankMapping> applicationBankMapping = applicationDetail.join("applicationBankMapping");
            Join<ApplicationDetails, ApplicationAddressMapping> applicationAddressMapping = applicationDetail.join("applicationAddressMapping");
            applicationDetail.fetch("clientMasterDraft");
            applicationDetail.fetch("centerMaster");
            List<Predicate> predicates = getPredicates(request, cb, applicationDetail, centerMaster, isDcbRequest, loanProductMaster, loanProductGroupMaster);
            criteria.select(applicationDetail).where(predicates.toArray(new Predicate[0]));
            if (!isDcbRequest) {
                return em.createQuery(criteria).setFirstResult(request.getStart()).setMaxResults(request.getLimit()).getResultList();
            } else {
                return em.createQuery(criteria).getResultList();
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private List<Predicate> getPredicates(FilterRequest request, CriteriaBuilder cb, Root<ApplicationDetails> applicationDetail, Join<ApplicationDetails, CenterMaster> centerMaster, boolean isDcbRequest, Join<ApplicationDetails, LoanProductMaster> loanProductMaster, Join<ApplicationDetails, LoanProductGroupMaster> loanProductGroupMaster) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("orgId"), request.getOrganizationId()));
        if (!isDcbRequest) {
            if (StringUtils.hasText(request.getApplicationStatus())) {
                predicates.add(cb.equal(applicationDetail.get("applicationStatus"), request.getApplicationStatus()));
            }
            if (StringUtils.hasText(request.getBcPartnerId())) {
                predicates.add(cb.equal(loanProductGroupMaster.get("bcId"), request.getBcPartnerId()));
            }
            if (!CollectionUtils.isEmpty(request.getBranchList())) {
                predicates.add(cb.in(centerMaster.get("branchId")).value(request.getBranchList()));
            }
        } else {
            if (StringUtils.hasText(request.getApplicationStatus())) {
                predicates.add(cb.equal(applicationDetail.get("applicationStatus"), request.getApplicationStatus()));
            } else {
                List<String> applicationStatusList = new ArrayList<>();
                applicationStatusList.add("R");
                applicationStatusList.add("X");
                applicationStatusList.add("A");
                predicates.add(cb.not(applicationDetail.get("applicationStatus").in(applicationStatusList)));
            }
            if (StringUtils.hasText(request.getBcPartnerId())) {
                predicates.add(cb.equal(loanProductGroupMaster.get("bcId"), request.getBcPartnerId()));
            } else {
                predicates.add(cb.equal(loanProductGroupMaster.get("bcId"), "DCB"));
            }
            List<String> infoVerifiedStatusList = new ArrayList<>();
            infoVerifiedStatusList.add("I");
            infoVerifiedStatusList.add("A");
            predicates.add(cb.in(applicationDetail.get("infoVerifiedStatus")).value(infoVerifiedStatusList));
            if (request.getBranchId() != null) {
                predicates.add(cb.equal(centerMaster.get("branchId"), request.getBranchId()));
            }
        }
        if (request.getCenterId() != null) {
            predicates.add(cb.equal(centerMaster.get("centerMasterPK").get("centerId"), request.getCenterId()));
        }
        if (request.getClientId() != null) {
            predicates.add(cb.equal(applicationDetail.get("clientId"), request.getClientId()));
        }
        if (request.getApplicationId() != null) {
            predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("applicationId"), request.getApplicationId()));
        }
        if (StringUtils.hasText(request.getStartDate()) && StringUtils.hasText(request.getEndDate())) {
            predicates.add(cb.between(cb.function("TRUNC", LocalDate.class, applicationDetail.get("appliedOn")), DateTimeUtil.stringToDate(request.getStartDate()), DateTimeUtil.stringToDate(request.getEndDate())));
        }
        if (request.getProductGroupId() != null) {
            predicates.add(cb.equal(loanProductGroupMaster.get("loanProductGroupMasterPK").get("productGroupId"), request.getProductGroupId()));
        }
        if (request.getProductId() != null) {
            predicates.add(cb.equal(loanProductMaster.get("loanProductMasterPK").get("productId"), request.getProductId()));
        }
        return predicates;
    }

    @Override
    public Long findAllApplicationDetailsCount(FilterRequest request, boolean isDcbRequest) throws BadRequestException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<ApplicationDetails> applicationDetail = criteria.from(ApplicationDetails.class);
        Join<ApplicationDetails, CenterMaster> centerMaster = applicationDetail.join("centerMaster");
        Join<ApplicationDetails, LoanProductMaster> loanProductMaster = applicationDetail.join("loanProductMaster");
        Join<ApplicationDetails, LoanProductGroupMaster> loanProductGroupMaster = applicationDetail.join("loanProductGroupMaster");
        applicationDetail.join("clientMasterDraft");
        applicationDetail.join("centerMaster");
        try {
            List<Predicate> predicates = getPredicates(request, cb, applicationDetail, centerMaster, isDcbRequest, loanProductMaster, loanProductGroupMaster);
            criteria.select(cb.count(applicationDetail)).where(predicates.toArray(new Predicate[0]));
            return em.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ApplicationDetails getDetailsByApplicationIdAndOrgId(Long applicationId, Long orgId) throws BadRequestException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ApplicationDetails> criteria = cb.createQuery(ApplicationDetails.class);
            Root<ApplicationDetails> applicationDetail = criteria.from(ApplicationDetails.class);
            applicationDetail.fetch("clientMasterDraft");
            applicationDetail.fetch("centerMaster");
            applicationDetail.fetch("loanProductMaster");
            applicationDetail.fetch("loanPurposeMaster", JoinType.LEFT);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("orgId"), orgId));
            if (applicationId != null) {
                predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("applicationId"), applicationId));
            }
            criteria.select(applicationDetail).where(predicates.toArray(new Predicate[0]));
            return em.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ApplicationDetails> getAllApplicationDetailsForMasCb(FilterRequest request, Long orgId) throws BadRequestException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ApplicationDetails> criteria = cb.createQuery(ApplicationDetails.class);
            Root<ApplicationDetails> applicationDetail = criteria.from(ApplicationDetails.class);
            Join<ApplicationDetails, CenterMaster> centerMaster = applicationDetail.join("centerMaster");
            Join<CenterMaster, BranchMaster> branchMaster = centerMaster.join("branchMaster");
            Join<ApplicationDetails, ApplicationFamilyMemberMapping> applicationFamilyMemberMapping = applicationDetail.join("applicationFamilyMemberMapping");
            Join<ApplicationFamilyMemberMapping, ClientFamilyMemberDetail> clientFamilyMemberDetail = applicationFamilyMemberMapping.join("clientFamilyMemberDetail");
            Join<ApplicationDetails, LoanProductGroupMaster> loanProductGroupMaster = applicationDetail.join("loanProductGroupMaster");
            List<Predicate> predicates = new ArrayList<>();
            List<String> status = new ArrayList<>();
            status.add("X");
            status.add("R");
            status.add("A");
            predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("orgId"), orgId));
            predicates.add(cb.equal(applicationFamilyMemberMapping.get("isCoApplicant"), "Y"));
            predicates.add(cb.equal(loanProductGroupMaster.get("bcId"), "MAS"));
            predicates.add(cb.not(applicationDetail.get("applicationStatus").in(status)));
            if (request.getBranchId() != null) {
                predicates.add(cb.equal(centerMaster.get("branchId"), request.getBranchId()));
            }
            if (request.getCenterId() != null) {
                predicates.add(cb.equal(applicationDetail.get("centerId"), request.getCenterId()));
            }
            if (StringUtils.hasText(request.getStartDate()) && StringUtils.hasText(request.getEndDate())) {
                Predicate date = cb.between(applicationDetail.get("appliedOn"), DateTimeUtil.stringToDate(request.getStartDate()), DateTimeUtil.stringToDate(request.getEndDate()));
                predicates.add(date);
            }
            criteria.select(applicationDetail).distinct(true).where(predicates.toArray(new Predicate[0]));
            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ClientCreditBureauSummary getLatestSingleClientBureauSummaryDetails(Long applicationId, Long orgId) throws BadRequestException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ClientCreditBureauSummary> criteria = cb.createQuery(ClientCreditBureauSummary.class);
            Root<ClientCreditBureauSummary> clientCreditBureauSummaryRoot = criteria.from(ClientCreditBureauSummary.class);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(clientCreditBureauSummaryRoot.get("clientCreditBureauSummaryPK").get("orgId"), orgId));
            if (applicationId != null) {
                predicates.add(cb.equal(clientCreditBureauSummaryRoot.get("clientCreditBureauSummaryPK").get("applicationId"), applicationId));
            }
            criteria.orderBy(cb.desc(clientCreditBureauSummaryRoot.get("insertedOn")));
            criteria.select(clientCreditBureauSummaryRoot).where(predicates.toArray(new Predicate[0]));
            return em.createQuery(criteria).getResultStream().findFirst().orElse(null);
        } catch (Exception e) {
            e.getMessage();
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ApplicationDetails getApplicationDetailsByOrgIdForAccountOpening(Long applicationId, Long orgId) throws BadRequestException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ApplicationDetails> criteria = cb.createQuery(ApplicationDetails.class);
            Root<ApplicationDetails> applicationDetail = criteria.from(ApplicationDetails.class);
            Join<ApplicationDetails, CenterMaster> centerMaster = applicationDetail.join("centerMaster");
            applicationDetail.fetch("clientMasterDraft");
            applicationDetail.fetch("centerMaster");
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("orgId"), orgId));
            if (applicationId != null) {
                predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("applicationId"), applicationId));
            }
            criteria.select(applicationDetail).where(predicates.toArray(new Predicate[0]));
            return em.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Procedure
    public List<ApplicationListDto> getApplicationGridDto(Long orgId, String userId, FilterRequest request) {
        ProcedureCall procedureCall = ((Session) em.getDelegate()).getSession().createStoredProcedureCall("PKG_LOAN_TRANSACTION_REPORT.PR_APPLICATION_DETAIL_GRID", ApplicationListDto.class);
        procedureCall.registerParameter(1, Long.class, ParameterMode.IN).bindValue(orgId);
        if (request.getBranchId() != null) {
            procedureCall.registerParameter(2, Integer.class, ParameterMode.IN).bindValue(request.getBranchId());
        } else {
            procedureCall.registerParameter(2, Integer.class, ParameterMode.IN).bindValue(0);
        }
        if (request.getCenterId() != null) {
            procedureCall.registerParameter(3, Long.class, ParameterMode.IN).bindValue(request.getCenterId());
        } else {
            procedureCall.registerParameter(3, Long.class, ParameterMode.IN).bindValue(0L);
        }
        if (request.getProductGroupId() != null) {
            procedureCall.registerParameter(4, Integer.class, ParameterMode.IN).bindValue(request.getProductGroupId());
        } else {
            procedureCall.registerParameter(4, Integer.class, ParameterMode.IN).bindValue(0);
        }
        if (request.getProductId() != null) {
            procedureCall.registerParameter(5, Integer.class, ParameterMode.IN).bindValue(request.getProductId());
        } else {
            procedureCall.registerParameter(5, Integer.class, ParameterMode.IN).bindValue(0);
        }
        if (StringUtils.hasText(request.getBcPartnerId())) {
            procedureCall.registerParameter(6, String.class, ParameterMode.IN).bindValue(request.getBcPartnerId());
        } else {
            procedureCall.registerParameter(6, String.class, ParameterMode.IN).bindValue("");
        }
        if (StringUtils.hasText(request.getClientCode())) {
            procedureCall.registerParameter(7, String.class, ParameterMode.IN).bindValue(request.getClientCode());
        } else {
            procedureCall.registerParameter(7, String.class, ParameterMode.IN).bindValue("");
        }
        if (request.getApplicationId() != null) {
            procedureCall.registerParameter(8, Long.class, ParameterMode.IN).bindValue(request.getApplicationId());
        } else {
            procedureCall.registerParameter(8, Long.class, ParameterMode.IN).bindValue(0L);
        }
        if (StringUtils.hasText(request.getApplicationStatus())) {
            procedureCall.registerParameter(9, String.class, ParameterMode.IN).bindValue(request.getApplicationStatus());
        } else {
            procedureCall.registerParameter(9, String.class, ParameterMode.IN).bindValue("");
        }
        if (StringUtils.hasText(request.getStartDate())) {
            procedureCall.registerParameter(10, String.class, ParameterMode.IN).bindValue(request.getStartDate());
        } else {
            procedureCall.registerParameter(10, String.class, ParameterMode.IN).bindValue("");
        }
        if (StringUtils.hasText(request.getEndDate())) {
            procedureCall.registerParameter(11, String.class, ParameterMode.IN).bindValue(request.getEndDate());
        } else {
            procedureCall.registerParameter(11, String.class, ParameterMode.IN).bindValue("");
        }
        procedureCall.registerParameter(12, String.class, ParameterMode.IN).bindValue(userId);
        procedureCall.registerParameter(13, Integer.class, ParameterMode.IN).bindValue(request.getStart());
        procedureCall.registerParameter(14, Integer.class, ParameterMode.IN).bindValue(request.getLimit());
        if (StringUtils.hasText(request.getInfoVerifiedStatus())) {
            procedureCall.registerParameter(15, String.class, ParameterMode.IN).bindValue(request.getInfoVerifiedStatus());
        } else {
            procedureCall.registerParameter(15, String.class, ParameterMode.IN).bindValue("");
        }
        if (StringUtils.hasText(request.getExtLanNo())) {
            procedureCall.registerParameter(16, String.class, ParameterMode.IN).bindValue(request.getExtLanNo());
        } else {
            procedureCall.registerParameter(16, String.class, ParameterMode.IN).bindValue("");
        }
        if (StringUtils.hasText(request.getClmStatus())) {
            procedureCall.registerParameter(17, String.class, ParameterMode.IN).bindValue(request.getClmStatus());
        } else {
            procedureCall.registerParameter(17, String.class, ParameterMode.IN).bindValue("");
        }
        if (StringUtils.hasText(request.getSanctionStatus())) {
            procedureCall.registerParameter(18, String.class, ParameterMode.IN).bindValue(request.getSanctionStatus());
        } else {
            procedureCall.registerParameter(18, String.class, ParameterMode.IN).bindValue("");
        }
        procedureCall.registerParameter(19, String.class, ParameterMode.IN).bindValue(request.getIsCsv());
        if (StringUtils.hasText(request.getInfoVerifiedOnStartDate())) {
            procedureCall.registerParameter(20, String.class, ParameterMode.IN).bindValue(request.getInfoVerifiedOnStartDate());
        } else {
            procedureCall.registerParameter(20, String.class, ParameterMode.IN).bindValue("");
        }
        if (StringUtils.hasText(request.getInfoVerifiedOnEndDate())) {
            procedureCall.registerParameter(21, String.class, ParameterMode.IN).bindValue(request.getInfoVerifiedOnEndDate());
        } else {
            procedureCall.registerParameter(21, String.class, ParameterMode.IN).bindValue("");
        }
        if (StringUtils.hasText(request.getBureauResult())) {
            procedureCall.registerParameter(22, String.class, ParameterMode.IN).bindValue(request.getBureauResult());
        } else {
            procedureCall.registerParameter(22, String.class, ParameterMode.IN).bindValue("");
        }
        procedureCall.registerParameter(23, void.class, ParameterMode.REF_CURSOR);
        ProcedureOutputs procedureOutput = procedureCall.getOutputs();
        ResultSetOutput currOutput = (ResultSetOutput) procedureOutput.getCurrent();
        return currOutput.getResultList();
    }

    @Override
    public List<ApplicationDetails> dcbApplicationDetails(FilterRequest request, boolean isDcbRequest) throws BadRequestException {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ApplicationDetails> criteria = cb.createQuery(ApplicationDetails.class);
            Root<ApplicationDetails> applicationDetail = criteria.from(ApplicationDetails.class);
            Join<ApplicationDetails, CenterMaster> centerMaster = applicationDetail.join("centerMaster");
            Join<ApplicationDetails, LoanProductMaster> loanProductMaster = applicationDetail.join("loanProductMaster");
            Join<ApplicationDetails, LoanProductGroupMaster> loanProductGroupMaster = applicationDetail.join("loanProductGroupMaster");
            List<Predicate> predicates = preparePredicates(request, cb, applicationDetail, centerMaster, isDcbRequest, loanProductMaster, loanProductGroupMaster);
            criteria.select(applicationDetail).where(predicates.toArray(new Predicate[0]));
            if (!isDcbRequest) {
                return em.createQuery(criteria).setFirstResult(request.getStart()).setMaxResults(request.getLimit()).getResultList();
            } else {
                return em.createQuery(criteria).getResultList();
            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching application {} :", request.getApplicationId(), e);
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private List<Predicate> preparePredicates(FilterRequest request, CriteriaBuilder cb, Root<ApplicationDetails> applicationDetail, Join<ApplicationDetails, CenterMaster> centerMaster, boolean isDcbRequest, Join<ApplicationDetails, LoanProductMaster> loanProductMaster, Join<ApplicationDetails, LoanProductGroupMaster> loanProductGroupMaster) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("orgId"), request.getOrganizationId()));
        if (!isDcbRequest) {
            if (StringUtils.hasText(request.getApplicationStatus())) {
                predicates.add(cb.equal(applicationDetail.get("applicationStatus"), request.getApplicationStatus()));
            }
            if (StringUtils.hasText(request.getBcPartnerId())) {
                predicates.add(cb.equal(loanProductGroupMaster.get("bcId"), request.getBcPartnerId()));
            }
            if (!CollectionUtils.isEmpty(request.getBranchList())) {
                predicates.add(cb.in(centerMaster.get("branchId")).value(request.getBranchList()));
            }
        } else {
            if (StringUtils.hasText(request.getApplicationStatus())) {
                predicates.add(cb.equal(applicationDetail.get("applicationStatus"), request.getApplicationStatus()));
            } else {
                List<String> applicationStatusList = new ArrayList<>();
                applicationStatusList.add("R");
                applicationStatusList.add("X");
                applicationStatusList.add("A");
                predicates.add(cb.not(applicationDetail.get("applicationStatus").in(applicationStatusList)));
            }
            if (StringUtils.hasText(request.getBcPartnerId())) {
                predicates.add(cb.equal(loanProductGroupMaster.get("bcId"), request.getBcPartnerId()));
            } else {
                predicates.add(cb.equal(loanProductGroupMaster.get("bcId"), "DCB"));
            }
            if (request.getBranchId() != null) {
                predicates.add(cb.equal(centerMaster.get("branchId"), request.getBranchId()));
            }
        }
        if (request.getCenterId() != null) {
            predicates.add(cb.equal(centerMaster.get("centerMasterPK").get("centerId"), request.getCenterId()));
        }
        if (request.getClientId() != null) {
            predicates.add(cb.equal(applicationDetail.get("clientId"), request.getClientId()));
        }
        if (request.getApplicationId() != null) {
            predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("applicationId"), request.getApplicationId()));
        }
        if (StringUtils.hasText(request.getStartDate()) && StringUtils.hasText(request.getEndDate())) {
            predicates.add(cb.between(cb.function("TRUNC", LocalDate.class, applicationDetail.get("appliedOn")), DateTimeUtil.stringToDate(request.getStartDate()), DateTimeUtil.stringToDate(request.getEndDate())));
        }
        if (request.getProductGroupId() != null) {
            predicates.add(cb.equal(loanProductGroupMaster.get("loanProductGroupMasterPK").get("productGroupId"), request.getProductGroupId()));
        }
        if (request.getProductId() != null) {
            predicates.add(cb.equal(loanProductMaster.get("loanProductMasterPK").get("productId"), request.getProductId()));
        }
        return predicates;
    }

    @Override
    public Long findAllApplicationDetailsForCbCount(CbFilterRequest request, Long organizationId, String status) throws BadRequestException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<ApplicationDetails> applicationDetail = criteria.from(ApplicationDetails.class);
        Join<ApplicationDetails, CenterMaster> centerMaster = applicationDetail.join("centerMaster");
        try {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("orgId"), organizationId));
            predicates.add(cb.equal(applicationDetail.get("applicationStatus"), status));
            if (!CollectionUtils.isEmpty(request.getBranchList())) {
                predicates.add(cb.in(centerMaster.get("branchId")).value(request.getBranchList()));
            }
            if (request.getApplicationId() != null) {
                predicates.add(cb.equal(applicationDetail.get("applicationDetailsPK").get("applicationId"), request.getApplicationId()));
            }
            if (StringUtils.hasText(request.getBcPartnerId())) {
                predicates.add(cb.equal(applicationDetail.get("loanProductGroupMaster").get("bcId"), request.getBcPartnerId()));
            }
            if (StringUtils.hasText(request.getStartDate()) && StringUtils.hasText(request.getEndDate())) {
                Predicate date = cb.between(applicationDetail.get("appliedOn"), DateTimeUtil.stringToDate(request.getStartDate()), DateTimeUtil.stringToDate(request.getEndDate()));
                predicates.add(date);
            }
            criteria.select(cb.count(applicationDetail)).where(predicates.toArray(new Predicate[0]));
            return em.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<DcbApplicationDetail> dcbApplicationList(Long orgId, FilterRequest request) {
        ProcedureCall procedureCall = ((Session) em.getDelegate()).getSession().createStoredProcedureCall("PKG_BC_REPORT.PR_PRE_DISB_DCB_BUREAU", DcbApplicationDetail.class);
        procedureCall.registerParameter(1, Long.class, ParameterMode.IN).bindValue(orgId);
        if (request.getApplicationId() != null) {
            procedureCall.registerParameter(2, Long.class, ParameterMode.IN).bindValue(request.getApplicationId());
        } else {
            procedureCall.registerParameter(2, Long.class, ParameterMode.IN).bindValue(0L);
        }
        procedureCall.registerParameter(3, String.class, ParameterMode.IN).bindValue(request.getBcPartnerId());
        procedureCall.registerParameter(4, LocalDate.class, ParameterMode.IN).bindValue(DateTimeUtil.stringToDate(request.getStartDate()));
        procedureCall.registerParameter(5, LocalDate.class, ParameterMode.IN).bindValue(DateTimeUtil.stringToDate(request.getEndDate()));
        if (request.getApplicationStatus() != null) {
            procedureCall.registerParameter(6, String.class, ParameterMode.IN).bindValue(request.getApplicationStatus());
        } else {
            procedureCall.registerParameter(6, String.class, ParameterMode.IN).bindValue("");
        }
        procedureCall.registerParameter(7, void.class, ParameterMode.REF_CURSOR);
        ProcedureOutputs procedureOutput = procedureCall.getOutputs();
        ResultSetOutput currOutput = (ResultSetOutput) procedureOutput.getCurrent();
        return currOutput.getResultList();
    }

    @Procedure
    public List<MasReportCbListDto> getDataForMasCbReport(FilterRequest request, UserSession userSession) {
        ProcedureCall procedureCall = ((Session) em.getDelegate()).getSession().createStoredProcedureCall("PKG_BC_REPORT.PR_PRE_MASS_BUREAU", MasReportCbListDto.class);
        procedureCall.registerParameter(1, Long.class, ParameterMode.IN).bindValue(userSession.getOrganizationId());
        if (request.getBranchId() != null) {
            procedureCall.registerParameter(2, Integer.class, ParameterMode.IN).bindValue(request.getBranchId());
        } else {
            procedureCall.registerParameter(2, Integer.class, ParameterMode.IN).bindValue(0);
        }
        if (request.getCenterId() != null) {
            procedureCall.registerParameter(3, Long.class, ParameterMode.IN).bindValue(request.getCenterId());
        } else {
            procedureCall.registerParameter(3, Long.class, ParameterMode.IN).bindValue(0L);
        }
        procedureCall.registerParameter(4, String.class, ParameterMode.IN).bindValue("MAS");
        procedureCall.registerParameter(5, LocalDate.class, ParameterMode.IN).bindValue(DateTimeUtil.stringToDate(request.getStartDate()));
        procedureCall.registerParameter(6, LocalDate.class, ParameterMode.IN).bindValue(DateTimeUtil.stringToDate(request.getEndDate()));
        procedureCall.registerParameter(7, void.class, ParameterMode.REF_CURSOR);
        ProcedureOutputs procedureOutput = procedureCall.getOutputs();
        ResultSetOutput currOutput = (ResultSetOutput) procedureOutput.getCurrent();
        return currOutput.getResultList();
    }
}