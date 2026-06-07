package com.insa.nicojean.td2.web.modele.eleve;

import com.insa.nicojean.td2.web.modele.Action;

import instructif.metier.modele.Eleve;
import instructif.metier.service.ServiceEleve;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.http.HttpServletRequest;

public class AuthentifierEleveAction extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        try {
            JsonObject body = Json.createReader(request.getInputStream()).readObject();
            String mail = body.getString("mail");
            String mdp = body.getString("motDePasse");

            Eleve eleve = new ServiceEleve().authentifierEleve(mail, mdp);
            request.setAttribute("eleve", eleve);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("eleve", null);
        }
    }
}
