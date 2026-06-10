package com.insa.nicojean.td2.web.modele.intervenant;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Intervenant;
import instructif.metier.service.ServiceIntervenant;
import jakarta.servlet.http.HttpServletRequest;

public class AuthentifierIntervenantAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        String mail = request.getParameter("mail");
        String mdp = request.getParameter("mdp");
        ServiceIntervenant service = new ServiceIntervenant();
        Intervenant intervenant = service.authentifierIntervenant(mail, mdp);
        request.setAttribute("succes", intervenant != null);
        if (intervenant != null) {
            request.setAttribute("intervenantId", intervenant.getId());
        }
    }
}
