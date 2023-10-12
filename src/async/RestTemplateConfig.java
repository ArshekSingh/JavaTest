package async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private final RestTemplateConfigValue restTemplateConfigValue;

    public RestTemplateConfig(RestTemplateConfigValue restTemplateConfigValue) {
        this.restTemplateConfigValue = restTemplateConfigValue;
    }

    @Bean(name = "cbRestTemplate")
    public RestTemplate customRestTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(restTemplateConfigValue.getConnectionRequestTimeout());
        httpRequestFactory.setConnectTimeout(restTemplateConfigValue.getConnectTimeout());
        httpRequestFactory.setReadTimeout(restTemplateConfigValue.getReadTimeout());
        return new RestTemplate(httpRequestFactory);
    }
}