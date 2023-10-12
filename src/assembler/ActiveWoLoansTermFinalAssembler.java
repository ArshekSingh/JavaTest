package assembler;

import com.sts.finncub.core.constants.Constant;
import com.sts.finncub.core.dto.ActiveWoLoansAddTermFinalDto;
import com.sts.finncub.core.entity.ActiveWoLoansAddTermFinal;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActiveWoLoansTermFinalAssembler implements Constant {
    public ActiveWoLoansAddTermFinalDto entityToDto(ActiveWoLoansAddTermFinal activeWoLoansAddTermFinal) {
        ActiveWoLoansAddTermFinalDto activeWoLoansAddTermFinalDto = new ActiveWoLoansAddTermFinalDto();
        activeWoLoansAddTermFinalDto.setLoanId(activeWoLoansAddTermFinal.getLoanId());
        activeWoLoansAddTermFinalDto.setReportDate(activeWoLoansAddTermFinal.getReportDate());
        activeWoLoansAddTermFinalDto.setDistrictName(activeWoLoansAddTermFinal.getDistrictName());
        activeWoLoansAddTermFinalDto.setStateId(activeWoLoansAddTermFinal.getStateId());
        activeWoLoansAddTermFinalDto.setStateName(activeWoLoansAddTermFinal.getStateName());
        activeWoLoansAddTermFinalDto.setZoId(activeWoLoansAddTermFinal.getZoId());
        activeWoLoansAddTermFinalDto.setZoName(activeWoLoansAddTermFinal.getZoName());
        activeWoLoansAddTermFinalDto.setDivId(activeWoLoansAddTermFinal.getDivId());
        activeWoLoansAddTermFinalDto.setDivisionName(activeWoLoansAddTermFinal.getDivisionName());
        activeWoLoansAddTermFinalDto.setBranchId(activeWoLoansAddTermFinal.getBranchId());
        activeWoLoansAddTermFinalDto.setOldBranchCode(activeWoLoansAddTermFinal.getOldBranchCode());
        activeWoLoansAddTermFinalDto.setBranchName(activeWoLoansAddTermFinal.getBranchName());
        activeWoLoansAddTermFinalDto.setBranchAddress(activeWoLoansAddTermFinal.getBranchAddress());
        activeWoLoansAddTermFinalDto.setBranchLatitude(activeWoLoansAddTermFinal.getBranchLatitude());
        activeWoLoansAddTermFinalDto.setBranchLongitude(activeWoLoansAddTermFinal.getBranchLongitude());
        activeWoLoansAddTermFinalDto.setPurpose(activeWoLoansAddTermFinal.getPurpose());
        activeWoLoansAddTermFinalDto.setSubPurpose(activeWoLoansAddTermFinal.getSubPurpose());
        activeWoLoansAddTermFinalDto.setClientName(activeWoLoansAddTermFinal.getClientName());
        activeWoLoansAddTermFinalDto.setDob(activeWoLoansAddTermFinal.getDob());
        activeWoLoansAddTermFinalDto.setAge(activeWoLoansAddTermFinal.getAge());
        activeWoLoansAddTermFinalDto.setGender(activeWoLoansAddTermFinal.getGender());
        activeWoLoansAddTermFinalDto.setMobileNumber(activeWoLoansAddTermFinal.getMobileNumber());
        activeWoLoansAddTermFinalDto.setCaste(activeWoLoansAddTermFinal.getCaste());
        activeWoLoansAddTermFinalDto.setReligion(activeWoLoansAddTermFinal.getReligion());
        activeWoLoansAddTermFinalDto.setOccupation(activeWoLoansAddTermFinal.getOccupation());
        activeWoLoansAddTermFinalDto.setAadharNumber(activeWoLoansAddTermFinal.getAadharNumber());
        activeWoLoansAddTermFinalDto.setPancardNumber(activeWoLoansAddTermFinal.getPancardNumber());
        activeWoLoansAddTermFinalDto.setVotercardNumber(activeWoLoansAddTermFinal.getVotercardNumber());
        activeWoLoansAddTermFinalDto.setLoanCode(activeWoLoansAddTermFinal.getLoanCode());
        activeWoLoansAddTermFinalDto.setClientId(activeWoLoansAddTermFinal.getClientId());
        activeWoLoansAddTermFinalDto.setCenterId(activeWoLoansAddTermFinal.getCenterId());
        activeWoLoansAddTermFinalDto.setCenterName(activeWoLoansAddTermFinal.getCenterName());
        activeWoLoansAddTermFinalDto.setCenterCode(activeWoLoansAddTermFinal.getCenterCode());
        activeWoLoansAddTermFinalDto.setAssignedTo(activeWoLoansAddTermFinal.getAssignedTo());
        activeWoLoansAddTermFinalDto.setUserName(activeWoLoansAddTermFinal.getUserName());
        activeWoLoansAddTermFinalDto.setPos(activeWoLoansAddTermFinal.getPos());
        activeWoLoansAddTermFinalDto.setPrinCollAmt(activeWoLoansAddTermFinal.getPrinCollAmt());
        activeWoLoansAddTermFinalDto.setIntCollAmt(activeWoLoansAddTermFinal.getIntCollAmt());
        activeWoLoansAddTermFinalDto.setIntPos(activeWoLoansAddTermFinal.getIntPos());
        activeWoLoansAddTermFinalDto.setProductId(activeWoLoansAddTermFinal.getProductId());
        activeWoLoansAddTermFinalDto.setProductCode(activeWoLoansAddTermFinal.getProductCode());
        activeWoLoansAddTermFinalDto.setProductName(activeWoLoansAddTermFinal.getProductName());
        activeWoLoansAddTermFinalDto.setBcId(activeWoLoansAddTermFinal.getBcId());
        activeWoLoansAddTermFinalDto.setPortfolioType(activeWoLoansAddTermFinal.getPortfolioType());
        activeWoLoansAddTermFinalDto.setLastCollDate(activeWoLoansAddTermFinal.getLastCollDate());
        activeWoLoansAddTermFinalDto.setFifoPar(activeWoLoansAddTermFinal.getFifoPar());
        activeWoLoansAddTermFinalDto.setFifoParDays(activeWoLoansAddTermFinal.getFifoParDays());
        activeWoLoansAddTermFinalDto.setDisbursementDate(activeWoLoansAddTermFinal.getDisbursementDate());
        activeWoLoansAddTermFinalDto.setClosureDate(activeWoLoansAddTermFinal.getClosureDate());
        activeWoLoansAddTermFinalDto.setInterestRate(activeWoLoansAddTermFinal.getInterestRate());
        activeWoLoansAddTermFinalDto.setTenureInMonths(activeWoLoansAddTermFinal.getTenureInMonths());
        activeWoLoansAddTermFinalDto.setPrincipalAmount(activeWoLoansAddTermFinal.getPrincipalAmount());
        activeWoLoansAddTermFinalDto.setInterestAmount(activeWoLoansAddTermFinal.getInterestAmount());
        activeWoLoansAddTermFinalDto.setDemandPrincipal(activeWoLoansAddTermFinal.getDemandPrincipal());
        activeWoLoansAddTermFinalDto.setDemandInterest(activeWoLoansAddTermFinal.getDemandInterest());
        activeWoLoansAddTermFinalDto.setProcessingFee(activeWoLoansAddTermFinal.getProcessingFee());
        activeWoLoansAddTermFinalDto.setTotalInstallments(activeWoLoansAddTermFinal.getTotalInstallments());
        activeWoLoansAddTermFinalDto.setDisbursedAmount(activeWoLoansAddTermFinal.getDisbursedAmount());
        activeWoLoansAddTermFinalDto.setLoanCycle(activeWoLoansAddTermFinal.getLoanCycle());
        activeWoLoansAddTermFinalDto.setUtilizationCheckDate(activeWoLoansAddTermFinal.getUtilizationCheckDate());
        activeWoLoansAddTermFinalDto.setMaturityDate(activeWoLoansAddTermFinal.getMaturityDate());
        activeWoLoansAddTermFinalDto.setEmiAmount(activeWoLoansAddTermFinal.getEmiAmount());
        activeWoLoansAddTermFinalDto.setNextRepayDate(activeWoLoansAddTermFinal.getNextRepayDate());
        activeWoLoansAddTermFinalDto.setRemarks(activeWoLoansAddTermFinal.getRemarks());
        activeWoLoansAddTermFinalDto.setFunderId(activeWoLoansAddTermFinal.getFunderId());
        activeWoLoansAddTermFinalDto.setFirstDueDate(activeWoLoansAddTermFinal.getFirstDueDate());
        activeWoLoansAddTermFinalDto.setTotalPrincipalDue(activeWoLoansAddTermFinal.getTotalPrincipalDue());
        activeWoLoansAddTermFinalDto.setTotalInterestDue(activeWoLoansAddTermFinal.getTotalInterestDue());
        activeWoLoansAddTermFinalDto.setMtdDue(activeWoLoansAddTermFinal.getMtdDue());
        activeWoLoansAddTermFinalDto.setMtdCollection(activeWoLoansAddTermFinal.getMtdCollection());
        activeWoLoansAddTermFinalDto.setTotalPrincipalCollection(activeWoLoansAddTermFinal.getTotalPrincipalCollection());
        activeWoLoansAddTermFinalDto.setTotalIntCollection(activeWoLoansAddTermFinal.getTotalIntCollection());
        activeWoLoansAddTermFinalDto.setTotalCollection(activeWoLoansAddTermFinal.getTotalCollection());
        activeWoLoansAddTermFinalDto.setTotalDemandDue(activeWoLoansAddTermFinal.getTotalDemandDue());
        activeWoLoansAddTermFinalDto.setAdvanceAmt(activeWoLoansAddTermFinal.getAdvanceAmt());
        activeWoLoansAddTermFinalDto.setParDays(activeWoLoansAddTermFinal.getParDays());
        activeWoLoansAddTermFinalDto.setPar(activeWoLoansAddTermFinal.getPar());
        activeWoLoansAddTermFinalDto.setAmtinarr(activeWoLoansAddTermFinal.getAmtinarr());
        activeWoLoansAddTermFinalDto.setIntinarr(activeWoLoansAddTermFinal.getIntinarr());
        activeWoLoansAddTermFinalDto.setInstallmentOs(activeWoLoansAddTermFinal.getInstallmentOs());
        activeWoLoansAddTermFinalDto.setInstallmentInArrear(activeWoLoansAddTermFinal.getInstallmentInArrear());
        activeWoLoansAddTermFinalDto.setBalanceTenAsOn(activeWoLoansAddTermFinal.getBalanceTenAsOn());
        activeWoLoansAddTermFinalDto.setAdvanceEmi(activeWoLoansAddTermFinal.getAdvanceEmi());
        activeWoLoansAddTermFinalDto.setProfessionType(activeWoLoansAddTermFinal.getProfessionType());
        activeWoLoansAddTermFinalDto.setTypeOfIdProof(activeWoLoansAddTermFinal.getTypeOfIdProof());
        activeWoLoansAddTermFinalDto.setIdNumber(activeWoLoansAddTermFinal.getIdNumber());
        activeWoLoansAddTermFinalDto.setBankName(activeWoLoansAddTermFinal.getBankName());
        activeWoLoansAddTermFinalDto.setBankBranch(activeWoLoansAddTermFinal.getBankBranch());
        activeWoLoansAddTermFinalDto.setBankAccountNumber(activeWoLoansAddTermFinal.getBankAccountNumber());
        activeWoLoansAddTermFinalDto.setBankIfscCode(activeWoLoansAddTermFinal.getBankIfscCode());
        activeWoLoansAddTermFinalDto.setAddress(activeWoLoansAddTermFinal.getAddress());
        activeWoLoansAddTermFinalDto.setPinCode(activeWoLoansAddTermFinal.getPinCode());
        activeWoLoansAddTermFinalDto.setRepaymentFrequency(activeWoLoansAddTermFinal.getRepaymentFrequency());
        activeWoLoansAddTermFinalDto.setAnnualIncome(activeWoLoansAddTermFinal.getAnnualIncome());
        activeWoLoansAddTermFinalDto.setAppliedOn(activeWoLoansAddTermFinal.getAppliedOn());
        activeWoLoansAddTermFinalDto.setAppliedAmount(activeWoLoansAddTermFinal.getAppliedAmount());
        return activeWoLoansAddTermFinalDto;
    }

    public List<ActiveWoLoansAddTermFinalDto> entityToDtoList(List<ActiveWoLoansAddTermFinal> requests) {
        if (CollectionUtils.isEmpty(requests))
            return Collections.emptyList();
        return requests.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
