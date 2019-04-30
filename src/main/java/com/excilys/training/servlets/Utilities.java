package com.excilys.training.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Utilities {

    /**
     *
     * @param request
     * @param response
     * @param filename
     */
    public static void forwardScreen(HttpServletRequest request, HttpServletResponse response, String vue) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            RequestDispatcher dispatcher = request.getRequestDispatcher(vue);
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            dispatcher.include(request, response);
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
