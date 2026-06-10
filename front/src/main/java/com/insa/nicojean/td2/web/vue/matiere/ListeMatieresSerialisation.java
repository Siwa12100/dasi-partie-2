package com.insa.nicojean.td2.web.vue.matiere;

import com.insa.nicojean.td2.web.vue.Serialisation;
import instructif.metier.modele.Matiere;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListeMatieresSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Matiere> matieres = (List<Matiere>) request.getAttribute("matieres");
        response.setContentType("application/json");
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < matieres.size(); i++) {
            Matiere m = matieres.get(i);
            sb.append("{\"id\":").append(m.getId()).append(",\"nom\":\"").append(m.getNom()).append("\"}");
            if (i < matieres.size() - 1) sb.append(",");
        }
        sb.append("]");
        response.getWriter().write(sb.toString());
    }
}
