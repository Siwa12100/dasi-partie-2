package com.insa.nicojean.td2.web.modele.eleve;

import com.insa.nicojean.td2.web.modele.Action;

import instructif.metier.modele.Eleve;
import instructif.metier.service.ServiceEleve;
import jakarta.servlet.http.HttpServletRequest;

public class RecupererEleveAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Eleve eleve = new ServiceEleve().recupererEleve(id);
            request.setAttribute("eleve", eleve);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("eleve", null);
        }
    }
}
