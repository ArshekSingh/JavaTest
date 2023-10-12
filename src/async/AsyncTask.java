package async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sts.finncub.core.constants.Constant;
import com.sts.finncub.core.entity.UserSession;
import com.sts.finncub.core.exception.BadRequestException;
import com.sts.finncub.kyc.service.HiMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static com.sts.finncub.kyc.async.ThreadPoolConfig.CB_THREAD_POOL_NAME;

@Slf4j
@Component
public class AsyncTask implements Constant {

    private final HiMarkService hiMarkService;

    @Autowired
    public AsyncTask(HiMarkService hiMarkService) {
        this.hiMarkService = hiMarkService;
    }

    @Async(CB_THREAD_POOL_NAME)
    public void fetchCIRReport(Long applicationId, UserSession userSession) throws JsonProcessingException, BadRequestException {
        try {
            hiMarkService.fetchReport(applicationId, userSession, AUTO_CB);
        } catch (Exception e) {
            log.error("Exception occurred while fetching CIR report for[APP_ID] {}", applicationId);
        }
    }

    @Async
    public void prepareRequestForS1HiMark(Long applicationId, UserSession userSession) {
        try {
            hiMarkService.prepareRequestForS1HiMark(applicationId, userSession);
        } catch (Exception e) {
            log.error("Exception occurred while fetching CIR report for[APP_ID] {}", applicationId);
        }
    }
}
