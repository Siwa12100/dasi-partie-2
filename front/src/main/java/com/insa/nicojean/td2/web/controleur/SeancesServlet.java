package com.insa.nicojean.td2.web.controleur;

import com.insa.nicojean.td2.web.modele.Action;
import com.insa.nicojean.td2.web.modele.seance.*;
import com.insa.nicojean.td2.web.vue.Serialisation;
import com.insa.nicojean.td2.web.vue.SuccesSerialisation;
import com.insa.nicojean.td2.web.vue.seance.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/seances")
public class SeancesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todo = request.getParameter("todo");
        Action action = null;
        Serialisation serialisation = null;

        switch (todo) {
            case "recuperer":
                action = new RecupererSeanceAction();
                serialisation = new SeanceSerialisation();
                break;
            case "recupererAttribuee":
                action = new RecupererSoutienAttribueAction();
                serialisation = new SeanceSerialisation();
                break;
            case "historiqueEleve":
                action = new ObtenirHistoriqueEleveAction();
                serialisation = new ListeSeancesSerialisation();
                break;
            case "historiqueIntervenant":
                action = new ObtenirHistoriqueIntervenantAction();
                serialisation = new ListeSeancesSerialisation();
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
            case "demander":
                action = new DemanderSoutienAction();
                serialisation = new SeanceSerialisation(); // retourne la séance créée
                break;
            case "accepter":
                action = new AccepterSoutienAction();
                break;
            case "refuser":
                action = new RefuserSoutienAction();
                break;
            case "terminer":
                action = new TerminerSoutienAction();
                break;
            case "bilan":
                action = new SoumettrebilanAction();
                break;
        }

        action.execute(request);
        serialisation.appliquer(request, response);
    }
}
