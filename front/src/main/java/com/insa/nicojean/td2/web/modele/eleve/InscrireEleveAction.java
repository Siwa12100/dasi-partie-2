package com.insa.nicojean.td2.web.modele.eleve;

import instructif.metier.modele.Eleve;
import instructif.metier.modele.Niveau;
import instructif.metier.service.ServiceEleve;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

import com.insa.nicojean.td2.web.modele.Action;

public class InscrireEleveAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        try {
            JsonObject body = Json.createReader(request.getInputStream()).readObject();
            
            Eleve eleve = new Eleve(
                body.getString("nom"),
                body.getString("prenom"),
                new Date(body.getJsonNumber("dateNaissance").longValue()),
                body.getString("mail"),
                body.getString("motDePasse"),
                null, // college set par le service
                Niveau.valueOf(body.getString("classe"))
            );
            String codeEtablissement = body.getString("codeEtablissement");

            boolean ok = new ServiceEleve().inscrireEleve(eleve, codeEtablissement);
            request.setAttribute("succes", ok);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("succes", false);
        }
    }
}
