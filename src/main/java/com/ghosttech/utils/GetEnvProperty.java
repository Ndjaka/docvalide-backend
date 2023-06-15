package com.ghosttech.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class GetEnvProperty {

    private final Environment env;

    @Autowired
    public GetEnvProperty(Environment env) {
        this.env = env;
    }

    public String getPropertyValue(String key) {
        return env.getProperty(key);
    }
}