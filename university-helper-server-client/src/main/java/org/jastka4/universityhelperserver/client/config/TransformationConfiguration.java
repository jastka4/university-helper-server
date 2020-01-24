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
    private List<Transformation> grades;

    public List<Transformation> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<Transformation> calendars) {
        this.calendars = calendars;
    }

    public List<Transformation> getGrades() {
        return grades;
    }

    public void setGrades(List<Transformation> grades) {
        this.grades = grades;
    }
}
