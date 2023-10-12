package util;

import com.sts.finncub.core.components.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
public class SmsUtil {

    private final SmsProperties smsProperties;

    private final RestTemplate restTemplate;

    @Autowired
    public SmsUtil(SmsProperties smsProperties, RestTemplate restTemplate) {
        this.smsProperties = smsProperties;
        this.restTemplate = restTemplate;
    }

    public String sendSms(String mobileNumber, String message) {
        String strUrlPin = smsProperties.getSmsUrl();
        String strUserNamePin = smsProperties.getUsername();
        String strPasswordPin = smsProperties.getPass();
        String strsenderidPin = smsProperties.getSenderid();
        String msgtypePin = smsProperties.getMsgtype();
        String responsePin = smsProperties.getResponse();
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(strUrlPin).queryParam("username", strUserNamePin).queryParam("pass", strPasswordPin).queryParam("senderid", strsenderidPin).queryParam("dest_mobileno", mobileNumber).queryParam("msgtype", msgtypePin).queryParam("message", message).queryParam("response", responsePin);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            HttpEntity<String> smsResponse = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity, String.class);
            return smsResponse.getBody();
        } catch (Exception exception) {
            log.error("Exception occurs while sending SMS on mobile {} => {}", mobileNumber, exception.getMessage());
            return null;
        }
    }
    public String smsReport(String scheduleId){
        String strUrlPin = smsProperties.getSmsReportUrl();
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(strUrlPin).queryParam("Scheduleid", scheduleId);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            HttpEntity<String> smsResponse = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
            if (smsResponse.getBody() != null) {
                return smsResponse.getBody();
            }
        } catch (Exception exception) {
            log.error("Exception occurs while fetching Report on scheduleId {} => {}", scheduleId, exception.getMessage());
            return null;
        }
        return null;
    }

}
