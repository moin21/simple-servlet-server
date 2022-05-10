package com.bridgelabz;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class Handlers {
    public static class RootHandler implements HttpHandler {
        @Override
        public void handler(HttpExchange exchange) throws IOException {
String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: " + SimpleHttpServer.port + "</h1>";
exchange.sendResponseHeaders(200, response.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }
    }
}
