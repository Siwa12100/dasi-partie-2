package com.insa.nicojean.td2.web.vue;

import jakarta.json.Json;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SuccesSerialisation extends Serialisation {
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        boolean succes = (boolean) request.getAttribute("succes");
        try (PrintWriter out = response.getWriter()) {
            out.print(Json.createObjectBuilder().add("succes", succes).build().toString());
        }
    }
}
