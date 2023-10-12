package dao;

import com.sts.finncub.core.dto.ApplicationDetailDto;
import com.sts.finncub.core.dto.DisbursementDetailsCustomDto;
import com.sts.finncub.core.dto.FirstEmiDateDto;
import com.sts.finncub.core.entity.*;
import com.sts.finncub.core.exception.BadRequestException;
import com.sts.finncub.core.exception.InternalServerErrorException;
import com.sts.finncub.core.repository.dao.DisbursementDao;
import com.sts.finncub.core.request.FilterRequest;
import com.sts.finncub.core.response.Response;
import com.sts.finncub.core.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Repository
@AllArgsConstructor
public class DisbursementDaoImpl implements DisbursementDao {

    private final EntityManager entityManager;

    @Transactional(rollbackFor = InternalServerErrorException.class)
    @Procedure
    public Response loanDisbursement(Long orgId, ApplicationDetailDto applicationDetailDto, String userId) throws InternalServerErrorException {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("PKG_LOAN_PROCESS.PR_LOAN_DISBURSE");
        storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(10, String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter(11, String.class, ParameterMode.OUT);
        storedProcedureQuery.setParameter(1, orgId);
        storedProcedureQuery.setParameter(2, applicationDetailDto.getApplicationId());
        storedProcedureQuery.setParameter(3, applicationDetailDto.getDisbursementType());
        storedProcedureQuery.setParameter(4, applicationDetailDto.getDisbursementDate());
        storedProcedureQuery.setParameter(5, applicationDetailDto.getPaymentMode());
        storedProcedureQuery.setParameter(6, applicationDetailDto.getFeeCollMode());
        storedProcedureQuery.setParameter(7, userId);
        storedProcedureQuery.setParameter(8, StringUtils.hasText(applicationDetailDto.getFcLoanId()) ? applicationDetailDto.getFcLoanId() : "");
        storedProcedureQuery.setParameter(9, StringUtils.hasText(applicationDetailDto.getFcLoanAmount()) ? applicationDetailDto.getFcLoanAmount() : "");
        storedProcedureQuery.execute();
        String status = (String) storedProcedureQuery.getOutputParameterValue(10);
        String message = (String) storedProcedureQuery.getOutputParameterValue(11);
        if (!"1".equals(status)) {
            throw new InternalServerErrorException(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new Response(message, HttpStatus.OK);
    }

    @Override
    public List<DisbursementDetail> findByStatusAndPaymentStatus(FilterRequest request) throws BadRequestException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<DisbursementDetail> criteria = cb.createQuery(DisbursementDetail.class);
            Root<DisbursementDetail> disbursementDetails = criteria.from(DisbursementDetail.class);
            List<Predicate> predicates = getPredicates(request, cb, disbursementDetails, null);
//            disbursementDetails.fetch("loanDetail");
//            disbursementDetails.fetch("clientMaster");
            criteria.select(disbursementDetails).where(predicates.toArray(new Predicate[predicates.size()]));
            return entityManager.createQuery(criteria).setFirstResult(request.getStart()).setMaxResults(request.getLimit()).getResultList();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private List<Predicate> getPredicates(FilterRequest request, CriteriaBuilder cb, Root<DisbursementDetail> disbursementDetails, List<Long> loanIdList) {
        Join<DisbursementDetail, LoanDetail> loanDetail = disbursementDetails.join("loanDetail");
        Join<LoanDetail, CenterMaster> centerMaster = loanDetail.join("centerMaster");
        Join<LoanDetail, ApplicationBankMapping> applicationBankMapping = loanDetail.join("applicationBankMappingList");
        Join<ApplicationBankMapping, ClientBankDetail> clientBankDetail = applicationBankMapping.join("clientBankDetail");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(disbursementDetails.get("status"), "U"));
		if (request.getPaymentAttempt() < 1) {
			predicates.add(cb.equal(disbursementDetails.get("paymentStatus"), "P"));
			predicates.add(cb.equal(disbursementDetails.get("paymentAttempt"), request.getPaymentAttempt()));
		} else {
			predicates.add(cb.equal(disbursementDetails.get("paymentStatus"), "F"));
			predicates.add(cb.equal(disbursementDetails.get("paymentAttempt"), request.getPaymentAttempt()));
		}       
        predicates.add(cb.equal(disbursementDetails.get("disbursementDetailPK").get("orgId"), request.getOrganizationId()));
        predicates.add(cb.equal(clientBankDetail.get("isValidated"), "Y"));
        if (StringUtils.hasText(request.getLoanCode())) {
			predicates.add(cb.equal(loanDetail.get("loanCode"), request.getLoanCode()));
		}
		if (StringUtils.hasText(request.getBcPartner())) {
			predicates.add(cb.equal(loanDetail.get("loanProductGroupMaster").get("bcId"), request.getBcPartner()));
		}
		if (!CollectionUtils.isEmpty(request.getCenterList())) {
			predicates.add(cb.in(centerMaster.get("centerMasterPK").get("centerId")).value(request.getCenterList()));
		}
        if (!CollectionUtils.isEmpty(request.getBranchList())) {
            predicates.add(cb.in(centerMaster.get("branchId")).value(request.getBranchList()));
        }
        if (StringUtils.hasText(request.getStartDate()) && StringUtils.hasText(request.getEndDate())) {
			predicates
					.add(cb.between(cb.function("TRUNC", LocalDate.class, disbursementDetails.get("disbursementDate")),
							DateTimeUtil.stringToDate(request.getStartDate()),
							DateTimeUtil.stringToDate(request.getEndDate())));
        }
        if (!CollectionUtils.isEmpty(loanIdList)) {
            predicates.add(cb.in(disbursementDetails.get("disbursementDetailPK").get("loanId")).value(loanIdList));
        }
        return predicates;
    }

    @Override
    public DisbursementDetailsCustomDto getCountAndSumOfPayableAmountDisbursementDetails(FilterRequest request, List<Long> loanIdList) throws BadRequestException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<DisbursementDetailsCustomDto> criteria = cb.createQuery(DisbursementDetailsCustomDto.class);
            Root<DisbursementDetail> disbursementDetails = criteria.from(DisbursementDetail.class);
            List<Predicate> predicates = getPredicates(request, cb, disbursementDetails, loanIdList);
            criteria.multiselect(cb.sum(disbursementDetails.get("payableAmount")), cb.count(disbursementDetails)).where(predicates.toArray(new Predicate[predicates.size()])).distinct(true);
            return entityManager.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Procedure
    public Response calculateFee(Long orgId, Long applicationId, Integer productId) throws InternalServerErrorException {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("PKG_LOAN_PROCESS.PR_CALCULATE_LPF_LPC");
        storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, Long.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter(5, Long.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter(7, String.class, ParameterMode.OUT);
        storedProcedureQuery.setParameter(1, orgId);
        storedProcedureQuery.setParameter(2, applicationId);
        storedProcedureQuery.setParameter(3, productId);
        storedProcedureQuery.execute();
        Long lpfAmount = (Long) storedProcedureQuery.getOutputParameterValue(4);
        Long lpcAmount = (Long) storedProcedureQuery.getOutputParameterValue(5);
        String status = (String) storedProcedureQuery.getOutputParameterValue(6);
        String message = (String) storedProcedureQuery.getOutputParameterValue(7);
        if (!"1".equals(status)) {
            throw new InternalServerErrorException(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Map<String, Long> responseMap = new HashMap<>();
        responseMap.put("lpfAmount", lpfAmount);
        responseMap.put("lpcAmount", lpcAmount);
        return new Response(message, responseMap, HttpStatus.OK);
    }

    @Override
    public List<DisbursementDetail> findByStatusAndOrgId(String status, Long organizationId) throws BadRequestException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<DisbursementDetail> criteria = cb.createQuery(DisbursementDetail.class);
            Root<DisbursementDetail> disbursementDetails = criteria.from(DisbursementDetail.class);
            List<Predicate> predicates = new ArrayList<>();
            Join<DisbursementDetail, LoanDetail> loanDetail = disbursementDetails.join("loanDetail");
            Join<LoanDetail, ClientMaster> clientMaster = disbursementDetails.join("clientMaster");
            Join<LoanDetail, CenterMaster> centerMaster = loanDetail.join("centerMaster");
            Join<LoanDetail, ApplicationBankMapping> applicationBankMapping = loanDetail.join("applicationBankMappingList");
            Join<ApplicationBankMapping, ClientBankDetail> clientBankDetail = applicationBankMapping.join("clientBankDetail");
            predicates.add(cb.equal(disbursementDetails.get("status"), status));
            predicates.add(cb.equal(disbursementDetails.get("disbursementDetailPK").get("orgId"), organizationId));
            disbursementDetails.fetch("loanDetail");
            disbursementDetails.fetch("clientMaster");
            criteria.select(disbursementDetails).where(predicates.toArray(new Predicate[predicates.size()]));
            return entityManager.createQuery(criteria).getResultList();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public DisbursementDetail getDisbursementByLoanIdAndOrgId(Long loanId, Long orgId) throws BadRequestException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<DisbursementDetail> criteria = cb.createQuery(DisbursementDetail.class);
            Root<DisbursementDetail> disbursementDetails = criteria.from(DisbursementDetail.class);
            List<Predicate> predicates = new ArrayList<>();
            Join<DisbursementDetail, LoanDetail> loanDetail = disbursementDetails.join("loanDetail");
            predicates.add(cb.equal(disbursementDetails.get("disbursementDetailPK").get("orgId"), orgId));
            predicates.add(cb.equal(disbursementDetails.get("disbursementDetailPK").get("loanId"), loanId));
            disbursementDetails.fetch("loanDetail");
            disbursementDetails.fetch("clientMaster");
            criteria.select(disbursementDetails).where(predicates.toArray(new Predicate[predicates.size()]));
            return entityManager.createQuery(criteria).getResultList().stream().findFirst().get();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = InternalServerErrorException.class)
    @Procedure
    public Response submitLoanWaiver(FilterRequest filterRequest, UserSession userSession) throws InternalServerErrorException {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("PKG_LOAN_MISC_PROCESS.PR_UPDATE_LOAN_WAIVER");
        storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, LocalDate.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(5, BigDecimal.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(8, String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter(9, String.class, ParameterMode.OUT);
        storedProcedureQuery.setParameter(1, userSession.getOrganizationId());
        storedProcedureQuery.setParameter(2, filterRequest.getLoanId());
        storedProcedureQuery.setParameter(3, filterRequest.getWaiverType());
        storedProcedureQuery.setParameter(4, DateTimeUtil.stringToDate(filterRequest.getWaiverDate()));
        storedProcedureQuery.setParameter(5, filterRequest.getWaiverAmount());
        storedProcedureQuery.setParameter(6, filterRequest.getWaiverRemarks());
        storedProcedureQuery.setParameter(7, userSession.getUserId());
        storedProcedureQuery.execute();
        String status = (String) storedProcedureQuery.getOutputParameterValue(8);
        String message = (String) storedProcedureQuery.getOutputParameterValue(9);
        if (!"1".equals(status)) {
            throw new InternalServerErrorException(message, HttpStatus.BAD_REQUEST);
        }
        return new Response(message, HttpStatus.OK);
    }


    @Procedure
    @Transactional(rollbackFor = InternalServerErrorException.class)
    public Response reverseLoanWaiver(FilterRequest filterRequest, UserSession userSession) throws InternalServerErrorException {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("PKG_LOAN_MISC_PROCESS.PR_REVERSE_LOAN_WAIVER");
        storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.OUT);
        storedProcedureQuery.setParameter(1, userSession.getOrganizationId());
        storedProcedureQuery.setParameter(2, filterRequest.getLoanId());
        storedProcedureQuery.setParameter(3, filterRequest.getWaiverSeqNumber());
        storedProcedureQuery.setParameter(4, userSession.getUserId());
        storedProcedureQuery.execute();
        String status = (String) storedProcedureQuery.getOutputParameterValue(5);
        String message = (String) storedProcedureQuery.getOutputParameterValue(6);
        if (!"1".equals(status)) {
            throw new InternalServerErrorException(message, HttpStatus.BAD_REQUEST);
        }
        return new Response(message, HttpStatus.OK);
    }

    @Override
    public List<FirstEmiDateDto> getFirstEmiDates(Long orgId, Long applicationId, Date disbursementDateFormat) {
        ProcedureCall procedureCall = ((Session) entityManager.getDelegate()).getSession().createStoredProcedureCall("PKG_LOAN_PROCESS.PR_EMI_DATE_LIST", FirstEmiDateDto.class);
        procedureCall.registerParameter(1, Long.class, ParameterMode.IN).bindValue(orgId);
        procedureCall.registerParameter(2, Long.class, ParameterMode.IN).bindValue(applicationId);
        procedureCall.registerParameter(3, Date.class, ParameterMode.IN).bindValue(disbursementDateFormat);
        procedureCall.registerParameter(4, void.class, ParameterMode.REF_CURSOR);
        ProcedureOutputs procedureOutput = procedureCall.getOutputs();
        ResultSetOutput currOutput = (ResultSetOutput) procedureOutput.getCurrent();
        return currOutput.getResultList();
    }

    @Procedure
    public Response checkClientMobileDedupe(Long clientId, Long applicationId, String mobileNumber, UserSession userSession) throws BadRequestException {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("PKG_APPLICATION_PROCESS.PR_CLIENT_DEDUPE");
            storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(3, Long.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(6, Integer.class, ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter(7, String.class, ParameterMode.OUT);
            storedProcedureQuery.setParameter(1, userSession.getOrganizationId());
            storedProcedureQuery.setParameter(2, clientId);
            storedProcedureQuery.setParameter(3, applicationId);
            storedProcedureQuery.setParameter(4, mobileNumber);
            storedProcedureQuery.setParameter(5, userSession.getUserId());
            storedProcedureQuery.execute();
            Integer status = (Integer) storedProcedureQuery.getOutputParameterValue(6);
            String message = (String) storedProcedureQuery.getOutputParameterValue(7);
            if (1 != status) {
                throw new InternalServerErrorException(message, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new Response(message, HttpStatus.OK);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Long getTotalDisbursementHeaderCount(FilterRequest request) throws BadRequestException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
            Root<DisbursementDetail> disbursementDetails = criteria.from(DisbursementDetail.class);
            List<Predicate> predicates = getPredicates(request, cb, disbursementDetails, null);
            criteria.select(cb.count(disbursementDetails)).where(predicates.toArray(new Predicate[predicates.size()]));
            return entityManager.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}