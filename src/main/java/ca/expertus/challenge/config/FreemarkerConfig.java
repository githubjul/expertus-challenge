package ca.expertus.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FreemarkerConfig extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration {


    @Value("${expertus.analytics.googleAnalytics}")
    private String googleAnalyticsId;

    @Override
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = super.freeMarkerConfigurer();

        Map<String, Object> sharedVariables = new HashMap<>();
        sharedVariables.put("googleAnalyticsId", googleAnalyticsId);
        configurer.setFreemarkerVariables(sharedVariables);
        return configurer;
    }
}
