package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class AccountCompanyPK implements Serializable {

    @Column(name = "ORG_ID")
    private Long orgId;

    @Column(name = "ACCOUNT_KEY")
    private String accountKey;
}
