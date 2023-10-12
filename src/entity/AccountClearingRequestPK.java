package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class AccountClearingRequestPK implements Serializable {

    @Column(name = "BATCH_ID")
    private String batchId;

    @Column(name = "ORG_ID")
    private Long orgId;

    @Column(name = "VOUCHER_CODE")
    private String voucherCode;

    @Column(name = "VOUCHER_NUMBER")
    private Integer voucherNumber;

    @Column(name = "SEQUENCE")
    private Integer sequence;
}