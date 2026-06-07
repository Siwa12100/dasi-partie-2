package com.insa.nicojean.td2.web.controleur;

import com.insa.nicojean.td2.web.modele.Action;
import com.insa.nicojean.td2.web.modele.statistiques.ObtenirStatistiquesIntervenantAction;
import com.insa.nicojean.td2.web.vue.Serialisation;
import com.insa.nicojean.td2.web.vue.statistiques.StatistiquesIntervenantSerialisation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/statistiques")
public class StatistiquesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todo = request.getParameter("todo");
        Action action = null;
        Serialisation serialisation = null;

        switch (todo) {
            case "intervenant":
                action = new ObtenirStatistiquesIntervenantAction();
                serialisation = new StatistiquesIntervenantSerialisation();
                break;
        }

        action.execute(request);
        serialisation.appliquer(request, response);
    }
}
