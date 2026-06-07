package com.insa.nicojean.td2.web.vue.matiere;

import com.insa.nicojean.td2.web.vue.Serialisation;
import instructif.metier.modele.Theme;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListeThemesSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Theme> themes = (List<Theme>) request.getAttribute("themes");
        response.setContentType("application/json");
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < themes.size(); i++) {
            Theme t = themes.get(i);
            sb.append("{\"id\":").append(t.getId())
              .append(",\"nom\":\"").append(t.getNom())
              .append("\",\"matiere\":{\"id\":").append(t.getMatiere().getId())
              .append(",\"nom\":\"").append(t.getMatiere().getNom()).append("\"}}");
            if (i < themes.size() - 1) sb.append(",");
        }
        sb.append("]");
        response.getWriter().write(sb.toString());
    }
}
