package com.rolling.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.hibernate.stat.Statistics;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MetricsConfig {
    private final SessionFactory sessionFactory;

    @Bean
    public Statistics hibernateStatistics() {
        Statistics statistics = sessionFactory.getStatistics();
        statistics.setStatisticsEnabled(true);
        return statistics;
    }

}
