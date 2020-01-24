package org.jastka4.universityhelperserver.client.controllers;

import org.jastka4.universityhelperserver.client.common.LoadType;
import org.jastka4.universityhelperserver.client.common.RequestType;
import org.jastka4.universityhelperserver.client.common.Transformation;
import org.jastka4.universityhelperserver.client.common.UnsupportedUniversityException;
import org.jastka4.universityhelperserver.client.services.RequestService;
import org.jastka4.universityhelperserver.client.services.TransformationService;
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
            return sendRequestToUniversityApi(request, RequestType.CALENDARS);
        } catch (UnsupportedUniversityException e) {
            return e.getMessage();
        }
    }

    private String sendRequestToUniversityApi(final String request, final RequestType requestType) throws UnsupportedUniversityException {
        final String university = transformationService.getUniversity(request);
        final Optional<Transformation> transformation = transformationService.getTransformationForUniversity(university, requestType);
        if (transformation.isPresent()) {
            final String transformedRequest = transformationService.transformJson(request, transformation.get(), LoadType.REQUEST, university);
            final String response = requestService.sendPostRequest(transformation.get().getAddress(), transformedRequest);
            return transformationService.transformJson(response, transformation.get(), LoadType.RESPONSE, university);
        }

        return "";
    }
}
