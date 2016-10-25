package com.opensource.api.loan.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(Include.NON_NULL)
public class LoanResponse implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -6845425944766119851L;

    
    private boolean isEligible;
    private double ficoScore;
    private double proposedEmi;
    
    private double proposedInterestRate;

    
    public boolean isEligible() {
        return isEligible;
    }
    
    @JsonProperty(value="isEligible")
    public void setEligible(boolean isEligible) {
        this.isEligible = isEligible;
    }
    
    public double getFicoScore() {
        return ficoScore;
    }

    public void setFicoScore(double ficoScore) {
        this.ficoScore = ficoScore;
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
