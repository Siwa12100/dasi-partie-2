package com.insa.nicojean.td2.web.vue.eleve;

import instructif.metier.modele.Eleve;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.insa.nicojean.td2.web.vue.Serialisation;

public class ListeElevesSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        @SuppressWarnings("unchecked")
        List<Eleve> eleves = (List<Eleve>) request.getAttribute("eleves");

        JsonObjectBuilder container = Json.createObjectBuilder();
        JsonArrayBuilder liste = Json.createArrayBuilder();

        if (eleves != null) {
            for (Eleve eleve : eleves) {
                liste.add(Json.createObjectBuilder()
                    .add("id", eleve.getId())
                    .add("nom", eleve.getNom())
                    .add("prenom", eleve.getPrenom())
                    .add("mail", eleve.getMail())
                    .add("classe", eleve.getClasse().name())
                    .add("college", eleve.getCollege().getNom())
                );
            }
        }
        container.add("eleves", liste);

        try (PrintWriter out = response.getWriter()) {
            out.print(container.build().toString());
        }
    }
}
