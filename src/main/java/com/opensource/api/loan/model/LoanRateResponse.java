package com.opensource.api.loan.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
public class LoanRateResponse implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 5074292538348260386L;
    
    @JsonProperty(value="isEligible")
    private boolean isEligible;
    private double proposedEmi;
    private double proposedInterestRate;
    public boolean isEligible() {
        return isEligible;
    }
    public void setEligible(boolean isEligible) {
        this.isEligible = isEligible;
    }
    public double getProposedEmi() {
        return proposedEmi;
    }
    public void setProposedEmi(double proposedEmi) {
        this.proposedEmi = proposedEmi;
    }
    public double getProposedInterestRate() {
        return proposedInterestRate;
    }
    public void setProposedInterestRate(double proposedInterestRate) {
        this.proposedInterestRate = proposedInterestRate;
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
