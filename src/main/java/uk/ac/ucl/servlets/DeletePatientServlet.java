package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import java.io.IOException;

@WebServlet("/deletePatient")
public class DeletePatientServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        try
        {
            String id = request.getParameter("id");
            Model model = ModelFactory.getModel();
            model.deletePatient(id);
            response.sendRedirect("/patientList");
        }
        catch (Exception e)
        {
            request.setAttribute("errorMessage", "Error deleting patient: " + e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}