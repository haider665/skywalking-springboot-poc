package io.github.demo.demoservice.config;

import io.micrometer.observation.ObservationRegistry;
import org.apache.skywalking.apm.meter.micrometer.SkywalkingConfig;
import org.apache.skywalking.apm.meter.micrometer.SkywalkingMeterRegistry;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

/**
 * @author mojib.haider
 * @since 5/8/24
 */
@Configuration
public class Config {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(ObservationRegistry observationRegistry) {
        return WebClient.builder().observationRegistry(observationRegistry);
    }

    @Bean
    public SkywalkingMeterRegistry skywalkingMeterRegistry() {
        SkywalkingConfig config = new SkywalkingConfig(Arrays.asList("test_rate_counter"));
        return new SkywalkingMeterRegistry(config);
    }
}
