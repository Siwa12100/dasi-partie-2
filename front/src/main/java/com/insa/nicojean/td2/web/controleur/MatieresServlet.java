package com.insa.nicojean.td2.web.controleur;

import com.insa.nicojean.td2.web.modele.Action;
import com.insa.nicojean.td2.web.modele.matiere.*;
import com.insa.nicojean.td2.web.vue.Serialisation;
import com.insa.nicojean.td2.web.vue.matiere.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/matieres")
public class MatieresServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todo = request.getParameter("todo");
        Action action = null;
        Serialisation serialisation = null;

        switch (todo) {
            case "listerMatieres":
                action = new ListerMatieresAction();
                serialisation = new ListeMatieresSerialisation();
                break;
            case "recupererMatiere":
                action = new RecupererMatiereAction();
                serialisation = new MatiereSerialisation();
                break;
            case "listerThemes":
                action = new ListerThemesAction();
                serialisation = new ListeThemesSerialisation();
                break;
            case "recupererTheme":
                action = new RecupererThemeAction();
                serialisation = new ThemeSerialisation();
                break;
            case "recupererThemesParMatiere":
                action = new RecupererThemesParMatiereAction();
                serialisation = new ListeThemesSerialisation();
                break;
        }

        action.execute(request);
        serialisation.appliquer(request, response);
    }
}
