package com.example.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

public class SimpleStatusServlet {
    
    public void doGet(Object request, Object response) throws IOException {
        // Mock response object handling
        if (response instanceof MockHttpServletResponse) {
            MockHttpServletResponse mockResp = (MockHttpServletResponse) response;
            mockResp.setContentType("text/plain");
            PrintWriter writer = mockResp.getWriter();
            writer.write("Credit Card Reward Maximizer is running");
            writer.flush();
        }
    }
    
    // Mock response class for testing
    public static class MockHttpServletResponse {
        private String contentType;
        private PrintWriter writer;
        
        public void setContentType(String contentType) {
            this.contentType = contentType;
        }
        
        public PrintWriter getWriter() {
            return writer;
        }
        
        public void setWriter(PrintWriter writer) {
            this.writer = writer;
        }
    }
}