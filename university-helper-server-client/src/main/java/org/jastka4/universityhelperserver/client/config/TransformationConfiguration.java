package org.jastka4.universityhelperserver.client.config;

import org.jastka4.universityhelperserver.client.common.Transformation;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "config")
public class TransformationConfiguration {

    private List<Transformation> calendars;
    private List<Transformation> finances;
    private List<Transformation> grades;
    private List<Transformation> messages;
    private List<Transformation> payments;
    private List<Transformation> profiles;
    private List<Transformation> login;

    public List<Transformation> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<Transformation> calendars) {
        this.calendars = calendars;
    }

    public List<Transformation> getFinances() {
        return finances;
    }

    public void setFinances(List<Transformation> finances) {
        this.finances = finances;
    }

    public List<Transformation> getGrades() {
        return grades;
    }

    public void setGrades(List<Transformation> grades) {
        this.grades = grades;
    }

    public List<Transformation> getMessages() {
        return messages;
    }

    public void setMessages(List<Transformation> messages) {
        this.messages = messages;
    }

    public List<Transformation> getPayments() {
        return payments;
    }

    public void setPayments(List<Transformation> payments) {
        this.payments = payments;
    }

    public List<Transformation> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Transformation> profiles) {
        this.profiles = profiles;
    }

    public List<Transformation> getLogin() {
        return login;
    }

    public void setLogin(List<Transformation> login) {
        this.login = login;
    }
}
