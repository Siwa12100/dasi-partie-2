package com.insa.nicojean.td2.web.modele.seance;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.SeanceSoutien;
import instructif.metier.service.ServiceSeanceSoutien;
import jakarta.servlet.http.HttpServletRequest;

public class RecupererSeanceAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        ServiceSeanceSoutien service = new ServiceSeanceSoutien();
        SeanceSoutien seance = service.recupererSeanceSoutien(id);
        request.setAttribute("seance", seance);
    }
}
