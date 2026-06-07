package com.insa.nicojean.td2.web.modele.intervenant;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Intervenant;
import instructif.metier.service.ServiceIntervenant;
import jakarta.servlet.http.HttpServletRequest;

public class RecupererIntervenantAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        ServiceIntervenant service = new ServiceIntervenant();
        Intervenant intervenant = service.recupererIntervenant(id);
        request.setAttribute("intervenant", intervenant);
    }
}
