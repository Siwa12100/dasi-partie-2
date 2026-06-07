package com.insa.nicojean.td2.web.modele.seance;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.service.ServiceSeanceSoutien;
import jakarta.servlet.http.HttpServletRequest;

public class SoumettrebilanAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        String compteRendu = request.getParameter("compteRendu");
        ServiceSeanceSoutien service = new ServiceSeanceSoutien();
        boolean succes = service.soumettreBilan(id, compteRendu);
        request.setAttribute("succes", succes);
    }
}
