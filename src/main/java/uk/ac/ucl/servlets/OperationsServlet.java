package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import java.io.IOException;

@WebServlet("/operations")
public class OperationsServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        try
        {
            Model model = ModelFactory.getModel();
            request.setAttribute("oldestPatient", model.getOldestAlivePatient());
            request.setAttribute("aliveDeceased", model.getAliveDeceasedCount());
            request.setAttribute("cityCount", model.getCountByCity());

            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/operations.jsp");
            dispatch.forward(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("errorMessage", "Error loading operations: " + e.getMessage());
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
            dispatch.forward(request, response);
        }
    }
}