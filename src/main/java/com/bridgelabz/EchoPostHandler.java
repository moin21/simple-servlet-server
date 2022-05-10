package com.bridgelabz;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class EchoPostHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, Object> parameters = new HashMap<>();
        InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String response = "";
        for (String key: parameters.keySet())
            response += key + " = " + parameters.get(key) + "\n";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {
        if (query != null) {
            String pairs[] = query.split("[&]");
            for (String pair : pairs) {
                String param[] = pair.split("[=]");
                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
                }
                if (param.length > 0) {
                    value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
                }
                parameters.put(key, value);
            }
        }
    }
}

