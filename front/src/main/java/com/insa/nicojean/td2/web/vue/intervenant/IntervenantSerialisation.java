package com.insa.nicojean.td2.web.vue.intervenant;

import com.insa.nicojean.td2.web.vue.Serialisation;
import instructif.metier.modele.Intervenant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IntervenantSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Intervenant intervenant = (Intervenant) request.getAttribute("intervenant");
        response.setContentType("application/json");
        response.getWriter().write(
            "{\"id\":" + intervenant.getId() +
            ",\"nom\":\"" + intervenant.getNom() + "\"" +
            ",\"prenom\":\"" + intervenant.getPrenom() + "\"" +
            ",\"mail\":\"" + intervenant.getMail() + "\"" +
            ",\"telephone\":\"" + intervenant.getTelephone() + "\"" +
            ",\"niveauMin\":\"" + intervenant.getNiveauMin() + "\"" +
            ",\"niveauMax\":\"" + intervenant.getNiveauMax() + "\"}"
        );
    }
}
