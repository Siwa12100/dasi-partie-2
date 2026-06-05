/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.insa.nicojean.td2.web.modele;

import com.insa.nicojean.td2.web.test.DemandeTest;
import jakarta.servlet.http.HttpServletRequest;
import com.insa.nicojean.td2.web.test.ServiceTest;
import java.util.List;

/**
 *
 * @author ncolomb
 */
public class ConsulterListeDemandesAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {
        
        ServiceTest service = new ServiceTest();
        List<DemandeTest> demandes = service.listerDemandes();
        
        request.setAttribute("demandes", demandes);
        
        for (DemandeTest demande : demandes) {
            System.out.println(demande);
        }
    }
}
