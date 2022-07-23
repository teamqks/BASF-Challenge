package com.basf.challenge.configuration;

import com.basf.challenge.mappers.PatentMapper;
import com.basf.challenge.ner.NerProcess;
import com.basf.challenge.repositories.PatentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = PatentRepository.class)
public class ChallengeConfiguration {

    @Bean
    public PatentMapper getPatentMapper() {
        return new PatentMapper();
    }

    @Bean
    public NerProcess getNerProcess() {
        return new NerProcess();
    }

}
