package com.insa.nicojean.td2.web.modele.seance;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.service.ServiceSeanceSoutien;
import jakarta.servlet.http.HttpServletRequest;

public class AccepterSoutienAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        ServiceSeanceSoutien service = new ServiceSeanceSoutien();
        boolean succes = service.accepterSoutien(id);
        request.setAttribute("succes", succes);
    }
}
