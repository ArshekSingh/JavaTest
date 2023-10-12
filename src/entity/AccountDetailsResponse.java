package entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDetailsResponse {
    private String accountName;
    private String accountKey;
    private String branchCode;
    private BigDecimal debit;
    private BigDecimal credit;
}
