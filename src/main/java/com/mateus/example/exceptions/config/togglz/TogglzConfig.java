package com.mateus.example.exceptions.config.togglz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.user.UserProvider;

@Configuration
public class TogglzConfig {
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private UserProvider userProvider;

    @Bean
    public FeatureManager featureManagerBuilder() {

        FeatureManagerBuilder featureManagerBuilder =  new FeatureManagerBuilder().
                featureEnum(MyFeatures.class).
                stateRepository(stateRepository).
                userProvider(userProvider);

        return featureManagerBuilder.build();
    }
}
