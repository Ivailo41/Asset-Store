package com.assetstore.network;

import com.assetstore.network.handler.BodyHandler;

import java.util.Map;

public record Response<T>(String status, String message, Map<String, String> headers, BodyHandler<T> body) {
}
