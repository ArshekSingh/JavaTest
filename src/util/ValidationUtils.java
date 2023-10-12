package util;

import com.sts.finncub.core.constants.Constant;
import com.sts.finncub.core.dto.*;
import com.sts.finncub.core.enums.YblStatus;
import com.sts.finncub.core.exception.BadRequestException;
import com.sts.finncub.core.request.*;
import com.sts.finncub.core.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ValidationUtils implements Constant {

    public static void validateRequest(NeftDownloadExcelRequest request) throws BadRequestException {
        Date disburseDate = DateTimeUtil.parseDate(request.getStartDate(), DateTimeUtil.DD_MM_YYYY_MINUS);
        if (disburseDate == null)
            throw new BadRequestException("Invalid date format for startDate", HttpStatus.BAD_REQUEST);
        Date endDate = DateTimeUtil.parseDate(request.getEndDate(), DateTimeUtil.DD_MM_YYYY_MINUS);
        if (endDate == null) throw new BadRequestException("Invalid date format for endDate", HttpStatus.BAD_REQUEST);
    }

    public static void validateTransferRequest(EmployeeTransferRequest request) throws BadRequestException {
        if (request == null) throw new BadRequestException("Invalid request", HttpStatus.BAD_REQUEST);
        if (request.getEmployeeId() == null)
            throw new BadRequestException("EmployeeId can't be empty", HttpStatus.BAD_REQUEST);
    }

    public static void validateStatementRequest(BankStatementFilterRequest request) throws BadRequestException {
        if (request == null) throw new BadRequestException("Invalid request", HttpStatus.BAD_REQUEST);
        if (!StringUtils.hasText(request.getVoucherCode()))
            throw new BadRequestException("voucherCode can't be empty", HttpStatus.BAD_REQUEST);
        if (request.getVoucherNumber() == null)
            throw new BadRequestException("voucherNumber can't be null", HttpStatus.BAD_REQUEST);
    }

    public static void validateBankUploader(MultipartFile mFile, String voucherCode, Integer voucherNumber, String bankAccCode, String bankCode, String stmtDate) throws BadRequestException {
        if (mFile.isEmpty()) throw new BadRequestException("Please select file", HttpStatus.BAD_REQUEST);
        if (!StringUtils.hasText(bankCode))
            throw new BadRequestException("Bank Code can't be empty", HttpStatus.BAD_REQUEST);
        if (!StringUtils.hasText(bankAccCode))
            throw new BadRequestException("BankAccountCode can't be empty", HttpStatus.BAD_REQUEST);
        if (!StringUtils.hasText(voucherCode))
            throw new BadRequestException("Voucher code can't be empty", HttpStatus.BAD_REQUEST);
        if (voucherNumber == null)
            throw new BadRequestException("Voucher Number can't be null", HttpStatus.BAD_REQUEST);
        Date statementDate = DateTimeUtil.parseDate(stmtDate, DateTimeUtil.DD_MM_YYYY_MINUS);
        if (statementDate == null) throw new BadRequestException("Invalid statement date", HttpStatus.BAD_REQUEST);
    }

    public static void validateBankUpdateStatementRequest(BankUploaderHeaderRequestDto request) throws BadRequestException {
        if (!StringUtils.hasText(request.getVoucherCode()))
            throw new BadRequestException("Voucher code can't be empty", HttpStatus.BAD_REQUEST);
        if (request.getVoucherNumber() == null)
            throw new BadRequestException("Voucher Number can't be null", HttpStatus.BAD_REQUEST);
    }

    public static void validateBankStatementRequest(BankStatementFilterRequest request) throws BadRequestException {
        if (!StringUtils.hasText(request.getVoucherCode()))
            throw new BadRequestException("Voucher code can't be empty", HttpStatus.BAD_REQUEST);
        if (request.getVoucherNumber() == null)
            throw new BadRequestException("Voucher Number can't be null", HttpStatus.BAD_REQUEST);

    }

    public static void validateVoucherHeaderDto(VoucherHeaderDto voucherHeaderDto) throws BadRequestException {
        if (voucherHeaderDto == null) {
            throw new BadRequestException("Invalid Request body.", HttpStatus.BAD_REQUEST);
        }
        if (CollectionUtils.isEmpty(voucherHeaderDto.getVoucherDetailDtoList())) {
            throw new BadRequestException("Voucher detail request not exist.", HttpStatus.BAD_REQUEST);
        } else {
            List<VoucherDetailDto> voucherDetailDtoList = voucherHeaderDto.getVoucherDetailDtoList();
            for (VoucherDetailDto voucherDetailDto : voucherDetailDtoList) {
                if (!StringUtils.hasText(voucherHeaderDto.getDraftVoucherCode())) {
                    throw new BadRequestException("Draft Voucher Code not exist.", HttpStatus.BAD_REQUEST);
                }
                if (voucherHeaderDto.getDraftVoucherNumber() == null) {
                    throw new BadRequestException("Draft Voucher Number not exist.", HttpStatus.BAD_REQUEST);
                }
                if (voucherDetailDto.getAmount() == null) {
                    throw new BadRequestException("Amount not exist.", HttpStatus.BAD_REQUEST);
                }
                if (!StringUtils.hasText(voucherDetailDto.getDrcrFlag())) {
                    throw new BadRequestException("Drcr Flag not exist.", HttpStatus.BAD_REQUEST);
                }
                if (!StringUtils.hasText(voucherDetailDto.getPeriod())) {
                    throw new BadRequestException("Period not exist.", HttpStatus.BAD_REQUEST);
                }
                if (!StringUtils.hasText(voucherDetailDto.getAccount())) {
                    throw new BadRequestException("Account not exist.", HttpStatus.BAD_REQUEST);
                }
                if (!StringUtils.hasText(voucherDetailDto.getVoucherDate())) {
                    throw new BadRequestException("Voucher date not exist.", HttpStatus.BAD_REQUEST);
                }
                if (!StringUtils.hasText(voucherDetailDto.getCurrencyCode())) {
                    throw new BadRequestException("Currency code not exist.", HttpStatus.BAD_REQUEST);
                }
                if (!StringUtils.hasText(voucherDetailDto.getAccountKey())) {
                    throw new BadRequestException("Account key not exist.", HttpStatus.BAD_REQUEST);
                }
            }
        }
    }

    public static void validateCenterTransferRequest(MtCenterTransferRequestDto request) throws BadRequestException {
        if (!StringUtils.hasText(request.getFromUser())) {
            throw new BadRequestException("Please select employee whose center need to transfer!", HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.hasText(request.getToUser())) {
            throw new BadRequestException("Please select assignee name!", HttpStatus.BAD_REQUEST);
        }
        if (CollectionUtils.isEmpty(request.getCenterId())) {
            throw new BadRequestException("Please select atleast one center!", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateYblDetailRequest(FilterRequest request) throws BadRequestException {
        if (!StringUtils.hasText(request.getBcPartnerId())) {
            throw new BadRequestException("BC Partner can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.hasText(request.getBcPartnerId()) && !YBL.equals(request.getBcPartnerId())) {
            throw new BadRequestException("BC Partner should be " + YBL, HttpStatus.BAD_REQUEST);
        }
        if (request.getLimit() <= 0) {
            throw new BadRequestException("Limit can't be Zero", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateYblDownloadRequest(YblRequest request) throws BadRequestException {
        if (!StringUtils.hasText(request.getStatus())) {
            throw new BadRequestException("Status can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.hasText(request.getStatus())) {
            List<String> statusList = Arrays.stream(YblStatus.values()).map(Enum::name).collect(Collectors.toList());
            if (!statusList.contains(request.getStatus()))
                throw new BadRequestException("Invalid YBL status", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateBureauRuleParams(FilterRequest request) throws BadRequestException {
        if (!StringUtils.hasText(request.getBcPartner())) {
            throw new BadRequestException("BC partner can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.hasText(request.getCbPartner())) {
            throw new BadRequestException("CB partner can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (request.getProductGroupId() == null) {
            throw new BadRequestException("Product Group can't be empty", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateColendingLoanRequest(TaggingDto request) throws BadRequestException {
        if (request == null)
            throw new BadRequestException("Invalid request", HttpStatus.BAD_REQUEST);
        if (request.getSchemeId() == null)
            throw new BadRequestException("SchemeId is required", HttpStatus.BAD_REQUEST);
        if (!StringUtils.hasText(request.getClmLenderId()))
            throw new BadRequestException("LenderId is required", HttpStatus.BAD_REQUEST);
    }

    public static void validateInventoryRequest(ChequeInventoryDto request) throws BadRequestException {
        if (!StringUtils.hasText(request.getBankAccCode()))
            throw new BadRequestException("BankAccCode is required", HttpStatus.BAD_REQUEST);
        if (!StringUtils.hasText(request.getBranchId()))
            throw new BadRequestException("BranchId is required", HttpStatus.BAD_REQUEST);
        if (!StringUtils.hasText(request.getChequeBookletNo()))
            throw new BadRequestException("ChequeBookletNo is required", HttpStatus.BAD_REQUEST);
        if (!StringUtils.hasText(request.getChequeBookletDate()))
            throw new BadRequestException("ChequeBookletDate is required", HttpStatus.BAD_REQUEST);
        if (!StringUtils.hasText(request.getFromChequeNo()))
            throw new BadRequestException("FromChequeNo is required", HttpStatus.BAD_REQUEST);
        if (!StringUtils.hasText(request.getToChequeNo()))
            throw new BadRequestException("ToChequeNo is required", HttpStatus.BAD_REQUEST);
    }

    public static void validateIndusSanctionRequest(IblRequest request) throws BadRequestException {
        if (!StringUtils.hasText(request.getBatchId())) {
            log.info("BatchId is required");
            throw new BadRequestException("BatchId is required", HttpStatus.BAD_REQUEST);
        }
        if (!request.getBcId().equalsIgnoreCase(IBL_CLM)) {
            throw new BadRequestException("Bc Id is not correct", HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.hasText(request.getBcId())) {
            log.info("BcId is required");
            throw new BadRequestException("BcId is required", HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.hasText(request.getEntityType())) {
            log.info("Entity type is required");
            throw new BadRequestException("Entity type is required", HttpStatus.BAD_REQUEST);
        }
        if (!"SRC".equalsIgnoreCase(request.getEntityType()) && !"SNC".equalsIgnoreCase(request.getEntityType())) {
            log.info("Entity type is not correct");
            throw new BadRequestException("Entity type is not correct", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateIndiaBullsSanctionRequest(IndiaBullsRequest request) throws BadRequestException {
        if (!StringUtils.hasText(request.getApplicationId())) {
            throw new BadRequestException("Application Id is required", HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.hasText(request.getBcId())) {
            throw new BadRequestException("Bc Id is required", HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.hasText(request.getProcMethod())) {
            throw new BadRequestException("Proc Method is required", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateIblDetailRequest(FilterRequest request) throws BadRequestException {
        if (!StringUtils.hasText(request.getBcPartnerId())) {
            throw new BadRequestException("BC Partner can't be empty", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.hasText(request.getBcPartnerId()) && !IBL_CLM.equals(request.getBcPartnerId())) {
            throw new BadRequestException("BC Partner should be " + IBL_CLM, HttpStatus.BAD_REQUEST);
        }
        if (request.getLimit() <= 0) {
            throw new BadRequestException("Limit can't be Zero", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateIndiaBullsColendingDataRequest(FilterRequest request) throws BadRequestException {
        if (!StringUtils.hasText(request.getSanctionStatus())) {
            throw new BadRequestException("Sanction status is required", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateJanaCollectionRequest(String batchId, String entityType) throws BadRequestException {
        if (!StringUtils.hasText(batchId)) {
            throw new BadRequestException("BatchId is required", HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.hasText(entityType)) {
            throw new BadRequestException("entity type is required", HttpStatus.BAD_REQUEST);
        }
        if (!entityType.equals("XLS")) {
            throw new BadRequestException("Invalid entityType", entityType, HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateValue(String value) throws BadRequestException {
        if (!StringUtils.hasText(value)) {
            throw new BadRequestException("Value is required", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateFunderAllocationDetailUploader(MultipartFile file, String allocationDate) throws BadRequestException {
        if (file.isEmpty()) throw new BadRequestException("Please select file", HttpStatus.BAD_REQUEST);
        if (!StringUtils.hasText(allocationDate))
            throw new BadRequestException("allocationDate can't be empty", HttpStatus.BAD_REQUEST);
    }

    public static void validateUnitySourceFileRequest(List<UnitySourcingDto> unityDtos) throws BadRequestException {
        for (int i = 0; i < unityDtos.size(); i++) {
            if (!StringUtils.hasText(unityDtos.get(i).getExternalLanNo())) {
                throw new BadRequestException(i + 1 + " External Lan number cannot be empty for this row no.", HttpStatus.BAD_REQUEST);
            }
            if (!StringUtils.hasText(unityDtos.get(i).getBcName())) {
                throw new BadRequestException(i + 1 + " BC Name cannot be empty for this row no.", HttpStatus.BAD_REQUEST);
            }
            if (!StringUtils.hasText(unityDtos.get(i).getCenterId())) {
                throw new BadRequestException(i + 1 + " Center id cannot be empty for this row no.", HttpStatus.BAD_REQUEST);
            }
            if (!StringUtils.hasText(unityDtos.get(i).getCustomerName())) {
                throw new BadRequestException(i + 1 + " Customer Name cannot be empty for this row no.", HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static void validateUnityDisbursementFileRequest(List<UnityDisbursementUploaderRequest> unityDtos) throws BadRequestException {
        for (int i = 0; i < unityDtos.size(); i++) {
            if (!StringUtils.hasText(unityDtos.get(i).getExternalLanNo())) {
                throw new BadRequestException(i + 1 + " External Lan number cannot be empty for this row no.", HttpStatus.BAD_REQUEST);
            }
            if ((unityDtos.get(i).getLoanAmount() == null)) {
                throw new BadRequestException(i + 1 + " LOAN AMOUNT Name cannot be empty for this row no.", HttpStatus.BAD_REQUEST);
            }
            if (!StringUtils.hasText(unityDtos.get(i).getDisbDate())) {
                throw new BadRequestException(i + 1 + " DisbDate cannot be empty for this row no.", HttpStatus.BAD_REQUEST);
            }
            if (!StringUtils.hasText(unityDtos.get(i).getNeftDate())) {
                throw new BadRequestException(i + 1 + " NeftDate cannot be empty for this row no.", HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static Response validateAddInventoryRequest(InventoryDetailsDto dto) {
        if(!StringUtils.hasText(dto.getAssetOwner())) {
            return new Response("Asset owner is mandatory",HttpStatus.BAD_REQUEST);
        }
        if(!StringUtils.hasText(dto.getAssetType())) {
            return new Response("Asset type is mandatory",HttpStatus.BAD_REQUEST);

        }
        if(!StringUtils.hasText(dto.getInventoryPurchaseDate())) {
            return new Response("Purchase date is mandatory",HttpStatus.BAD_REQUEST);
        }
        if(!StringUtils.hasText(dto.getSerialNo())) {
            return new Response("Serial no is mandatory",HttpStatus.BAD_REQUEST);
        }
        return new Response(SUCCESS,HttpStatus.OK);
    }

    public static void validateButtonRoleMappingRequest(ButtonRoleRequest request) throws BadRequestException {
        if(request.getMenuId() == null) {
            throw new BadRequestException("Menu Id is required", HttpStatus.BAD_REQUEST);
        }
        if(!StringUtils.hasText(request.getButtonName())) {
            throw new BadRequestException("Button Name is required", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateGetButtonRoleMapRequest(ButtonRoleRequest request) throws BadRequestException {
        if(request.getMenuId() == null) {
            throw new BadRequestException("Menu Id is required");
        }
        if(!StringUtils.hasText(request.getButtonName())) {
            throw new BadRequestException("Button name is required");
        }
    }
}