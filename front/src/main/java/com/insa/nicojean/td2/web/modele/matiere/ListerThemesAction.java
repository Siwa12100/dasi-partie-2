package com.insa.nicojean.td2.web.modele.matiere;

import com.insa.nicojean.td2.web.modele.Action;
import instructif.metier.modele.Theme;
import instructif.metier.service.ServiceMatiere;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public class ListerThemesAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        ServiceMatiere service = new ServiceMatiere();
        List<Theme> themes = service.listerThemes();
        request.setAttribute("themes", themes);
    }
}
