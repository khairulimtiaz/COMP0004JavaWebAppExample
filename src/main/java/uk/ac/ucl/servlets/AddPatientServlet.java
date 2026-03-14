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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/addPatient")
public class AddPatientServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // Display the add patient form
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/addPatient.jsp");
        dispatch.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        try
        {
            // Validate required fields
            String first = request.getParameter("FIRST");
            String last = request.getParameter("LAST");
            String birthdate = request.getParameter("BIRTHDATE");
            String gender = request.getParameter("GENDER");

            if (first == null || first.trim().isEmpty()
                    || last == null || last.trim().isEmpty()
                    || birthdate == null || birthdate.trim().isEmpty()
                    || gender == null || gender.trim().isEmpty())
            {
                request.setAttribute("errorMessage", "First name, last name, birthdate and gender are required.");
                getServletContext().getRequestDispatcher("/addPatient.jsp").forward(request, response);
                return;
            }

            // Collect all form fields and pass to Model
            String[] fields = {"BIRTHDATE", "DEATHDATE", "SSN", "DRIVERS", "PASSPORT",
                    "PREFIX", "FIRST", "LAST", "SUFFIX", "MAIDEN", "MARITAL",
                    "RACE", "ETHNICITY", "GENDER", "BIRTHPLACE", "ADDRESS",
                    "CITY", "STATE", "ZIP"};

            Map<String,String> patientData = new HashMap<>();
            for (String field : fields)
            {
                String value = request.getParameter(field);
                patientData.put(field, value != null ? value.trim() : "");
            }

            Model model = ModelFactory.getModel();
            model.addPatient(patientData);
            response.sendRedirect("/patientList");
        }
        catch (Exception e)
        {
            request.setAttribute("errorMessage", "Error adding patient: " + e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}