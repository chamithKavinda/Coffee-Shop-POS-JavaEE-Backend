package org.example.coffeeshopposjavaeebackend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CORSFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("CORS Filter executed");

        // Set CORS headers
        res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5501"); // Allow your frontend origin
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS"); // Allowed HTTP methods
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Allowed request headers
        res.setHeader("Access-Control-Expose-Headers", "Content-Type, Authorization"); // Exposed response headers
        res.setHeader("Access-Control-Allow-Credentials", "true"); // Allow cookies and credentials

        // Handle preflight (OPTIONS) requests
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK); // Respond OK to preflight requests
            return;
        }

        // Continue with the filter chain
        chain.doFilter(req, res);
    }
}
