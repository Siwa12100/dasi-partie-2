package com.insa.nicojean.td2.web.controleur;

import com.insa.nicojean.td2.web.modele.eleve.*;
import com.insa.nicojean.td2.web.modele.intervenant.*;
import com.insa.nicojean.td2.web.modele.matiere.*;
import com.insa.nicojean.td2.web.modele.seance.*;
import com.insa.nicojean.td2.web.modele.statistiques.ObtenirStatistiquesIntervenantAction;
import com.insa.nicojean.td2.web.vue.*;
import com.insa.nicojean.td2.web.vue.eleve.*;
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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ActionServlet", urlPatterns = {"/api/*"})
public class ActionServlet extends HttpServlet {

    // ---------------
    // Clés de session
    // ---------------
    public static final String SESSION_ELEVE_ID        = "eleveId";
    public static final String SESSION_INTERVENANT_ID  = "intervenantId";

    // ---------------
    // Clés de request
    // ---------------
    public static final String REQUEST_ELEVE_ID        = "sessionEleveId";
    public static final String REQUEST_INTERVENANT_ID  = "sessionIntervenantId";

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.creerFabriquePersistance();
    }

    @Override
    public void destroy() {
        JpaUtil.fermerFabriquePersistance();
        super.destroy();
    }

    // ---------------
    // Helpers session
    // ---------------

    /**
     * Injecte dans la request les ids présents en session (s'ils existent).
     * Les actions peuvent ensuite lire REQUEST_ELEVE_ID / REQUEST_INTERVENANT_ID
     * comme n'importe quel autre attribut de request.
     */
    private void propaguerSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // false = ne pas créer si absente
        if (session == null) return;

        Object eleveId = session.getAttribute(SESSION_ELEVE_ID);
        if (eleveId != null) {
            request.setAttribute(REQUEST_ELEVE_ID, eleveId);
        }

        Object intervenantId = session.getAttribute(SESSION_INTERVENANT_ID);
        if (intervenantId != null) {
            request.setAttribute(REQUEST_INTERVENANT_ID, intervenantId);
        }
    }

    /**
     * Vérifie qu'un élève est bien connecté.
     * Renvoie 401 si ce n'est pas le cas.
     * @return true si la requête peut continuer, false sinon.
     */
    private boolean exigerEleveConnecte(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (request.getAttribute(REQUEST_ELEVE_ID) == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Vous devez être connecté en tant qu'élève.");
            return false;
        }
        return true;
    }

    /**
     * Vérifie qu'un intervenant est bien connecté.
     * Renvoie 401 si ce n'est pas le cas.
     */
    private boolean exigerIntervenantConnecte(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (request.getAttribute(REQUEST_INTERVENANT_ID) == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Vous devez être connecté en tant qu'intervenant.");
            return false;
        }
        return true;
    }

    // -------------------------------------------------------
    // Dispatch principal
    // -------------------------------------------------------

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path   = request.getPathInfo();
        String method = request.getMethod();

        System.out.println("Path   : " + path);
        System.out.println("Method : " + method);

        // On propage systématiquement les ids de session dans la request
        propaguerSession(request);

        switch (path) {

            // -------------------------------------------------------
            // Élèves
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

            case "/eleves/authentifier": {
                // Si déjà connecté, on court-circuite
                if (request.getAttribute(REQUEST_ELEVE_ID) != null) {
                    new EleveSerialisation().appliquer(request, response);
                    break;
                }
                new AuthentifierEleveAction().execute(request);
                // Après authentification réussie, on persiste l'id en session
                ouvrirSessionEleve(request);
                new EleveSerialisation().appliquer(request, response);
                break;
            }

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

            case "/intervenants/authentifier": {
                // Si déjà connecté, on court-circuite
                if (request.getAttribute(REQUEST_INTERVENANT_ID) != null) {
                    new SuccesSerialisation().appliquer(request, response);
                    break;
                }
                new AuthentifierIntervenantAction().execute(request);
                // Après authentification réussie, on persiste l'id en session
                ouvrirSessionIntervenant(request);
                new SuccesSerialisation().appliquer(request, response);
                break;
            }

            // -------------------------------------------------------
            // Matières  (données publiques – pas de session nécessaire)
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
                if (!exigerIntervenantConnecte(request, response)) break;
                new RecupererSoutienAttribueAction().execute(request);
                new SeanceSerialisation().appliquer(request, response);
                break;

            case "/seances/historiqueEleve":
                if (!exigerEleveConnecte(request, response)) break;
                new ObtenirHistoriqueEleveAction().execute(request);
                new ListeSeancesSerialisation().appliquer(request, response);
                break;

            case "/seances/historiqueIntervenant":
                if (!exigerIntervenantConnecte(request, response)) break;
                new ObtenirHistoriqueIntervenantAction().execute(request);
                new ListeSeancesSerialisation().appliquer(request, response);
                break;

            case "/seances/demander":
                if (!exigerEleveConnecte(request, response)) break;
                new DemanderSoutienAction().execute(request);
                new SeanceSerialisation().appliquer(request, response);
                break;

            case "/seances/accepter":
                if (!exigerIntervenantConnecte(request, response)) break;
                new AccepterSoutienAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;

            case "/seances/refuser":
                if (!exigerIntervenantConnecte(request, response)) break;
                new RefuserSoutienAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;

            case "/seances/terminer":
                if (!exigerIntervenantConnecte(request, response)) break;
                new TerminerSoutienAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;

            case "/seances/bilan":
                if (!exigerIntervenantConnecte(request, response)) break;
                new SoumettrebilanAction().execute(request);
                new SuccesSerialisation().appliquer(request, response);
                break;

            // -------------------------------------------------------
            // Statistiques
            // -------------------------------------------------------
            case "/statistiques/intervenant":
                if (!exigerIntervenantConnecte(request, response)) break;
                new ObtenirStatistiquesIntervenantAction().execute(request);
                new StatistiquesIntervenantSerialisation().appliquer(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "Route inconnue : " + path);
                break;
        }
    }

    // -------------------------------------------------------
    // Helpers ouverture de session après authentification
    // -------------------------------------------------------

    /**
     * L'action a placé l'élève authentifié dans request("eleve").
     * On récupère son id et on l'écrit en session.
     */
    private void ouvrirSessionEleve(HttpServletRequest request) {
        Object eleve = request.getAttribute("eleve");
        if (eleve == null) return; // échec d'authentification, pas de session à ouvrir

        // On suppose que l'action a aussi posé l'id dans request("eleveId")
        // ou qu'on peut l'extraire de l'objet eleve.
        // Adapter selon votre modèle.
        Object id = request.getAttribute("eleveId");
        if (id == null) return;

        HttpSession session = request.getSession(true); // créer si absente
        session.setAttribute(SESSION_ELEVE_ID, id);
        // On propage immédiatement pour la suite de cette requête
        request.setAttribute(REQUEST_ELEVE_ID, id);
    }

    private void ouvrirSessionIntervenant(HttpServletRequest request) {
        Object id = request.getAttribute("intervenantId");
        if (id == null) return;

        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_INTERVENANT_ID, id);
        request.setAttribute(REQUEST_INTERVENANT_ID, id);
    }

    // -------------------------------------------------------
    // doGet / doPost
    // -------------------------------------------------------

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
        return "ActionServlet – point d'entrée unique de l'API";
    }
}