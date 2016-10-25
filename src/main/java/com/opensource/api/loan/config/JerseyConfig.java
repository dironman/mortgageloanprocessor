package com.opensource.api.loan.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.opensource.api.loan.model.LoanRequest;
import com.opensource.api.loan.rest.MortgageLoanResource;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(MortgageLoanResource.class).register(LoanRequest.class);
    }

}

