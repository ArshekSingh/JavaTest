package entity;

import com.sts.finncub.core.entity.ClientMaster;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CheckUserSpecification implements Specification<ClientMaster> {

    private final String aadharNumber;
    private final String votercardNumber;
    private final Long orgId;
    private final Long clientId;
    private final String kycType;
    private final String kycId;

    public CheckUserSpecification(String aadharNumber, String votercardNumber, Long orgId, Long clientId, String kycType, String kycId) {
        this.aadharNumber = aadharNumber;
        this.votercardNumber = votercardNumber;
        this.orgId = orgId;
        this.clientId = clientId;
        this.kycType = kycType;
        this.kycId = kycId;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<ClientMaster> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate orgIdPredicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("clientMasterPK").get("orgId"), orgId));
        List<Predicate> orList = new ArrayList<>();
        if (StringUtils.hasText(aadharNumber)) {
            orList.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("aadharCardNumberOrig"), aadharNumber)));
        }
        if (StringUtils.hasText(votercardNumber)) {
            orList.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("votercardNumber"), votercardNumber)));
        }
        if(clientId != null) {
            orList.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("clientMasterPK").get("clientId"), clientId)));
        }
        if(StringUtils.hasText(kycType) && StringUtils.hasText(kycId) && "PA".equalsIgnoreCase(kycType)) {
            orList.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("kycId"), kycId)));
        }
        Predicate[] array = new Predicate[orList.size()];
        Predicate orPredicate = criteriaBuilder.or(orList.toArray(array));
        predicates.add(orgIdPredicate);
        predicates.add(orPredicate);
        return criteriaBuilder.and(predicates.toArray(new Predicate[2]));
    }
}