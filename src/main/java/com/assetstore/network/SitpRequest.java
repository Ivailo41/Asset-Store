package com.assetstore.network;

import com.assetstore.network.publisher.BodyPublisher;

import java.util.HashMap;
import java.util.Map;

public record SitpRequest(String method, String url, Map<String, String> headers, BodyPublisher body) {

    public static class Builder {
        private String method = "GET";
        private String url;
        private final Map<String, String> headers;
        private BodyPublisher body;

        public Builder() {
            this.headers = new HashMap<>();
        }

        public Builder url(String uri) {
            this.url = uri;
            return this;
        }

        public Builder header(String key, String value) {
            headers.put(key, value);
            return this;
        }

        public Builder POST(BodyPublisher body) {
            this.method = "POST";
            this.body = body;
            return this;
        }

        public SitpRequest build() {
            return new SitpRequest(method, url, headers, body);
        }
    }
}
