package com.insa.nicojean.td2.web.modele.matiere;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Theme;
import instructif.metier.service.ServiceMatiere;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class RecupererThemesParMatiereAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long idMatiere = Long.parseLong(request.getParameter("idMatiere"));
        ServiceMatiere service = new ServiceMatiere();
        List<Theme> themes = service.recupererThemesParMatiere(idMatiere);
        request.setAttribute("themes", themes);
    }
}
