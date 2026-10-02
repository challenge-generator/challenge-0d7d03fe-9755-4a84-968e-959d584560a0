package com.financial.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.time.Duration;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
public class CommentsApplication {
    private final ApplicationProperties applicationProperties;

    public CommentsApplication(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        validateApplicationProperties();
    }

    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(applicationProperties.getCircuitBreaker().getFailureRateThreshold())
                .waitDurationInOpenState(Duration.ofMillis(applicationProperties.getCircuitBreaker().getWaitDurationInOpenState()))
                .slidingWindowSize(applicationProperties.getCircuitBreaker().getSlidingWindowSize())
                .build();
        return CircuitBreakerRegistry.of(circuitBreakerConfig);
    }

    private void validateApplicationProperties() {
        if (applicationProperties.getCircuitBreaker().getFailureRateThreshold() <= 0 ||
            applicationProperties.getCircuitBreaker().getWaitDurationInOpenState() <= 0 ||
            applicationProperties.getCircuitBreaker().getSlidingWindowSize() <= 0) {
            throw new IllegalStateException("Las propiedades de CircuitBreaker deben tener valores positivos");
        }
    }
}

record CircuitBreakerProperties(
        int failureRateThreshold,
        int waitDurationInOpenState,
        int slidingWindowSize
) {}

record ApplicationProperties(
        CircuitBreakerProperties circuitBreaker
) {}