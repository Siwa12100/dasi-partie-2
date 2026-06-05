/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instructif.util;

import instructif.metier.modele.College;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author aessalhi
 */
public class EducationGouvCollegeAPI {
    private final static String ETABLISSEMENT_INFO_URI = "https://data.education.gouv.fr/api/explore/v2.1/catalog/datasets/fr-en-adresse-et-geolocalisation-etablissements-premier-et-second-degre/records";
    
    public static College getCollege(String codeEtablissement) {
        College result = null;

        try {
            URI requestUri = URI.create(ETABLISSEMENT_INFO_URI
                    + "?refine=numero_uai:"+ URLEncoder.encode(codeEtablissement, StandardCharsets.UTF_8)
                );

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder(requestUri).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() == 200) {
                String body = httpResponse.body();

                JsonObject response = Json.createReader(new StringReader(body)).readObject();
                if (response.getInt("total_count") > 0) {
                    JsonObject object = response.getJsonArray("results").getJsonObject(0);

                    result = new College(
                            codeEtablissement,
                            object.getString("appellation_officielle"),
                            object.getString("adresse_uai"),
                            object.getString("code_postal_uai"),
                            object.getString("libelle_commune"),
                            object.getString("libelle_departement"),
                            object.getString("libelle_academie"),
                            object.getString("secteur_public_prive_libe"),
                            object.getJsonNumber("latitude").doubleValue(),
                            object.getJsonNumber("longitude").doubleValue()
                    );
                }
            }
            else {
                throw new IOException("HTTP Error Status Code "+httpResponse.statusCode());
            }

        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
            result = null;
        }
        
        return result; 
    }
}
