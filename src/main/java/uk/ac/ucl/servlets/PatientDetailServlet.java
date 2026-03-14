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
import java.util.Map;

@WebServlet("/patientDetail")
public class PatientDetailServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String id = request.getParameter("id");
            Model model = ModelFactory.getModel();
            Map<String,String> patient = model.getPatientById(id);
            request.setAttribute("patient", patient);
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/patientDetail.jsp");
            dispatch.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error loading patient: " + e.getMessage());
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
            dispatch.forward(request, response);
        }
    }
}