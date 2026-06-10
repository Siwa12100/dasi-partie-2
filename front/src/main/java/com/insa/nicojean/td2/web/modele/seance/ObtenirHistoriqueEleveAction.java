package com.insa.nicojean.td2.web.modele.seance;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Eleve;
import instructif.metier.modele.SeanceSoutien;
import instructif.metier.service.ServiceEleve;
import instructif.metier.service.ServiceSeanceSoutien;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class ObtenirHistoriqueEleveAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long idEleve = Long.parseLong(request.getParameter("idEleve"));
        Eleve eleve = new ServiceEleve().recupererEleve(idEleve);
        ServiceSeanceSoutien service = new ServiceSeanceSoutien();
        List<SeanceSoutien> historique = service.obtenirHistoriqueEleve(eleve);
        request.setAttribute("seances", historique);
    }
}
