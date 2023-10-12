package aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
@Configuration
@Slf4j
public class UserLocationAspect {

//    private final MeetingDetailRepository meetingDetailRepository;
//    private final UserRepository userRepository;
//    private final FirebaseMessagingService firebaseMessagingService;
//
//    @Pointcut("execution(* org.springframework.data.jpa.repository.JpaRepository+.save(..))))")
//    public void saveMethodInUserLocationTrackerRepository() {
//    }
//
//    @AfterReturning(value = "saveMethodInUserLocationTrackerRepository()", returning = "result")
//    private void before(JoinPoint joinPoint, Object result) {
//        if (result instanceof UserLocationTracker) {
//            UserLocationTracker userLocationTracker = (UserLocationTracker) result;
//            log.info("Aspect is working, congratulations. Jointpoint {} , result {}", joinPoint, userLocationTracker.getUserId() + " latitude: " + userLocationTracker.getLattitude());
//            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//            List<MeetingDetail> userMeetings = meetingDetailRepository.findTodaysPendingMeetingByUserId(userLocationTracker.getUserId(), LocalDate.now(), currentTime, "S");
//            if (userMeetings == null || userMeetings.isEmpty()) {
//                log.info("No meeting is in scheduled status for user: {}", userLocationTracker.getUserId());
//            } else {
//                try {
//                    Optional<User> user = userRepository.findByUserId(userLocationTracker.getUserId());
//                    if (user.isPresent()) {
//                        if (user.get().getFbToken() != null && !user.get().getFbToken().isEmpty()) {
//                            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm a"));
//                            firebaseMessagingService.sendNotification("You are running late!", "Hurry up! By " + time + ", you should have covered " + userMeetings.size() + " meetings till now! \n", user.get().getFbToken());
//                            log.info("Notification sent to user: {}", user.get().getUserId());
//                        } else {
//                            log.info("No firebase token found for user: {}", user.get().getUserId());
//                        }
//                    } else {
//                        log.info("No user found for ID: {}", userLocationTracker.getUserId());
//                    }
//                } catch (Exception exception) {
//                    log.error("Exception occured while sending notification for user: {}", userLocationTracker.getUserId());
//                }
//            }
//        }
//    }
}
