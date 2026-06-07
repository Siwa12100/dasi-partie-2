package com.insa.nicojean.td2.web.controleur;

import com.insa.nicojean.td2.web.modele.Action;
import com.insa.nicojean.td2.web.modele.intervenant.*;
import com.insa.nicojean.td2.web.vue.Serialisation;
import com.insa.nicojean.td2.web.vue.SuccesSerialisation;
import com.insa.nicojean.td2.web.vue.intervenant.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/intervenants")
public class IntervenantsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todo = request.getParameter("todo");
        Action action = null;
        Serialisation serialisation = null;

        switch (todo) {
            case "lister":
                action = new ListerIntervenantsAction();
                serialisation = new ListeIntervenantsSerialisation();
                break;
            case "recuperer":
                action = new RecupererIntervenantAction();
                serialisation = new IntervenantSerialisation();
                break;
        }

        action.execute(request);
        serialisation.appliquer(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todo = request.getParameter("todo");
        Action action = null;
        Serialisation serialisation = new SuccesSerialisation();

        switch (todo) {
            case "authentifier":
                action = new AuthentifierIntervenantAction();
                break;
        }

        action.execute(request);
        serialisation.appliquer(request, response);
    }
}
