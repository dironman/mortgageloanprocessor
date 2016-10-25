package com.opensource.api.loan.service.dao.impl;

import java.text.DecimalFormat;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.filter.LoggingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.expression.MapAccessor;

import com.opensource.api.loan.model.CreditReportResponse;
import com.opensource.api.loan.model.LoanRateResponse;
import com.opensource.api.loan.model.LoanRequest;
import com.opensource.api.loan.service.constants.MortgageLoanConstants;
import com.opensource.api.loan.service.dao.LoanRateDao;

@Named
public class LoanRateDaoImpl implements LoanRateDao{

    ClientConfig clientConfig;
    Client client;
    
    @Value("${loanRate.serviceBaseUrl}")
    private String serviceBaseUrl;
    
    
    @PostConstruct
    public void init()
    {
        System.out.println("At post construct: Client construction");
        System.out.println(serviceBaseUrl);
        clientConfig = new ClientConfig();
        client = ClientBuilder.newClient( 
                    new ClientConfig().register(LoggingFilter.class) 
                );
    }
    
    @Override
    public LoanRateResponse getLoanRates(LoanRequest loanRequest, CreditReportResponse creditReportResponse) {
        WebTarget target = client.target(serviceBaseUrl)
                .queryParam(MortgageLoanConstants.LOANRATE_QP_FICOSCORE_NAME, new DecimalFormat("0.#").format(creditReportResponse.getFicoScore()))
                .queryParam(MortgageLoanConstants.LOANRATE_QP_ANNUALINCOME_NAME, new DecimalFormat("0.#").format(loanRequest.getAnnualIncome()))
                .queryParam(MortgageLoanConstants.LOANRATE_QP_REQUESTEDLOANAMOUNT_NAME, new DecimalFormat("0.#").format(loanRequest.getRequestedLoanAmount()))
                .queryParam(MortgageLoanConstants.LOANRATE_QP_REQUESTEDLOANTERM_NAME, new DecimalFormat("0.#").format(loanRequest.getRequestedLoanTerm()));
        LoanRateResponse response = target.request(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON).get(LoanRateResponse.class);
        return response;
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
