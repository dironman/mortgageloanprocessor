package com.opensource.api.loan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditReportResponse {
    private double ficoScore;
    
    @JsonProperty(value="isPaymentDefaulter")
    private boolean isPaymentDefaulter;
    public double getFicoScore() {
        return ficoScore;
    }
    public void setFicoScore(double ficoScore) {
        this.ficoScore = ficoScore;
    }
    public boolean isPaymentDefaulter() {
        return isPaymentDefaulter;
    }
    public void setPaymentDefaulter(boolean isPaymentDefaulter) {
        this.isPaymentDefaulter = isPaymentDefaulter;
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
