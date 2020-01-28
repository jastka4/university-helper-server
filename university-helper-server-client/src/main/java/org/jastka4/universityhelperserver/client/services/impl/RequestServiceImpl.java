package org.jastka4.universityhelperserver.client.services.impl;

import org.jastka4.universityhelperserver.client.services.RequestService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class RequestServiceImpl implements RequestService {
    public JSONObject sendPostRequest(final String requestUrl, final String payload) {
        try {
            final URL url = new URL(requestUrl);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            writer.write(payload);
            writer.close();

            final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            return new JSONObject(jsonString.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
