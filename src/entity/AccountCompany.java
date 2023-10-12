package entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "ACCOUNT_COMPANY")
public class AccountCompany extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2338626292552177485L;

    @EmbeddedId
    private AccountCompanyPK accountCompanyPK;

    @Column(name = "SUB_ACCOUNT")
    private String subAccount;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "BOOK_FLAG")
    private String bookFlag;

    @Column(name = "OPEN_ITEM_FLAG")
    private String openItemFlag;

    @Column(name = "DBCR_INDICATOR")
    private String dbcrIndicator;

    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;

    @Column(name = "EFFECTIVE_DATE")
    private LocalDate effectiveDate;

    @Column(name = "BANK_ACC_CODE")
    private String bankAccCode;

    @NotAudited
    @JoinColumns({
            @JoinColumn(name = "ACCOUNT_KEY", referencedColumnName = "ACCOUNT_KEY", insertable = false, updatable = false)
    })
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountMaster accountMaster;
}