package com.everc.automation.model;

import org.apache.commons.lang3.RandomStringUtils;

public class Customer {
    private String email;
    private String password;
    private String securityAnswer;
    static public String defaultEmail = generateRandomEmail();
    static public String defaultPassword = "Password123";
    static public String defaultSecurityAnswer = "test text";


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public Customer(){
        this.email = defaultEmail;
        this.password = defaultPassword;
        this.securityAnswer = defaultSecurityAnswer;
    }

    public Customer(String email, String password){
        this.email = email;
        this.password = password;
        this.securityAnswer = "";
    }

    public static String generateRandomEmail(){
        String randomEmail = RandomStringUtils.random(10,true,true) + "@test.com";
        return randomEmail;
    }
}
