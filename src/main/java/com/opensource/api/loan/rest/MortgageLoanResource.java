package com.opensource.api.loan.rest;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.opensource.api.loan.model.LoanRequest;
import com.opensource.api.loan.model.LoanResponse;
import com.opensource.api.loan.service.api.MortgageLoanService;

@Component
@Path("eligibility")
public class MortgageLoanResource {

    @Inject
    private MortgageLoanService service;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public LoanResponse getEligibilityDetails(
            @BeanParam LoanRequest loanRequest)
    {
//        LoanResponse loanResponse = service.determineEligibility(loanRequest);
        LoanResponse loanResponse = service.getEligibility(loanRequest);
        return loanResponse;
        
    }
    
    @Path("health")
    @GET
    public Response health()
    {
        return Response.ok().build();
    }
}


/*
 * Copyright 2016 Capital One Financial Corporation All Rights Reserved.
 * 
 * This software contains valuable trade secrets and proprietary information of
 * Capital One and is protected by law. It may not be copied or distributed in
 * any form or medium, disclosed to third parties, reverse engineered or used in
 * any manner without prior written authorization from Capital One.
 */
