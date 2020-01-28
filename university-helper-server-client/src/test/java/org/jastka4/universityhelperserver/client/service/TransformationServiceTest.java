package org.jastka4.universityhelperserver.client.service;

import org.jastka4.universityhelperserver.client.common.UnsupportedUniversityException;
import org.jastka4.universityhelperserver.client.services.TransformationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransformationServiceTest {
    @Autowired
    TransformationService transformationService;

    private static String calendarJsonInput;
    private static String calendarJsonResult;

    @BeforeAll
    static void initAll() {
        calendarJsonInput = "{\"university\": \"pwr\",\"studentNumber\": \"123456\",\"startDate\": \"2020-01-01\",\"endDate\": \"2020-01-31\"}";
        calendarJsonResult = "{\"index\":\"123456\",\"date\":{\"start\":\"2020-01-01\",\"end\":\"2020-01-31\"}}";
    }

    @DisplayName("Test calendar JSON transformation")
    @Test
    void transformCalendarJson() throws UnsupportedUniversityException {
//        final String output = transformationService.transformJson(calendarJsonInput, RequestType.CALENDARS);
//        assertEquals(calendarJsonResult, output);
    }
}