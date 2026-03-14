package uk.ac.ucl.servlets;

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

@WebServlet("/editPatient")
public class EditPatientServlet extends HttpServlet
{
    // Show the edit form pre-filled with current values
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        try
        {
            String id = request.getParameter("id");
            Model model = ModelFactory.getModel();
            Map<String,String> patient = model.getPatientById(id);
            request.setAttribute("patient", patient);

            getServletContext().getRequestDispatcher("/editPatient.jsp").forward(request, response);
        }
        catch (Exception e)
        {
            request.setAttribute("errorMessage", "Error loading patient: " + e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // Process the submitted edit form
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        try
        {
            String id = request.getParameter("ID");

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
                // Reload patient data so the form stays filled
                Model model = ModelFactory.getModel();
                request.setAttribute("patient", model.getPatientById(id));
                getServletContext().getRequestDispatcher("/editPatient.jsp").forward(request, response);
                return;
            }

            String[] fields = {"BIRTHDATE", "DEATHDATE", "SSN", "DRIVERS", "PASSPORT",
                    "PREFIX", "FIRST", "LAST", "SUFFIX", "MAIDEN", "MARITAL",
                    "RACE", "ETHNICITY", "GENDER", "BIRTHPLACE", "ADDRESS",
                    "CITY", "STATE", "ZIP"};

            Map<String,String> updatedData = new HashMap<>();
            for (String field : fields)
            {
                String value = request.getParameter(field);
                updatedData.put(field, value != null ? value.trim() : "");
            }

            Model model = ModelFactory.getModel();
            model.editPatient(id, updatedData);
            response.sendRedirect("/patientDetail?id=" + id);
        }
        catch (Exception e)
        {
            request.setAttribute("errorMessage", "Error editing patient: " + e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}