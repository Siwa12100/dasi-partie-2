package com.insa.nicojean.td2.web.modele.matiere;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Matiere;
import instructif.metier.service.ServiceMatiere;
import jakarta.servlet.http.HttpServletRequest;

public class RecupererMatiereAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        ServiceMatiere service = new ServiceMatiere();
        Matiere matiere = service.recupererMatiere(id);
        request.setAttribute("matiere", matiere);
    }
}
