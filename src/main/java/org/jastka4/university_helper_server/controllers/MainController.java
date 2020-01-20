package org.jastka4.university_helper_server.controllers;

import org.jastka4.university_helper_server.services.TransformationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class MainController {

    private static final String CALENDAR = "calendar";

    @Resource
    private TransformationService transformationService;

    @PostMapping(value = "/calendar")
    public String index(@RequestBody final String calendarJson) {
        return transformationService.transformJson(calendarJson, CALENDAR);
    }
}
