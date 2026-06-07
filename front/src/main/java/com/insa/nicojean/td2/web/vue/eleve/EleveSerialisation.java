package com.insa.nicojean.td2.web.vue.eleve;

import instructif.metier.modele.Eleve;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.insa.nicojean.td2.web.vue.Serialisation;

public class EleveSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        Eleve eleve = (Eleve) request.getAttribute("eleve");

        JsonObjectBuilder json = Json.createObjectBuilder();
        if (eleve != null) {
            json.add("id", eleve.getId())
                .add("nom", eleve.getNom())
                .add("prenom", eleve.getPrenom())
                .add("mail", eleve.getMail())
                .add("classe", eleve.getClasse().name())
                .add("college", Json.createObjectBuilder()
                    .add("id", eleve.getCollege().getId())
                    .add("nom", eleve.getCollege().getNom())
                    .add("codeEtablissement", eleve.getCollege().getCodeEtablissement())
                    .add("commune", eleve.getCollege().getCommune())
                );
        } else {
            json.addNull("eleve");
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(json.build().toString());
        }
    }
}
