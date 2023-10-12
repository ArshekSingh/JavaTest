package async;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
public class AsyncConfig {

    @Value("${cb.pool-max-size}")
    private Integer cbPoolMaxSize;

    @Value("${cb.pool-min-size}")
    private Integer cbPoolMinSize;

    @Value("${cb.queue-capacity}")
    private Integer queueCapacity;

    @Value("${cb.keep-alive-seconds}")
    private Integer keepAliveSeconds;
}