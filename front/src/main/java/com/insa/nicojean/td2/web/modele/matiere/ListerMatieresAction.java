package com.insa.nicojean.td2.web.modele.matiere;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Matiere;
import instructif.metier.service.ServiceMatiere;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class ListerMatieresAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        ServiceMatiere service = new ServiceMatiere();
        List<Matiere> matieres = service.listerMatieres();
        request.setAttribute("matieres", matieres);
    }
}
