package org.jastka4.universityhelperserver.client.common;

public enum LoadType {
    REQUEST("request"), RESPONSE("response");

    private final String name;

    LoadType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
