package fr.pau.univ.series.servlets;

import java.io.IOException;

import fr.pau.univ.series.dao.DaoFactory;
import fr.pau.univ.series.exception.DaoException;
import fr.pau.univ.series.model.Saison;
import fr.pau.univ.series.model.Serie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/supprimerSaison")
public class SupprimerSaison extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String serieIdStr = request.getParameter("serie");
        String saisonIdStr = request.getParameter("saison");
        String episodeIdStr = request.getParameter("episode");

        if (serieIdStr != null && !serieIdStr.isBlank() && saisonIdStr != null && !saisonIdStr.isBlank()) {
            try {
                int serieId = Integer.parseInt(serieIdStr);
                int saisonId = Integer.parseInt(saisonIdStr);
                
                Serie serie = DaoFactory.getInstance().getSerieDao().readSerie(serieId);
                if (serie != null && serie.getSaisons() != null) {
                    
                    // Si un ID d'épisode est présent, on supprime l'épisode
                    if (episodeIdStr != null && !episodeIdStr.isBlank()) {
                        int episodeId = Integer.parseInt(episodeIdStr);
                        for (Saison s : serie.getSaisons()) {
                            if (s.getId() == saisonId && s.getEpisodes() != null) {
                                s.getEpisodes().removeIf(ep -> ep.getId() == episodeId);
                                break;
                            }
                        }
                    } else {
                        // Sinon, on supprime la saison entière
                        serie.getSaisons().removeIf(s -> s.getId() == saisonId);
                    }
                    
                    // Mise à jour de la série (la cascade JPA supprime l'élément en BDD)
                    DaoFactory.getInstance().getSerieDao().updateSerie(serie);
                }
            } catch (DaoException | NumberFormatException e) {
                request.setAttribute("erreur", e.getMessage());
                e.printStackTrace();
            }
        }
        response.sendRedirect(request.getContextPath() + "/liste");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}