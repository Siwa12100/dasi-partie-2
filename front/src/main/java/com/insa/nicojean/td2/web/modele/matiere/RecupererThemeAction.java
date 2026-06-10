package com.insa.nicojean.td2.web.modele.matiere;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Theme;
import instructif.metier.service.ServiceMatiere;
import jakarta.servlet.http.HttpServletRequest;

public class RecupererThemeAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        ServiceMatiere service = new ServiceMatiere();
        Theme theme = service.recupererTheme(id);
        request.setAttribute("theme", theme);
    }
}
