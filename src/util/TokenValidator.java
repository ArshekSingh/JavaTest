package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sts.finncub.core.entity.UserSession;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;

@Slf4j
@Service
public class TokenValidator {
    private static final String KEY = "USER_SESSION";
    @Autowired
    private RedisTemplate<String, Object> template;

    public UserSession validateTokenAndReturnUserSession(final String id, String userAgent) throws JsonProcessingException {
        Object result = template.opsForHash().entries(KEY + ":" + id);
        if (((LinkedHashMap) result).size() == 0) {
            return new UserSession();
        }
        if (userAgent.equalsIgnoreCase("FINNCUB_MOBILE")) {
            LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 50));
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            template.expireAt(KEY + ":" + id, date);
        } else {
            LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 50));
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            template.expireAt(KEY + ":" + id, date);
        }
        return convertObjectIntoUserSession(result);
    }

    public UserSession convertObjectIntoUserSession(Object object) throws JsonProcessingException {
        JSONObject jsonObject = JSONUtils.toJSON(object);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        UserSession userSession = mapper.readValue(jsonObject.toString(), UserSession.class);
        UserSession userSessionResponse = mapper.readValue(userSession.getUserSessionJSON(), UserSession.class);
        return userSessionResponse;
    }
}