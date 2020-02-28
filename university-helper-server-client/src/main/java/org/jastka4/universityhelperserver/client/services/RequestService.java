package org.jastka4.universityhelperserver.client.services;

import org.json.JSONObject;

public interface RequestService {
    JSONObject sendPostRequest(final String requestUrl, final String payload);
}
