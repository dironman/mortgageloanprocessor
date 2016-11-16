package com.opensource.api.loan.service.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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
    public static final String CREDIT_REPORT_QUERY = "select * from public.CreditReportInfo where first_name = :first_name";
    
    ClientConfig clientConfig;
    Client client;
    
    @Value("${creditReport.serviceBaseUrl}")
    private String serviceBaseUrl;
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private DataSource dataSource;
    
    public DataSource getDataSource() {
        return dataSource;
    }
    
    @Inject
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

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

    @Override
    public CreditReportResponse fetchCreditReport(LoanRequest loanRequest) {
        Map<String, Object> dbParameters = new HashMap<String, Object>();
        dbParameters.put(MortgageLoanConstants.CREDITREPORT_DB_COL_FIRSTNAME, loanRequest.getFirstName());
        java.util.List<CreditReportResponse> creditReports =  namedParameterJdbcTemplate.query(CREDIT_REPORT_QUERY, dbParameters, new CreditReportMapper());
        if(creditReports.size() > 0)
            return creditReports.get(0);
        else 
            return null;
    }
    
    public class CreditReportMapper implements RowMapper<CreditReportResponse> {
        @Override
        public CreditReportResponse mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
            final CreditReportResponse creditReportResponse = new CreditReportResponse();
            creditReportResponse.setFicoScore(resultSet.getInt(MortgageLoanConstants.CREDITREPORT_DB_COL_FICOSCORE));
            creditReportResponse.setPaymentDefaulter(resultSet.getBoolean(MortgageLoanConstants.CREDITREPORT_DB_COL_ISPAYMENTDEFAULTER));
            return creditReportResponse;
        }
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
