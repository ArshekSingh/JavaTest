package util;

import com.sts.finncub.core.entity.ClientFamilyMemberDetail;
import com.sts.finncub.core.entity.ClientMaster;
import com.sts.finncub.core.entity.ClientMasterDraft;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FinncubUtil {

    public String constructName(ClientMasterDraft request) {
        String clientName = request.getFirstName();
        if (StringUtils.hasText(request.getMiddleName())) {
            clientName = clientName + " " + request.getMiddleName();
        }
        if (StringUtils.hasText(request.getLastName())) {
            clientName = clientName + " " + request.getLastName();
        }
        return clientName;
    }

    public String constructFamilyMemberName(ClientFamilyMemberDetail request) {
		if (request == null)
			return "";
        String clientName = request.getFirstName();
        if (StringUtils.hasText(request.getLastName())) {
            clientName = clientName + " " + request.getLastName();
        }
        return clientName;
    }

    public String constructClientName(ClientMaster clientMaster) {
        String clientName = "";
        if (clientMaster == null) return clientName;
        if (StringUtils.hasText(clientMaster.getFirstName())) {
            clientName = clientMaster.getFirstName();
        }
        if (StringUtils.hasText(clientMaster.getMiddleName())) {
            clientName = clientName + " " + clientMaster.getMiddleName();
        }
        if (StringUtils.hasText(clientMaster.getLastName())) {
            clientName = clientName + " " + clientMaster.getLastName();
        }
        return clientName;
    }
}