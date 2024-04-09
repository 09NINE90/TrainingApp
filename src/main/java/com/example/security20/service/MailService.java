package com.example.security20.service;

import com.example.security20.entity.MailType;
import com.example.security20.entity.User;

import java.util.Properties;
import java.util.prefs.Preferences;

public interface MailService {
    void sendMail(User user, MailType type, Properties properties);
}
