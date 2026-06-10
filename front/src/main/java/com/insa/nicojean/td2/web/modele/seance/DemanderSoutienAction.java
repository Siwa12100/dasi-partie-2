package com.insa.nicojean.td2.web.modele.seance;

import com.insa.nicojean.td2.web.controleur.ActionServlet;
import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Eleve;
import instructif.metier.modele.SeanceSoutien;
import instructif.metier.modele.Theme;
import instructif.metier.service.ServiceEleve;
import instructif.metier.service.ServiceMatiere;
import instructif.metier.service.ServiceSeanceSoutien;
import jakarta.servlet.http.HttpServletRequest;

public class DemanderSoutienAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long idEleve = (Long) request.getAttribute(ActionServlet.REQUEST_ELEVE_ID);
        Long idTheme = Long.parseLong(request.getParameter("idTheme"));
        String description = request.getParameter("description");

        Eleve eleve = new ServiceEleve().recupererEleve(idEleve);
        Theme theme = new ServiceMatiere().recupererTheme(idTheme);

        ServiceSeanceSoutien service = new ServiceSeanceSoutien();
        SeanceSoutien seance = service.demanderSoutien(eleve, theme, description);
        request.setAttribute("succes", seance != null);
        request.setAttribute("seance", seance);
    }
}
