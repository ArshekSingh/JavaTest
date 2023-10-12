package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "ACCOUNT_CLEARING_REQUEST")
public class AccountClearingRequest extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2338626292552177485L;

    @EmbeddedId
    private AccountClearingRequestPK accountClearingRequestPK;

    @Column(name = "PERIOD")
    private String period;

    @Column(name = "VOUCHER_DATE")
    private LocalDate voucherDate;

    @Column(name = "ATTR_1")
    private String attr1;

    @Column(name = "ATTR_2")
    private String attr2;
}