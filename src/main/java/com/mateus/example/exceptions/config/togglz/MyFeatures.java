package com.mateus.example.exceptions.config.togglz;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum MyFeatures implements Feature {
    @EnabledByDefault
    @Label("Inserir Pessoas")
    INSERT_PERSON;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}


