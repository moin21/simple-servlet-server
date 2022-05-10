package com.bridgelabz;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static com.bridgelabz.EchoPostHandler.parseQuery;

public class EchoGetHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, Object> parameters = new HashMap<>();
        URI requestedUri = exchange.getRequestURI();
        String query = requestedUri.getRawQuery();
        parseQuery(query, parameters);
        String response = "";
        for (String key : parameters.keySet())
            response += key + " = " + parameters.get(key) + "\n";
        exchange.sendResponseHeaders(20, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
