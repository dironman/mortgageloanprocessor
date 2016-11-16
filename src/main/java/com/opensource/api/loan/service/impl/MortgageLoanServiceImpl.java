package com.opensource.api.loan.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

import com.opensource.api.loan.model.CreditReportResponse;
import com.opensource.api.loan.model.LoanRateResponse;
import com.opensource.api.loan.model.LoanRequest;
import com.opensource.api.loan.model.LoanResponse;
import com.opensource.api.loan.service.api.MortgageLoanService;
import com.opensource.api.loan.service.dao.CreditReportDao;
import com.opensource.api.loan.service.dao.LoanRateDao;

@Named
public class MortgageLoanServiceImpl implements MortgageLoanService{

    @Inject
    private LoanRateDao loanRateDao;
    
    @Inject
    private CreditReportDao creditReportDao;
    
    @Value("${eligibility.min.score}")
    private int minimumEligibilityScore;
    
    @Override
    public LoanResponse determineEligibility(LoanRequest loanRequest) {
        
        LoanResponse loanResponse = null;
        try
        {
            CreditReportResponse creditReportResponse = creditReportDao.getCreditReport(loanRequest);
            LoanRateResponse loanRateResponse = loanRateDao.getLoanRates(loanRequest, creditReportResponse);
            loanResponse = new LoanResponse();
            loanResponse.setEligible(loanRateResponse.isEligible());
            loanResponse.setFicoScore(creditReportResponse.getFicoScore());
            loanResponse.setProposedEmi(loanRateResponse.getProposedEmi());
            loanResponse.setProposedInterestRate(loanRateResponse.getProposedInterestRate());
        }
        catch(Exception e)
        {
            throw e;
        }
        return loanResponse;
    }

    @Override
    public LoanResponse getEligibility(LoanRequest request) {
        CreditReportResponse creditReportResponse = creditReportDao.fetchCreditReport(request);
        final LoanResponse loanResponse = new LoanResponse();
        if(creditReportResponse.getFicoScore() > minimumEligibilityScore)
            loanResponse.setEligible(true);
        else
            loanResponse.setEligible(false);
        loanResponse.setFicoScore(creditReportResponse.getFicoScore());
        return loanResponse;
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
