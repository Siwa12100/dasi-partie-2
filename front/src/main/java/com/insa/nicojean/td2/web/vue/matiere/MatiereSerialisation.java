package com.insa.nicojean.td2.web.vue.matiere;

import com.insa.nicojean.td2.web.vue.Serialisation;
import instructif.metier.modele.Matiere;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MatiereSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Matiere matiere = (Matiere) request.getAttribute("matiere");
        response.setContentType("application/json");
        response.getWriter().write(
            "{\"id\":" + matiere.getId() + ",\"nom\":\"" + matiere.getNom() + "\"}"
        );
    }
}
