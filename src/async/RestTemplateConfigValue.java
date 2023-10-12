package async;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
public class RestTemplateConfigValue {

    @Value("${cb.connection-request-timeout}")
    private Integer connectionRequestTimeout;

    @Value("${cb.connect-timeout}")
    private Integer connectTimeout;

    @Value("${cb.read-timeout}")
    private Integer readTimeout;
}