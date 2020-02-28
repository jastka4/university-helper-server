package org.jastka4.universityhelperserver.client.services;

import org.jastka4.universityhelperserver.client.common.LoadType;
import org.jastka4.universityhelperserver.client.common.RequestType;
import org.jastka4.universityhelperserver.client.common.Transformation;
import org.jastka4.universityhelperserver.client.common.UnsupportedUniversityException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Optional;

public interface TransformationService {
    String transformJson(final JSONObject jsonRequest, final Transformation transformation, final LoadType loadType, final String university);

    String getUniversity(final JSONObject request);

    String putUniversity(final Map<String, String> request, final String university);

    Optional<Transformation> getTransformationForUniversity(final String university, final RequestType requestType) throws UnsupportedUniversityException;
}
