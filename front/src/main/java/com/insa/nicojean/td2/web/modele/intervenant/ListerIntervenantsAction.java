package com.insa.nicojean.td2.web.modele.intervenant;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Intervenant;
import instructif.metier.service.ServiceIntervenant;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class ListerIntervenantsAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        ServiceIntervenant service = new ServiceIntervenant();
        List<Intervenant> intervenants = service.listerIntervenants();
        request.setAttribute("intervenants", intervenants);
    }
}
