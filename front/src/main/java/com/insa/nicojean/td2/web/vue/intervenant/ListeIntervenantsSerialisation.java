package com.insa.nicojean.td2.web.vue.intervenant;

import com.insa.nicojean.td2.web.vue.Serialisation;
import instructif.metier.modele.Intervenant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListeIntervenantsSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Intervenant> intervenants = (List<Intervenant>) request.getAttribute("intervenants");
        response.setContentType("application/json");
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < intervenants.size(); i++) {
            Intervenant v = intervenants.get(i);
            sb.append("{\"id\":").append(v.getId())
              .append(",\"nom\":\"").append(v.getNom()).append("\"")
              .append(",\"prenom\":\"").append(v.getPrenom()).append("\"")
              .append(",\"mail\":\"").append(v.getMail()).append("\"")
              .append(",\"telephone\":\"").append(v.getTelephone()).append("\"")
              .append(",\"niveauMin\":\"").append(v.getNiveauMin()).append("\"")
              .append(",\"niveauMax\":\"").append(v.getNiveauMax()).append("\"}");
            if (i < intervenants.size() - 1) sb.append(",");
        }
        sb.append("]");
        response.getWriter().write(sb.toString());
    }
}
