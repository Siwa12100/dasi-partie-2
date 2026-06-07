package com.insa.nicojean.td2.web.vue.seance;

import com.insa.nicojean.td2.web.vue.Serialisation;
import instructif.metier.modele.SeanceSoutien;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SeanceSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SeanceSoutien s = (SeanceSoutien) request.getAttribute("seance");
        response.setContentType("application/json");
        response.getWriter().write(serialiserSeance(s));
    }

    public static String serialiserSeance(SeanceSoutien s) {
        if (s == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append("{")
          .append("\"id\":").append(s.getId()).append(",")
          .append("\"dateHeure\":\"").append(s.getDateHeure()).append("\",")
          .append("\"statut\":\"").append(s.getStatut()).append("\",")
          .append("\"messageDemande\":\"").append(s.getMessageDemande()).append("\",")
          .append("\"lienVisio\":").append(s.getLienVisio() != null ? "\"" + s.getLienVisio() + "\"" : "null").append(",")
          .append("\"duree\":").append(s.getDuree() != null ? s.getDuree() : "null").append(",")
          .append("\"compteRendu\":").append(s.getCompteRendu() != null ? "\"" + s.getCompteRendu() + "\"" : "null").append(",")
          .append("\"eleve\":{\"id\":").append(s.getEleve().getId())
            .append(",\"nom\":\"").append(s.getEleve().getNom())
            .append("\",\"prenom\":\"").append(s.getEleve().getPrenom()).append("\"},")
          .append("\"theme\":{\"id\":").append(s.getTheme().getId())
            .append(",\"nom\":\"").append(s.getTheme().getNom())
            .append("\",\"matiere\":{\"id\":").append(s.getTheme().getMatiere().getId())
            .append(",\"nom\":\"").append(s.getTheme().getMatiere().getNom()).append("\"}},")
          .append("\"intervenant\":");
        if (s.getIntervenant() != null) {
            sb.append("{\"id\":").append(s.getIntervenant().getId())
              .append(",\"nom\":\"").append(s.getIntervenant().getNom())
              .append("\",\"prenom\":\"").append(s.getIntervenant().getPrenom()).append("\"}");
        } else {
            sb.append("null");
        }
        sb.append("}");
        return sb.toString();
    }
}
