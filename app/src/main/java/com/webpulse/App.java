package com.webpulse;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String html = "<html><title>Maven App</title><body style='background:#f4f4f9; text-align:center; padding-top:50px; font-family:Arial;'>" +
                              "<h1 style='color:#2c3e50;'>Project Web-Pulse is Live!</h1>" +
                              "<p style='color:#16a085; font-size:18px;'>Successfully deployed using <b>Git, Maven, Terraform, and Ansible</b>.</p>" +
                              "</body></html>";
                exchange.sendResponseHeaders(200, html.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(html.getBytes());
                os.close();
            }
        });
        server.setExecutor(null);
        server.start();
        System.out.println("Web server started on port " + port);
    }
}
