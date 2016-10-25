package com.opensource.api.loan.model;

import java.io.Serializable;

import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Component;

public class LoanRequest implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -3924152655277667322L;
    
    @QueryParam("firstName")
    private String firstName;
    
    @QueryParam("middleName")
    private String middleName;
    
    @QueryParam("lastName")
    private String lastName;
    
    @QueryParam("dateOfBirth")
    private String dateOfBirth;
    
    @QueryParam("annualIncome")
    private double annualIncome;
    
    @QueryParam("requestedLoanAmount")
    private double requestedLoanAmount;
    
    @QueryParam("requestedLoanTerm")
    private double requestedLoanTerm;
    
    @QueryParam("socialSecurityNumber")
    private long socialSecurityNumber;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public double getAnnualIncome() {
        return annualIncome;
    }
    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }
    public double getRequestedLoanAmount() {
        return requestedLoanAmount;
    }
    public void setRequestedLoanAmount(double requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }
    public double getRequestedLoanTerm() {
        return requestedLoanTerm;
    }
    public void setRequestedLoanTerm(double requestedLoanTerm) {
        this.requestedLoanTerm = requestedLoanTerm;
    }
    public long getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
    public void setSocialSecurityNumber(long socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
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
