package org.jastka4.universityhelperserver.client.services.impl;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import org.jastka4.universityhelperserver.client.common.LoadType;
import org.jastka4.universityhelperserver.client.common.RequestType;
import org.jastka4.universityhelperserver.client.common.Transformation;
import org.jastka4.universityhelperserver.client.common.UnsupportedUniversityException;
import org.jastka4.universityhelperserver.client.config.TransformationConfiguration;
import org.jastka4.universityhelperserver.client.services.TransformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransformationServiceImpl implements TransformationService {
    private static final Logger LOG = LoggerFactory.getLogger(TransformationServiceImpl.class);
    private static final String UNIVERSITY = "university";

    @Resource
    private TransformationConfiguration transformationConfiguration;

    public String transformJson(final String jsonRequest, final Transformation transformation, final LoadType loadType, final String university) {
        final Map<String, String> inputJSON = (Map<String, String>) JsonUtils.jsonToObject(jsonRequest);

        final List<Object> chainrSpecJSON;
        if (LoadType.REQUEST.equals(loadType)) {
            chainrSpecJSON = JsonUtils.jsonToList(transformation.getRequest());
        } else {
            chainrSpecJSON = JsonUtils.jsonToList(transformation.getResponse());
        }
        final Chainr chainr = Chainr.fromSpec(chainrSpecJSON);

        final Object transformedOutput = chainr.transform(inputJSON);
        return JsonUtils.toJsonString(transformedOutput);
    }

    public String getUniversity(final String request) {
        final Map<String, String> json = (Map<String, String>) JsonUtils.jsonToObject(request);
        return json.get(UNIVERSITY);
    }

    public Optional<Transformation> getTransformationForUniversity(final String university, final RequestType requestType) throws UnsupportedUniversityException {
        final List<Transformation> transformations = getTransformationSpecJson(requestType);
        if (Objects.nonNull(transformations)) {
            return transformations.stream().filter(transformation -> university.equals(transformation.getName())).findFirst();
        } else {
            return Optional.empty();
        }
    }

    private List<Transformation> getTransformationSpecJson(final RequestType type) throws UnsupportedUniversityException {
        if (type.equals(RequestType.CALENDARS)) {
            return transformationConfiguration.getCalendars();
        } else if (type.equals(RequestType.FINANCES)) {
//            return transformationConfiguration.getFinances();
        } else if (type.equals(RequestType.GRADES)) {
//            return transformationConfiguration.getGrades();
        } else if (type.equals(RequestType.MESSAGES)) {
//            return transformationConfiguration.getMessages();
        } else if (type.equals(RequestType.PROFILES)) {
//            return transformationConfiguration.getProfiles();
        }

        throw new UnsupportedUniversityException("Incorrect university type: " + type);
    }
}
