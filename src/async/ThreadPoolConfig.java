package async;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
@RequiredArgsConstructor
public class ThreadPoolConfig {

    public static final String CB_THREAD_POOL_NAME = "cbEnquiryThreadPool";

    private final AsyncConfig config;

    @Bean
    @Qualifier(CB_THREAD_POOL_NAME)
    public Executor cbEnquiryThreadPool() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(config.getCbPoolMinSize());
        taskExecutor.setMaxPoolSize(config.getCbPoolMaxSize());
        taskExecutor.setQueueCapacity(config.getQueueCapacity());
        taskExecutor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        taskExecutor.setThreadNamePrefix("cb-enquiry");
        taskExecutor.initialize();
        return taskExecutor;
    }
}