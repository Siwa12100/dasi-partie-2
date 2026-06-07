package com.insa.nicojean.td2.web.vue.statistiques;

import com.insa.nicojean.td2.web.vue.Serialisation;
import instructif.metier.modele.College;
import instructif.metier.modele.StatistiquesIntervenant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StatistiquesIntervenantSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StatistiquesIntervenant stats = (StatistiquesIntervenant) request.getAttribute("stats");
        response.setContentType("application/json");

        StringBuilder sb = new StringBuilder();
        sb.append("{")
          .append("\"entretiensNb\":").append(stats.getEntretiensNb()).append(",")
          .append("\"moyenneDuree\":").append(stats.getMoyenneDuree()).append(",")
          .append("\"colleges\":[");

        List<College> colleges = stats.getColleges();
        for (int i = 0; i < colleges.size(); i++) {
            College c = colleges.get(i);
            sb.append("{\"id\":").append(c.getId())
              .append(",\"nom\":\"").append(c.getNom()).append("\"}");
            if (i < colleges.size() - 1) sb.append(",");
        }
        sb.append("]}");

        response.getWriter().write(sb.toString());
    }
}
