/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.insa.nicojean.td2.web.vue;

import com.insa.nicojean.td2.web.test.DemandeTest;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ncolomb
 */
public class ListeDemandesSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        List<DemandeTest> listeDemandes = (List<DemandeTest>)request.getAttribute("demandes");

        JsonObjectBuilder jsonContainer = Json.createObjectBuilder();
        JsonArrayBuilder jsonListe = Json.createArrayBuilder();

        for (DemandeTest demande : listeDemandes) {
           JsonObjectBuilder jsonDemande = Json.createObjectBuilder()
                   .add("id", demande.getId())
                   .add("dateCreation", demande.getDateCreation().toString())
                   .add("description", demande.getDescription());

            jsonListe.add(jsonDemande);
        }
        
        jsonContainer.add("demandes", jsonListe);
        
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonContainer.build().toString());
            out.close();
        }
    }
}
