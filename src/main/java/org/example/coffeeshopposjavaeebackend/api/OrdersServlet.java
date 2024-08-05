package org.example.coffeeshopposjavaeebackend.api;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/orders",loadOnStartup = 2)
public class OrdersServlet extends HttpServlet {
}
