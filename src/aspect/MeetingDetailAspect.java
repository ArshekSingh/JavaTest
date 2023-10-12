package aspect;

import com.sts.finncub.core.entity.MeetingDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
@Configuration
@Slf4j
public class MeetingDetailAspect {

    @Pointcut("execution(* org.springframework.data.jpa.repository.JpaRepository+.save(..))))")
    public void saveMethodInMeetingDetailRepository() {
    }

    @AfterReturning(value = "saveMethodInMeetingDetailRepository()", returning = "result")
    private void before(JoinPoint joinPoint, Object result) {
        if (result instanceof MeetingDetail) {
            MeetingDetail meetingDetail = (MeetingDetail) result;
            log.info("Aspect is working, congratulations. Jointpoint {} , result {}", joinPoint, meetingDetail.getAssignedTo() + " latitude: " + meetingDetail.getLatitude());
        }
    }
}
