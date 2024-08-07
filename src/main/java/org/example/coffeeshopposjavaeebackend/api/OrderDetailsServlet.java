package org.example.coffeeshopposjavaeebackend.api;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/orderDetails",loadOnStartup = 2)
public class OrderDetailsServlet extends HttpServlet {
}
