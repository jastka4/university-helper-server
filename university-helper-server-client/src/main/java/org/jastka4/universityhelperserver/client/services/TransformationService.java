package org.jastka4.universityhelperserver.client.services;

import org.jastka4.universityhelperserver.client.common.LoadType;
import org.jastka4.universityhelperserver.client.common.RequestType;
import org.jastka4.universityhelperserver.client.common.Transformation;
import org.jastka4.universityhelperserver.client.common.UnsupportedUniversityException;

import java.util.Optional;

public interface TransformationService {
    String transformJson(final String jsonRequest, final Transformation transformation, final LoadType loadType, final String university);

    String getUniversity(final String request);

    Optional<Transformation> getTransformationForUniversity(final String university, final RequestType requestType) throws UnsupportedUniversityException;
}
