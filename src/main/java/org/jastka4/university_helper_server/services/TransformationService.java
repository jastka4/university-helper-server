package org.jastka4.university_helper_server.services;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransformationService {

    private static final Logger LOG = LoggerFactory.getLogger(TransformationService.class);
    private static final String UNIVERSITY = "university";

    @Value("${resources.json.path}")
    private String jsonPath;

    public String transformJson(final String jsonRequest, final String type) {
        final Map<String, String> inputJSON = (Map<String, String>) JsonUtils.jsonToObject(jsonRequest);

        final List<Object> chainrSpecJSON = JsonUtils.classpathToList(getTransformationSpecPath(inputJSON.get(UNIVERSITY), type));
        final Chainr chainr = Chainr.fromSpec(chainrSpecJSON);

        final Object transformedOutput = chainr.transform(inputJSON);
        return JsonUtils.toJsonString(transformedOutput);
    }

    private String getTransformationSpecPath(final String university, final String type) {
        return jsonPath + university + "/" + type + ".json";
    }
}
