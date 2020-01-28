package org.jastka4.universityhelperserver.client.controllers;

import org.jastka4.universityhelperserver.client.common.LoadType;
import org.jastka4.universityhelperserver.client.common.RequestType;
import org.jastka4.universityhelperserver.client.common.Transformation;
import org.jastka4.universityhelperserver.client.common.UnsupportedUniversityException;
import org.jastka4.universityhelperserver.client.services.RequestService;
import org.jastka4.universityhelperserver.client.services.TransformationService;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MainController {

    @Resource
    private TransformationService transformationService;

    @Resource
    private RequestService requestService;

    @PostMapping(value = "/calendar")
    public String getCalendarEvents(@RequestBody final String request) {
        try {
            return sendRequestToUniversityApi(new JSONObject(request), RequestType.CALENDARS);
        } catch (UnsupportedUniversityException e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/grades")
    public String getGrades(@RequestBody final String request) {
        try {
            return sendRequestToUniversityApi(new JSONObject(request), RequestType.GRADES);
        } catch (UnsupportedUniversityException e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/messages")
    public String getMassages(@RequestBody final String request) {
        try {
            return sendRequestToUniversityApi(new JSONObject(request), RequestType.MESSAGES);
        } catch (UnsupportedUniversityException e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPayments(@RequestBody final String request) {
        try {
            return sendRequestToUniversityApi(new JSONObject(request), RequestType.FINANCES);
        } catch (UnsupportedUniversityException e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginUser(@RequestBody final String request) {
        try {
            return sendRequestToUniversityApi(new JSONObject(request), RequestType.LOGIN);
        } catch (UnsupportedUniversityException e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUser(@RequestBody final String request) {
        try {
            return sendRequestToUniversityApi(new JSONObject(request), RequestType.PROFILES);
        } catch (UnsupportedUniversityException e) {
            return e.getMessage();
        }
    }

    private String sendRequestToUniversityApi(final JSONObject request, final RequestType requestType) throws UnsupportedUniversityException {
        final String university = transformationService.getUniversity(request);
        final Optional<Transformation> transformation = transformationService.getTransformationForUniversity(university, requestType);
        if (transformation.isPresent()) {
            final String transformedRequest = transformationService.transformJson(request, transformation.get(), LoadType.REQUEST, university);
            final JSONObject response = requestService.sendPostRequest(transformation.get().getAddress(), transformedRequest);
            return transformationService.transformJson(response, transformation.get(), LoadType.RESPONSE, university);
        }

        return ""; // TODO - return error response
    }
}
