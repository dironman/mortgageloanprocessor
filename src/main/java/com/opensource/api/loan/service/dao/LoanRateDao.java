package com.opensource.api.loan.service.dao;

import com.opensource.api.loan.model.CreditReportResponse;
import com.opensource.api.loan.model.LoanRateResponse;
import com.opensource.api.loan.model.LoanRequest;

public interface LoanRateDao {

    public LoanRateResponse getLoanRates(LoanRequest loanRequest, CreditReportResponse creditReportResponse);
}


/*
 * Copyright 2016 Capital One Financial Corporation All Rights Reserved.
 * 
 * This software contains valuable trade secrets and proprietary information of
 * Capital One and is protected by law. It may not be copied or distributed in
 * any form or medium, disclosed to third parties, reverse engineered or used in
 * any manner without prior written authorization from Capital One.
 */
