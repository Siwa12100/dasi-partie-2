package com.insa.nicojean.td2.web.modele.statistiques;

import com.insa.nicojean.td2.web.controleur.ActionServlet;
import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Intervenant;
import instructif.metier.modele.StatistiquesIntervenant;
import instructif.metier.service.ServiceIntervenant;
import instructif.metier.service.ServiceStatistiques;
import jakarta.servlet.http.HttpServletRequest;

public class ObtenirStatistiquesIntervenantAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long idIntervenant = (Long) request.getAttribute(ActionServlet.REQUEST_INTERVENANT_ID);
        Intervenant intervenant = new ServiceIntervenant().recupererIntervenant(idIntervenant);
        ServiceStatistiques service = new ServiceStatistiques();
        StatistiquesIntervenant stats = service.obtenirStatistiquesIntervenant(intervenant);
        request.setAttribute("stats", stats);
    }
}
