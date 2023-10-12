package assembler;

import com.sts.finncub.core.dto.ApplicationDetailDto;
import com.sts.finncub.core.entity.ApplicationDetails;
import com.sts.finncub.core.entity.CenterMaster;
import com.sts.finncub.core.util.DateTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class ApplicationAssembler {
    public ApplicationDetailDto entityToDto(ApplicationDetails applicationDetails) {
        ApplicationDetailDto applicationDetailDto = new ApplicationDetailDto();
        applicationDetailDto.setApplicationId(applicationDetails.getApplicationDetailsPK().getApplicationId());
        applicationDetailDto.setOrgId(applicationDetails.getApplicationDetailsPK().getOrgId());
        applicationDetailDto.setAppliedOn(DateTimeUtil.dateToString(applicationDetails.getAppliedOn()));
        applicationDetailDto.setExpectedDisbursementOn(DateTimeUtil.dateToString(applicationDetails.getExpectedDisbursementOn()));
        applicationDetailDto.setApprovedOn(DateTimeUtil.dateToString(applicationDetails.getApprovedOn()));
        applicationDetailDto.setMandateRequestOn(DateTimeUtil.dateToString(applicationDetails.getMandateRequestOn()));
        applicationDetailDto.setInfoVerifiedOn(DateTimeUtil.dateTimeToString(applicationDetails.getInfoVerifiedOn()));
        applicationDetailDto.setSanctionDate(DateTimeUtil.dateToString(applicationDetails.getSanctionDate()));
        applicationDetailDto.setAppliedAmount(applicationDetails.getAppliedAmount().longValue());
        applicationDetailDto.setApplicationStatus(applicationDetails.getApplicationStatus());
        applicationDetailDto.setAppRejReason(applicationDetails.getAppRejReason());
        applicationDetailDto.setCenterId(applicationDetails.getCenterId());
        applicationDetailDto.setCenterGroupCode(applicationDetails.getCenterGroupCode());
        applicationDetailDto.setLoanPurposeId(applicationDetails.getLoanPurposeId());
        applicationDetailDto.setEligibilityAmount(applicationDetails.getEligibilityAmount());
        applicationDetailDto.setFoId(applicationDetails.getFoId());
        applicationDetailDto.setCycle(applicationDetails.getCycle());
        applicationDetailDto.setProcessingCharge(applicationDetails.getProcessingCharge());
        applicationDetailDto.setInsuranceCharge(applicationDetails.getInsuranceCharge());
        applicationDetailDto.setPayableAmount(applicationDetails.getPayableAmount());
        applicationDetailDto.setBureauResult(applicationDetails.getBureauResult());
        applicationDetailDto.setIsTopUp(applicationDetails.getIsTopUp());
        applicationDetailDto.setIsAgreementCopyAvail(applicationDetails.getIsAgreementCopyAvail());
        applicationDetailDto.setHomeVisitFlg(applicationDetails.getHomeVisitFlg());
        applicationDetailDto.setMandateStatus(applicationDetails.getMandateStatus());
        applicationDetailDto.setMandateRejReason(applicationDetails.getMandateRejReason());
        applicationDetailDto.setMandateUmrn(applicationDetails.getMandateUmrn());
        applicationDetailDto.setInfoVerifiedStatus(applicationDetails.getInfoVerifiedStatus());
        applicationDetailDto.setInfoVerifiedBy(applicationDetails.getInfoVerifiedBy());
        applicationDetailDto.setInfoVerifiedRejReason(applicationDetails.getInfoVerifiedRejReason());
        applicationDetailDto.setInfoVerifiedRejCount(applicationDetails.getInfoVerifiedRejCount());
        applicationDetailDto.setExtApplicationId(applicationDetails.getExtApplicationId());
        applicationDetailDto.setProductGroupId(applicationDetails.getProductGroupId());
        applicationDetailDto.setProductId(applicationDetails.getProductId());
        applicationDetailDto.setLoanSubPurposeId(applicationDetails.getLoanSubPurposeId());
        applicationDetailDto.setExtCustId(applicationDetails.getExtCustId());
        applicationDetailDto.setEkycNumber(applicationDetails.getEkycNumber());
        applicationDetailDto.setEKycReferenceKey(applicationDetails.getEKycReferenceKey());
        applicationDetailDto.setIsHealthCareDisbursed(applicationDetails.getIsHealthCareDisbursed());
        applicationDetailDto.setApplicationCode(applicationDetails.getApplicationCode());
        applicationDetailDto.setExtLanNo(applicationDetails.getExtLanNo());
        applicationDetailDto.setSanctionAmount(applicationDetails.getSanctionAmount());
        applicationDetailDto.setExtLoanId(applicationDetails.getExtLoanId());
        applicationDetailDto.setCersaiCharge(applicationDetails.getCersaiCharge());
        applicationDetailDto.setDocumentationCharge(applicationDetails.getDocumentationCharge());
        applicationDetailDto.setSecurityDeposit(applicationDetails.getSecurityDeposit());
        applicationDetailDto.setIsMobileVerified(applicationDetails.getIsMobileVerified());
        applicationDetailDto.setEmiDate(applicationDetails.getEmiDate());
        applicationDetailDto.setFirstEmiDate(DateTimeUtil.format(applicationDetails.getFirstEmiDate(), DateTimeUtil.DD_MM_YYYYDbFormat));
        applicationDetailDto.setOtherHospicashAmt(applicationDetails.getOtherHospicashAmt());
        applicationDetailDto.setIsEKycVerified(applicationDetails.getIsEKycVerified());
        applicationDetailDto.setEkycSkipReason(applicationDetails.getEkycSkipReason());
        applicationDetailDto.setRemarks(applicationDetails.getRemarks());
        applicationDetailDto.setKycSource(applicationDetails.getKycSource());
        applicationDetailDto.setClientId(applicationDetails.getClientId());
        applicationDetailDto.setInsertedOn(DateTimeUtil.dateTimeToString(applicationDetails.getInsertedOn()));
        applicationDetailDto.setInsertedBy(applicationDetails.getInsertedBy());
        applicationDetailDto.setUpdatedOn(DateTimeUtil.dateTimeToString(applicationDetails.getUpdatedOn()));
        applicationDetailDto.setUpdatedBy(applicationDetails.getUpdatedBy());
        return applicationDetailDto;
    }

    public ApplicationDetailDto entityToDtoCenter(CenterMaster centerMaster) {
        ApplicationDetailDto applicationDetailDto = new ApplicationDetailDto();
        applicationDetailDto.setCenterId(centerMaster.getCenterMasterPK().getCenterId());
        applicationDetailDto.setCenterCode(centerMaster.getCenterCode());
        applicationDetailDto.setCenterName(centerMaster.getCenterName());
        applicationDetailDto.setCenterStatus(centerMaster.getStatus());
        if (centerMaster.getEmiDate() != null) {
            applicationDetailDto.setEmiDate(centerMaster.getEmiDate());
        }
        applicationDetailDto.setBranchId(centerMaster.getBranchId());
        applicationDetailDto.setLatitude(centerMaster.getLatitude());
        applicationDetailDto.setLongitude(centerMaster.getLongitude());
        applicationDetailDto.setInsertedOn(DateTimeUtil.dateTimeToString(centerMaster.getInsertedOn()));
        applicationDetailDto.setInsertedBy(centerMaster.getInsertedBy());
        applicationDetailDto.setUpdatedOn(DateTimeUtil.dateTimeToString(centerMaster.getUpdatedOn()));
        applicationDetailDto.setUpdatedBy(centerMaster.getUpdatedBy());
        return applicationDetailDto;
    }
}