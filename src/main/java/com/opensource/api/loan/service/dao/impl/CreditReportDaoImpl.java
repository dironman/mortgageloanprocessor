package com.opensource.api.loan.service.dao.impl;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.springframework.beans.factory.annotation.Value;

import com.opensource.api.loan.model.CreditReportResponse;
import com.opensource.api.loan.model.LoanRequest;
import com.opensource.api.loan.service.constants.MortgageLoanConstants;
import com.opensource.api.loan.service.dao.CreditReportDao;

@Named
public class CreditReportDaoImpl implements CreditReportDao{

    /**
     * 
     */
    private static final long serialVersionUID = 9173213914863454732L;
    
    ClientConfig clientConfig;
    Client client;
    
    @Value("${creditReport.serviceBaseUrl}")
    private String serviceBaseUrl;
    
    
    @PostConstruct
    public void init()
    {
        System.out.println("At post construct: Client construction");
        System.out.println("service url:" + serviceBaseUrl);
        clientConfig = new ClientConfig();
        client = ClientBuilder.newClient( 
                    new ClientConfig().register(LoggingFilter.class) 
                );
    }

    @Override
    public CreditReportResponse getCreditReport(LoanRequest loanRequest) {
        WebTarget target = client.target(serviceBaseUrl)
                .queryParam(MortgageLoanConstants.CREDITREPORT_QP_FIRSTNAME_NAME, loanRequest.getFirstName())
                .queryParam(MortgageLoanConstants.CREDITREPORT_QP_MIDDLENAME_NAME, loanRequest.getMiddleName())
                .queryParam(MortgageLoanConstants.CREDITREPORT_QP_LASTNAME_NAME, loanRequest.getLastName())
                .queryParam(MortgageLoanConstants.CREDITREPORT_QP_DATEOFBIRTH_NAME, loanRequest.getDateOfBirth())
                .queryParam(MortgageLoanConstants.CREDITREPORT_QP_SSN_NAME, loanRequest.getSocialSecurityNumber());
        CreditReportResponse response = target.request(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON).get(CreditReportResponse.class);
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
