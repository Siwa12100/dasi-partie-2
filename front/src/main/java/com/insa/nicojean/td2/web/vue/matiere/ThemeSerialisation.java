package com.insa.nicojean.td2.web.vue.matiere;

import com.insa.nicojean.td2.web.vue.Serialisation;
import instructif.metier.modele.Theme;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ThemeSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Theme theme = (Theme) request.getAttribute("theme");
        response.setContentType("application/json");
        response.getWriter().write(
            "{\"id\":" + theme.getId() +
            ",\"nom\":\"" + theme.getNom() +
            "\",\"matiere\":{\"id\":" + theme.getMatiere().getId() +
            ",\"nom\":\"" + theme.getMatiere().getNom() + "\"}}"
        );
    }
}
