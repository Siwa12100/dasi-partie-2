package com.insa.nicojean.td2.web.modele.eleve;

import instructif.metier.modele.Eleve;
import instructif.metier.service.ServiceEleve;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

import com.insa.nicojean.td2.web.modele.Action;

public class ListerElevesAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        List<Eleve> eleves = new ServiceEleve().listerEleves();
        request.setAttribute("eleves", eleves);
    }
}
