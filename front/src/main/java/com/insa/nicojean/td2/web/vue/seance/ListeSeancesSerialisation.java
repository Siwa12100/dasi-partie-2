package com.insa.nicojean.td2.web.vue.seance;

import com.insa.nicojean.td2.web.vue.Serialisation;
import instructif.metier.modele.SeanceSoutien;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListeSeancesSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<SeanceSoutien> seances = (List<SeanceSoutien>) request.getAttribute("seances");
        response.setContentType("application/json");
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < seances.size(); i++) {
            sb.append(SeanceSerialisation.serialiserSeance(seances.get(i)));
            if (i < seances.size() - 1) sb.append(",");
        }
        sb.append("]");
        response.getWriter().write(sb.toString());
    }
}
