package com.insa.nicojean.td2.web.controleur;

import com.insa.nicojean.td2.web.modele.eleve.AuthentifierEleveAction;
import com.insa.nicojean.td2.web.modele.eleve.InscrireEleveAction;
import com.insa.nicojean.td2.web.modele.eleve.ListerElevesAction;
import com.insa.nicojean.td2.web.modele.eleve.RecupererEleveAction;
import com.insa.nicojean.td2.web.vue.*;
import com.insa.nicojean.td2.web.vue.eleve.EleveSerialisation;
import com.insa.nicojean.td2.web.vue.eleve.ListeElevesSerialisation;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/eleves"})
public class ElevesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String todo = req.getParameter("todo");
        switch (todo) {
            case "lister":
                new ListerElevesAction().execute(req);
                new ListeElevesSerialisation().appliquer(req, resp);
                break;
            case "recuperer":                        // ?todo=recuperer&id=42
                new RecupererEleveAction().execute(req);
                new EleveSerialisation().appliquer(req, resp);
                break;
            default:
                resp.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String todo = req.getParameter("todo");
        switch (todo) {
            case "inscrire":                         // body JSON
                new InscrireEleveAction().execute(req);
                new SuccesSerialisation().appliquer(req, resp);
                break;
            case "authentifier":                     // body JSON
                new AuthentifierEleveAction().execute(req);
                new EleveSerialisation().appliquer(req, resp);
                break;
            default:
                resp.sendError(404);
        }
    }
}
