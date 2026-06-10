package com.insa.nicojean.td2.web.controleur;

import com.insa.nicojean.td2.web.modele.ConsulterListeDemandesAction;
import com.insa.nicojean.td2.web.modele.eleve.AuthentifierEleveAction;
import com.insa.nicojean.td2.web.modele.eleve.InscrireEleveAction;
import com.insa.nicojean.td2.web.modele.eleve.ListerElevesAction;
import com.insa.nicojean.td2.web.modele.eleve.RecupererEleveAction;
import com.insa.nicojean.td2.web.modele.intervenant.*;
import com.insa.nicojean.td2.web.modele.matiere.*;
import com.insa.nicojean.td2.web.modele.seance.*;
import com.insa.nicojean.td2.web.modele.statistiques.ObtenirStatistiquesIntervenantAction;
import com.insa.nicojean.td2.web.vue.*;
import com.insa.nicojean.td2.web.vue.eleve.EleveSerialisation;
import com.insa.nicojean.td2.web.vue.eleve.ListeElevesSerialisation;
import com.insa.nicojean.td2.web.vue.intervenant.*;
import com.insa.nicojean.td2.web.vue.matiere.*;
import com.insa.nicojean.td2.web.vue.seance.*;
import com.insa.nicojean.td2.web.vue.statistiques.StatistiquesIntervenantSerialisation;

import instructif.dao.JpaUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ActionServlet", urlPatterns = {"/api/*"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        JpaUtil.creerFabriquePersistance();
    }

    @Override
    public void destroy() {
        JpaUtil.fermerFabriquePersistance();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();
        String method = request.getMethod();

        System.out.println("Param Path : " + path);
        System.out.println("Method : " + method);

        switch (path) {

            // -------------------------------------------------------
            // Ancienne route
            // -------------------------------------------------------
            case "/consulter-liste-demandes":
                new ConsulterListeDemandesAction().execute(request);
                new ListeDemandesSerialisation().appliquer(request, response);
                break;

            // -------------------------------------------------------
            // Elèves
            // -------------------------------------------------------
            case "/eleves/lister":
                new ListerElevesAction().execute(request);
                new ListeElevesSerialisation().appliquer(request, response);
                break;

            case "/eleves/recuperer":
                new RecupererEleveAction().execute(request);
                new EleveSerialisation().appliquer(request, response);
                break;

            case "/eleves/inscrire":
                new InscrireEleveAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;

            case "/eleves/authentifier":
                new AuthentifierEleveAction().execute(request);
                new EleveSerialisation().appliquer(request, response);
                break;

            // -------------------------------------------------------
            // Intervenants
            // -------------------------------------------------------
            case "/intervenants/lister":
                new ListerIntervenantsAction().execute(request);
                new ListeIntervenantsSerialisation().appliquer(request, response);
                break;

            case "/intervenants/recuperer":
                new RecupererIntervenantAction().execute(request);
                new IntervenantSerialisation().appliquer(request, response);
                break;

            case "/intervenants/authentifier":
                new AuthentifierIntervenantAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;

            // -------------------------------------------------------
            // Matières
            // -------------------------------------------------------
            case "/matieres/listerMatieres":
                new ListerMatieresAction().execute(request);
                new ListeMatieresSerialisation().appliquer(request, response);
                break;

            case "/matieres/recupererMatiere":
                new RecupererMatiereAction().execute(request);
                new MatiereSerialisation().appliquer(request, response);
                break;

            case "/matieres/listerThemes":
                new ListerThemesAction().execute(request);
                new ListeThemesSerialisation().appliquer(request, response);
                break;

            case "/matieres/recupererTheme":
                new RecupererThemeAction().execute(request);
                new ThemeSerialisation().appliquer(request, response);
                break;

            case "/matieres/recupererThemesParMatiere":
                new RecupererThemesParMatiereAction().execute(request);
                new ListeThemesSerialisation().appliquer(request, response);
                break;

            // -------------------------------------------------------
            // Séances
            // -------------------------------------------------------
            case "/seances/recuperer":
                new RecupererSeanceAction().execute(request);
                new SeanceSerialisation().appliquer(request, response);
                break;

            case "/seances/recupererAttribuee":
                new RecupererSoutienAttribueAction().execute(request);
                new SeanceSerialisation().appliquer(request, response);
                break;

            case "/seances/historiqueEleve":
                new ObtenirHistoriqueEleveAction().execute(request);
                new ListeSeancesSerialisation().appliquer(request, response);
                break;

            case "/seances/historiqueIntervenant":
                new ObtenirHistoriqueIntervenantAction().execute(request);
                new ListeSeancesSerialisation().appliquer(request, response);
                break;

            case "/seances/demander":
                new DemanderSoutienAction().execute(request);
                new SeanceSerialisation().appliquer(request, response);
                break;

            case "/seances/accepter":
                new AccepterSoutienAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;

            case "/seances/refuser":
                new RefuserSoutienAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;

            case "/seances/terminer":
                new TerminerSoutienAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;

            case "/seances/bilan":
                new SoumettrebilanAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;

            // -------------------------------------------------------
            // Statistiques
            // -------------------------------------------------------
            case "/statistiques/intervenant":
                new ObtenirStatistiquesIntervenantAction().execute(request);
                new StatistiquesIntervenantSerialisation().appliquer(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Route inconnue : " + path);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ActionServlet - point d'entrée unique de l'API";
    }
}






// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
//  */
// package com.insa.nicojean.td2.web.controleur;

// import com.insa.nicojean.td2.web.modele.ConsulterListeDemandesAction;
// import com.insa.nicojean.td2.web.vue.ListeDemandesSerialisation;
// import java.io.IOException;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// /**
//  *
//  * @author ncolomb
//  */
// @WebServlet(name = "ActionServlet", urlPatterns = {"/api/*"})
// public class ActionServlet extends HttpServlet {

//     /**
//      * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//      * methods.
//      *
//      * @param request servlet request
//      * @param response servlet response
//      * @throws ServletException if a servlet-specific error occurs
//      * @throws IOException if an I/O error occurs
//      */
//     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
            
//             String path = request.getPathInfo(); // /consulter-liste-demandes
        
//         System.out.println("Param Path : " + path);
        
//         switch (path) {
//             case "/consulter-liste-demandes":
//                 new ConsulterListeDemandesAction().execute(request);
//                 new ListeDemandesSerialisation().appliquer(request, response);
//                 break;
//             default:
                        
//         }
//     }

//     // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//     /**
//      * Handles the HTTP <code>GET</code> method.
//      *
//      * @param request servlet request
//      * @param response servlet response
//      * @throws ServletException if a servlet-specific error occurs
//      * @throws IOException if an I/O error occurs
//      */
//     @Override
//     protected void doGet(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         processRequest(request, response);
//     }

//     /**
//      * Handles the HTTP <code>POST</code> method.
//      *
//      * @param request servlet request
//      * @param response servlet response
//      * @throws ServletException if a servlet-specific error occurs
//      * @throws IOException if an I/O error occurs
//      */
//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         processRequest(request, response);
//     }

//     /**
//      * Returns a short description of the servlet.
//      *
//      * @return a String containing servlet description
//      */
//     @Override
//     public String getServletInfo() {
//         return "Short description";
//     }// </editor-fold>

// }
