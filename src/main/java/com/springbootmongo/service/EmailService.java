package com.springbootmongo.service;

public interface EmailService {
    public void sendEmail(String to, String subject, String body);
}
