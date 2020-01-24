package org.jastka4.universityhelperserver.client.services;

public interface RequestService {
    String sendPostRequest(final String requestUrl, final String payload);
}
