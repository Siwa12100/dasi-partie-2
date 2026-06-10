package com.insa.nicojean.td2.web.modele.seance;

import com.insa.nicojean.td2.web.controleur.ActionServlet;
import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Intervenant;
import instructif.metier.modele.SeanceSoutien;
import instructif.metier.service.ServiceIntervenant;
import instructif.metier.service.ServiceSeanceSoutien;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class ObtenirHistoriqueIntervenantAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long idIntervenant = (Long) request.getAttribute(ActionServlet.REQUEST_INTERVENANT_ID);
        Intervenant intervenant = new ServiceIntervenant().recupererIntervenant(idIntervenant);
        ServiceSeanceSoutien service = new ServiceSeanceSoutien();
        List<SeanceSoutien> seances = service.obtenirHistoriqueIntervenant(intervenant);
        request.setAttribute("seances", seances);
    }
}
