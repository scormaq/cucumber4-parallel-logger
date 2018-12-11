package com.github.scormaq.cucumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;

public class Definitions {

    private static final Logger LOGGER = LoggerFactory.getLogger(Definitions.class);

    @Given("I report next in log: {string}")
    public void givenFoo(String str) {
        LOGGER.warn("{}", str);
    }
}
