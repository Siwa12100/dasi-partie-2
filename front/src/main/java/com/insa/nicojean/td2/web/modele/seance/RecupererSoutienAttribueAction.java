package com.insa.nicojean.td2.web.modele.seance;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.SeanceSoutien;
import instructif.metier.service.ServiceSeanceSoutien;
import jakarta.servlet.http.HttpServletRequest;

public class RecupererSoutienAttribueAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long idIntervenant = Long.parseLong(request.getParameter("idIntervenant"));
        ServiceSeanceSoutien service = new ServiceSeanceSoutien();
        SeanceSoutien seance = service.recupererSoutienAttribue(idIntervenant);
        request.setAttribute("seance", seance);
    }
}
